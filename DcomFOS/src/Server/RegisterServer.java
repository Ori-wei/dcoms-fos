/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import FOSInterface.RegisterInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
                
/**
 *
 * @author User
 */
public class RegisterServer extends UnicastRemoteObject implements RegisterInterface{
    public RegisterServer() throws RemoteException{
        super();
    }
    @Override
public boolean registerAccount(
    String username, String password, String icnumber,
    String firstname, String lastname) throws RemoteException {
    
    Connection conn = null;
    Statement stmt = null;
    try {
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DcomsFOS", "root", "toor");
        stmt = conn.createStatement();
        stmt.executeUpdate("INSERT INTO USERS (USERNAME, PASSWORD, ACTOR, ICPASSPORTNUMBER, FIRSTNAME, LASTNAME) VALUES ('" + username + "', '" + password + "', 'customer', '" + icnumber + "', '" + firstname + "', '" + lastname + "')");
        
        // You might want to add additional checks or return values based on success or failure
        return true; // Registration successful
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // Registration failed
    } finally {
        // Close the resources in the finally block
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
}

}
//stm.executeUpdate("INSERT INTO USERS (USERNAME, PASSWORD, ACTOR, ICPASSPORTNUMBER, FIRSTNAME, LASTNAME) VALUES ('" + username + "', '" + password + "', 'customer', '" + icnumber + "', '" + firstname + "', '" + lastname + "')");