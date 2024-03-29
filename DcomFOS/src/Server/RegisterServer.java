/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import DatabaseObject.Orders;
import FOSInterface.RegisterInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
                
/**
 *
 * @author User
 */
public class RegisterServer extends UnicastRemoteObject implements RegisterInterface{
    public RegisterServer() throws RemoteException{
        super();
    }
    
@Override
public boolean registerAccount(String username, String password, String icnumber,String firstname, String lastname) throws RemoteException { 
    Connection conn = null;
    Statement stmt = null;
    ResultSet resultSet = null;
    boolean isDuplicate = false;

    try {
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DcomsFOS", "root", "toor");
        
        // Check if the username or icnumber is already in use
        String checkQuery = "SELECT * FROM USERS WHERE USERNAME = ? OR ICPASSPORTNUMBER = ?";
        PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
        checkStmt.setString(1, username);
        checkStmt.setString(2, icnumber);
        resultSet = checkStmt.executeQuery();
        
        if (resultSet.next()) {
            // Either the username or icnumber is already in use
            isDuplicate = true; // Registration failed
        } else {
            isDuplicate = false;
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO USERS (USERNAME, PASSWORD, ACTOR, ICPASSPORTNUMBER, FIRSTNAME, LASTNAME) VALUES ('" + username + "', '" + password + "', 'customer', '" + icnumber + "', '" + firstname + "', '" + lastname + "')");
            
            //get userid and insert into cart
            int userID = 0;
            Statement stmtUserID = conn.createStatement();
            String queryUserID = "Select UserID from USERS where USERNAME = '" + username + "'";
            ResultSet rsUserID = stmtUserID.executeQuery(queryUserID);
            if(rsUserID.next())
            {
               userID = rsUserID.getInt("UserID");
                System.out.println("UserID is: " + userID);
            }     
            Statement stmtInsertCart = conn.createStatement();
            String insertCart = "INSERT INTO CART (USERID, STATUS) VALUES (" + userID + ", 'empty') ";
            int rsInsertCart = stmtInsertCart.executeUpdate(insertCart);
            if(rsInsertCart>0)
            {
                System.out.println("Cart inserted, with number of rows affected: " + rsInsertCart);
            }
        }       
    } catch (SQLException e) {
        e.printStackTrace();
        isDuplicate = false; // Registration failed
    } finally {
        // Close the resources in the finally block
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
        return isDuplicate;
 

}

@Override
public boolean loginAccount(String username, String password) throws RemoteException {
    boolean loginSuccessful = false;

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DcomsFOS", "root", "toor");
        String query = "SELECT PASSWORD FROM USERS WHERE USERNAME = ?";
        pstmt = conn.prepareStatement(query);
        pstmt.setString(1, username);
        rs = pstmt.executeQuery();

        if (rs.next()) {
            String storedPasswordHash = rs.getString("PASSWORD");
            if (storedPasswordHash.equals(password)) {
                loginSuccessful = true;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(RegisterServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    return loginSuccessful;
}

@Override
public int getUserID( String username)throws RemoteException {
    int UserID = 0;
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DcomsFOS", "root", "toor");
        String query = "SELECT USERID FROM USERS WHERE USERNAME = ?";
        pstmt = conn.prepareStatement(query);
        pstmt.setString(1, username);
        rs = pstmt.executeQuery();

        if (rs.next()) {
            int storedUserID = rs.getInt("USERID");
            UserID = storedUserID;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return UserID;
}
public List<Orders> getOrderDetails(int orderID) throws RemoteException, SQLException {
        List<Orders> orderItemList = new ArrayList<>();
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DcomsFOS","root","toor");
        String getOrderTotal= "select totalprice as total, status, modeID from Orders where OrderID = " + orderID;
        Statement stm1 = conn.createStatement();
        ResultSet rs1 = stm1.executeQuery(getOrderTotal);
        double total=0;
        if (rs1.next())
        {
            System.out.println(rs1.getDouble("total"));
            total = rs1.getDouble("total");
            System.out.println("Total of OrderID gotten is: " + total);
            Orders orderItem = new Orders(rs1.getDouble("total"), rs1.getString("status"), rs1.getInt("modeID"));
            orderItemList.add(orderItem);
        }
        return orderItemList;
    }
    
@Override
    public List<Orders> getOrder(int userID) throws RemoteException, SQLException {
    List<Orders> orderList = new ArrayList<>();
    Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DcomsFOS", "root", "toor");
    String getOrderTotal = "SELECT orderID, totalprice as total, status, modeID FROM Orders WHERE userID = ?";
    
    try (PreparedStatement stm1 = conn.prepareStatement(getOrderTotal)) {
        stm1.setInt(1, userID);
        ResultSet rs1 = stm1.executeQuery();
        
        while (rs1.next()) {
            int orderID = rs1.getInt("orderID");
            double total = rs1.getDouble("total");
            String status = rs1.getString("status");
            int modeID = rs1.getInt("modeID");
            String modeName;
            if (modeID == 1){
                modeName = "Dine In";
            } else{
                modeName = "Takeaway";
            }
            Orders orderItem = new Orders(orderID, total, status, modeName);
            orderList.add(orderItem);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        conn.close();
    }
    
    return orderList;
}
}

//stm.executeUpdate("INSERT INTO USERS (USERNAME, PASSWORD, ACTOR, ICPASSPORTNUMBER, FIRSTNAME, LASTNAME) VALUES ('" + username + "', '" + password + "', 'customer', '" + icnumber + "', '" + firstname + "', '" + lastname + "')");