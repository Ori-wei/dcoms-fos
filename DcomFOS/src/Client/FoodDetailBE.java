/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

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
public class FoodDetailBE {
    public FoodDetailBE(int selection) throws SQLException
    {
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DcomsFOS","root","toor"); //the line to connect to database. Parameter is "database url", "username", "password"
        System.out.println("Connected"); //if see this message, databse is connected
        Statement stm = conn.createStatement(); //Statement class is used for java to identify the sql statement
        ResultSet resultSet = stm.executeQuery("select * from Food where FoodId = " + selection);
        int foodID;
        String foodName = null;
        double price;
        String description;
        String category;
        while (resultSet.next()) {
               foodID = resultSet.getInt("FoodID");
               foodName = resultSet.getString("FoodName");
               price = resultSet.getDouble("Price");
               description = resultSet.getString("Description");
               category = resultSet.getString("Category");

               System.out.println("FoodID: " + foodID +
                                  ", FoodName: " + foodName +
                                  ", Price: " + price +
                                  ", Description: " + description +
                                  ", Category: " + category);
           }
        resultSet.close();
        stm.close();
        System.out.println("How many would you like to order");
        Scanner sc = new Scanner(System.in);
        int quantity = sc.nextInt();
        
        String checkIfCartItemExistInCart = "select * from CartItem where FoodID = " + selection + " and CartID = 1";
        Statement stm2 = conn.createStatement();
        ResultSet rs2 = stm2.executeQuery(checkIfCartItemExistInCart);
        int nrow =0 ;
        if(rs2.next())
        {
            nrow = rs2.getInt(1);
        }     
        if(nrow==0)
        {
            System.out.println("cartitem dont exist. insert new");
            String nextCartItemIdQuery = "select count(*) from CartItem";
            Statement stm3 = conn.createStatement();
            ResultSet rs3 = stm3.executeQuery(nextCartItemIdQuery);
            int nextID = 0; 
            if(rs3.next())
            {
                nextID = rs3.getInt(1)+1;
            }
            System.out.println("Next CartItemId is: " + nextID);        
            String updateQty = "INSERT INTO CartItem (CartID, FoodID, Quantity) VALUES (1," + selection +", " + quantity + ")"; 
            Statement stm4 = conn.createStatement();
            int rs4 = stm4.executeUpdate(updateQty);
            System.out.println(rs4);
            System.out.println("created new cartitem");
        }
        else
        {
            String updateCartItem = "UPDATE CartItem SET Quantity = " + quantity + " WHERE CartID = 1 AND FoodID = " + selection +"";  
            Statement stm5 = conn.createStatement();
            int rs5 = stm5.executeUpdate(updateCartItem);
            System.out.println(rs5);
            System.out.println("Updated existing cartitem");
        }      
        conn.close(); 
    }
}
