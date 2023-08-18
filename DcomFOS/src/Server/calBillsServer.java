/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

/**
 *
 * @author ASUS
 */

import Client.CartItem;
import java.rmi.*;
import java.net.*;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import FOSInterface.YWInterface;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class calBillsServer extends UnicastRemoteObject implements YWInterface{
    
    public calBillsServer() throws RemoteException{
        super();
    }
    
    public List<CartItem> cartItemRetrieval(int cartid) throws RemoteException, SQLException{
        // cartItem list
        List<CartItem> cartItemList = new ArrayList<>();

        // connect with Cart DB and display to jtable
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DcomsFOS", "root", "toor");
        
        //if connection successful, this msg will print
        System.out.println("Connected");
        
        //retrieve cart items
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT FOOD.FOODNAME, CARTITEM.QUANTITY, FOOD.PRICE FROM FOOD JOIN CARTITEM ON FOOD.FOODID = CARTITEM.FOODID WHERE CARTITEM.CARTID = " + cartid);
        
        while (rs.next()){
            String foodname = rs.getString("FOODNAME");
            int quantity = rs.getInt("QUANTITY");
            double price = rs.getDouble("PRICE");
            CartItem ci = new CartItem(cartid, foodname, quantity, price);
            cartItemList.add(ci);
        }
        
        //saving the transaction, close
        conn.commit();
        conn.close();
        
        return cartItemList;
    }
    
    
    // function calindifoodPrice
    double totalindifoodprice;
    @Override
    public double calindifoodPrice(double price, int quantity) throws RemoteException{
        return totalindifoodprice = price * quantity;
    }
    
    // function calbeforeTax
    double totalbfTax;
    @Override
    public double calbeforeTax(List<Double> PriceList) throws RemoteException{
        totalbfTax = 0;
        while (!PriceList.isEmpty()) {  
            totalbfTax += PriceList.remove(0);
        }
        return totalbfTax;
    }
    
    // function calserviceTax
    
    @Override
    public double calserviceTax(double totalBFTax) throws RemoteException{
        //totalsvTax = 0;
        double totalsvTax = 0;
        totalsvTax = 10.00 * totalBFTax / 100;
        return totalsvTax;
    }
    
    // function calSST
    @Override
    public double calSST(double totalBFTax) throws RemoteException{
        double totalSST = 0;
        totalSST = 6.0 * totalBFTax / 100; 
        return totalSST;
    }
    
    // function calafterTax
    double totalafTax;
    @Override
    public double calafterTax(double totalBFTax, double totalsvTax, double totalSST) throws RemoteException{
        return totalafTax = totalBFTax + totalsvTax + totalSST;
    }
    
    // function placeOrder
    @Override
    public int placeOrder(int userid, int cartid, int modeid, double totalprice, String status) throws RemoteException, SQLException{
        // initialize orderID
        int orderID = 0;
        
        // connect with Order DB
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DcomsFOS", "root", "toor");
        
        String query = "INSERT INTO ORDERS (UserID, CartID, ModeID, TotalPrice, Status) VALUES ("+ userid + "," + cartid + "," + modeid + "," + totalprice + ",'" + status + "')";
        
        try {
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int rs = stmt.executeUpdate();
        
            // return orderID
            if (rs > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        orderID = generatedKeys.getInt(1);
                        System.out.println("Generated orderID: " + orderID);
                    }
                }
            }
            
            //saving the transaction, close
            conn.commit();
            conn.close();
            
            // successful msg
            System.out.println("Place order successful ^^~");
            System.out.println("Order ID is: " + orderID);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return orderID;
    }

    @Override
    public boolean makePayment(int orderid, double amount, String paymentMethod, Timestamp paymentDT) throws RemoteException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateOrderPaid(int orderid) throws RemoteException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
