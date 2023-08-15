/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Client.FoodDetailBE;
import FOSInterface.MenuInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author user
 */

public class MenuServer extends UnicastRemoteObject implements MenuInterface{ //must have extends UnicastRemoteObject and implements the Interface
    public MenuServer() throws RemoteException //must have this declaration
    {
        super(); //must have this constructor
    }
    @Override
    public void viewMenu() throws RemoteException, SQLException {
        // Your existing code to connect to the database and fetch the menu
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DcomsFOS","root","toor"); //the line to connect to database. Parameter is "database url", "username", "password"
        System.out.println("Connected"); //if see this message, databse is connected
        Statement stm = conn.createStatement(); //Statement class is used for java to identify the sql statement
        ResultSet resultSet = stm.executeQuery("select * from Food");
        while (resultSet.next()) {
               int foodID = resultSet.getInt("FoodID");
               String foodName = resultSet.getString("FoodName");
               double price = resultSet.getDouble("Price");
               String description = resultSet.getString("Description");
               String category = resultSet.getString("Category");

               System.out.println("FoodID: " + foodID +
                                  ", FoodName: " + foodName +
                                  ", Price: " + price +
                                  ", Description: " + description +
                                  ", Category: " + category);
           }
        resultSet.close();
        stm.close();
        System.out.println("Choose a selection: ");
        Scanner sc = new Scanner(System.in);
        int selection = sc.nextInt();
        //System.out.println("End of MenuServer");
        new FoodDetailBE(selection);
        conn.close(); 
    }
}