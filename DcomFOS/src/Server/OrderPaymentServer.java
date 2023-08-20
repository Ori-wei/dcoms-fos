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
import Client.OrderItem;
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderPaymentServer extends UnicastRemoteObject implements YWInterface{
    
    public OrderPaymentServer() throws RemoteException{
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
    
//    // Without Multithreading
//    // function calserviceTax
//    
//    @Override
//    public double calserviceTax(double totalBFTax) throws RemoteException{
//        //totalsvTax = 0;
//        double totalsvTax = 0;
//        totalsvTax = 10.00 * totalBFTax / 100;
//        return totalsvTax;
//    }
//    
//    // function calSST
//    @Override
//    public double calSST(double totalBFTax) throws RemoteException{
//        double totalSST = 0;
//        totalSST = 6.0 * totalBFTax / 100; 
//        return totalSST;
//    }
    
    // With multithreading
    @Override
    public double calserviceTax(double totalBFTax) throws RemoteException, InterruptedException {
        CalculateTaxesMultithreading.svTaxCalculator svThread = new CalculateTaxesMultithreading.svTaxCalculator();
        svThread.setTotalBFTax(totalBFTax);
        svThread.start();
        svThread.join();
        return svThread.getTotalsvTax();
    }
    
    @Override
    public double calSST(double totalBFTax) throws RemoteException, InterruptedException {
        CalculateTaxesMultithreading.SSTCalculator sstThread = new CalculateTaxesMultithreading.SSTCalculator();
        sstThread.setTotalBFTax(totalBFTax);
        sstThread.start();
        sstThread.join();
        return sstThread.getTotalSST();
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
        
        moveCartItemToOrderItem(cartid, orderID, totalprice);
        
        return orderID;
    }
    
    public void moveCartItemToOrderItem(int cartID, int orderID, double price) throws RemoteException, SQLException{
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DcomsFOS", "root", "toor");
        Statement stmt = conn.createStatement();
        List<CartItem> cartItemList = new ArrayList<>();
        String query = "SELECT CartItem.CARTITEMID, CartItem.FOODID, CartItem.QUANTITY, Food.PRICE FROM CARTITEM JOIN Food ON Food.FoodID = CartItem.FoodID WHERE CARTID = " + cartID;
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next())
        {
            CartItem cartItem = new CartItem(rs.getInt("CARTITEMID"), rs.getInt("FOODID"), rs.getInt("QUANTITY"), rs.getDouble("PRICE"));
            cartItemList.add(cartItem);
        }
        
        // make a list to save foodid, quantity
        // from this list, insert into orderitem
        for (CartItem cartItem2 : cartItemList) 
        {
            String query1 = "INSERT INTO ORDERITEM (OrderID, FoodID, Quantity, Price) VALUES (" 
                + orderID + "," + cartItem2.getFoodId() + "," + cartItem2.getQuantity() + "," + cartItem2.getPrice() + ")";
            int rs1 = stmt.executeUpdate(query1);
        }
        //delete the list into 
        for (CartItem cartItem3 : cartItemList) 
        {
            String query2 = "DELETE FROM CARTITEM WHERE CartItemID = " + cartItem3.getCartItemId();
            int rs2 = stmt.executeUpdate(query2);
        }
                
        //saving the transaction, close
        conn.commit();
        conn.close();
    }

    @Override
    public boolean makePayment(int orderid, double amount, String paymentMethod, Timestamp paymentDT) throws RemoteException, SQLException{
        // function success indicator
        // fail = 0, success = 1
        boolean makePaymentSuccess = false;
        
        // connect to payment db
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DcomsFOS", "root", "toor");
        System.out.println(conn);
        Statement stmt = conn.createStatement();
        String query = "INSERT INTO PAYMENT (OrderID, Amount, PaymentMethod, PaymentDateTime) VALUES (" + orderid + ", " + amount + ", '" + paymentMethod + "', '" + paymentDT + "')";
        int rs = stmt.executeUpdate(query);
        
        //saving the transaction, close
        conn.commit();
        conn.close();
        
        // successful msg
        System.out.println("Make payment successful ^^~");
        
        // return boolean
        return makePaymentSuccess = true;
    
    }

    @Override
    public boolean updateOrderPaid(int orderid) throws RemoteException, SQLException{
        // function success indicator
        // fail = 0, success = 1
        boolean updateOrderStatus = false;
        
        // establish connection
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DcomsFOS", "root", "toor");
        
        // update order db
        Statement stmt = conn.createStatement();
        String query = "UPDATE ORDERS SET STATUS = 'PAID' WHERE ORDERID = " + orderid;
        int rs = stmt.executeUpdate(query);
        
        //saving the transaction, close
        conn.commit();
        conn.close();
        
        return updateOrderStatus = true;
    }
}
