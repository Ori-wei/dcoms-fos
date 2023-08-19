/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Client.CartItem;
import Client.Menu;
import Client.OrderItem;
import Client.Orders;
import FOSInterface.MenuInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;


/**
 *
 * @author user
 */
public class MenuFrameServer extends UnicastRemoteObject implements MenuInterface{
    public MenuFrameServer() throws RemoteException {
        super();
    }
    @Override
    public List<Menu> getMenu() throws RemoteException {
        List<Menu> menuList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DcomsFOS","root","toor");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM food")) {

            while (rs.next()) {
                Menu menu = new Menu(rs.getInt("FoodID"), rs.getString("FoodName"), rs.getDouble("Price"));
                menuList.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }

    @Override
    public void viewMenu() throws RemoteException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Menu> getFoodDetail(int foodID) throws RemoteException, SQLException {
        List<Menu> menuList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DcomsFOS","root","toor");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM food where FoodID = " + foodID)) {

            while (rs.next()) {
                Menu menu = new Menu(rs.getInt("FoodID"), rs.getString("FoodName"), rs.getDouble("Price"), rs.getString("Description"), rs.getString("Category"));
                menuList.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(menuList);
        return menuList;
    }

    @Override
    public List<CartItem> getCartItem(int foodID, int userID) throws RemoteException, SQLException {
        int cartID = 0;
        List<CartItem> cartItemList = new ArrayList<>();
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DcomsFOS","root","toor");
        String checkCartOwnerID = "select * from Cart where UserID = " + userID;
        Statement stm1 = conn.createStatement();
        ResultSet rs1 = stm1.executeQuery(checkCartOwnerID);
        if (rs1.next())
        {
            System.out.println(rs1.getInt("CartID"));
            cartID = rs1.getInt("CartID");
            System.out.println("CartID gotten is: " + cartID);
        }
        String checkIfCartItemExistInCart = "select * from CartItem where FoodID = " + foodID + " and CartID = " + cartID;
        Statement stm2 = conn.createStatement();
        ResultSet rs2 = stm2.executeQuery(checkIfCartItemExistInCart);
        while (rs2.next()) {
                CartItem cartItem = new CartItem(rs2.getInt("CartItemID"), rs2.getInt("CartID"), rs2.getInt("FoodID"), rs2.getInt("Quantity"));
                cartItemList.add(cartItem);
            }
        return cartItemList;      
    }

    @Override
    public int updateCartItem(int foodID, int userID, int quantity) throws RemoteException, SQLException {
        int cartID = 0;
        int response = 0;
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DcomsFOS","root","toor");
        String checkCartOwnerID = "select * from Cart where UserID = " + userID;
        Statement stm1 = conn.createStatement();
        ResultSet rs1 = stm1.executeQuery(checkCartOwnerID);
        if (rs1.next())
        {
            System.out.println(rs1.getInt("CartID"));
            cartID = rs1.getInt("CartID");
            System.out.println("CartID gotten is: " + cartID);
        }
        System.out.println("FoodID gotten is: " + foodID);
        String checkIfCartItemExistInCart = "select * from CartItem where FoodID = " + foodID + " and CartID = " + cartID;
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
            String updateQty = "INSERT INTO CartItem (CartID, FoodID, Quantity) VALUES ("+ cartID + ", " + foodID +", " + quantity + ")"; 
            Statement stm4 = conn.createStatement();
            int rs4 = stm4.executeUpdate(updateQty);
            System.out.println(rs4);
            System.out.println("created new cartitem");
            response = 1;
        }
        else if(nrow>0)
        {
            String updateCartItem = "UPDATE CartItem SET Quantity = " + quantity + " WHERE CartID = " + cartID + " AND FoodID = " + foodID +"";  
            Statement stm5 = conn.createStatement();
            int rs5 = stm5.executeUpdate(updateCartItem);
            System.out.println(rs5);
            System.out.println("Updated existing cartitem");
            response = 1;
        }      
        else
        {
            System.out.println("Error in Server Update CartItem");
            response = 0;
        }
        System.out.println("Response is: " + response);
        return response;
    }

    @Override
    public List<CartItem> getCartItem(int userID) throws RemoteException, SQLException {
        int cartID = 0;
        List<CartItem> cartItemList = new ArrayList<>();
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DcomsFOS","root","toor");
        String checkCartOwnerID = "select * from Cart where UserID = " + userID;
        Statement stm1 = conn.createStatement();
        ResultSet rs1 = stm1.executeQuery(checkCartOwnerID);
        if (rs1.next())
        {
            System.out.println(rs1.getInt("CartID"));
            cartID = rs1.getInt("CartID");
            System.out.println("CartID gotten is: " + cartID);
        }
        String checkIfCartItemExistInCart = "SELECT CartItem.CartItemID, CartItem.CartID, CartItem.FoodID, CartItem.Quantity, Food.FoodName, Food.Price FROM CartItem JOIN Food ON Food.FoodID = CartItem.FoodID WHERE CartItem.CartID = " + cartID;
        Statement stm2 = conn.createStatement();
        ResultSet rs2 = stm2.executeQuery(checkIfCartItemExistInCart);
        while (rs2.next()) {
                CartItem cartItem = new CartItem(rs2.getInt("CartItemID"), rs2.getInt("CartID"), rs2.getInt("FoodID"), rs2.getInt("Quantity"), rs2.getString("FoodName"), rs2.getDouble("Price"));
                cartItemList.add(cartItem);
            }
        return cartItemList;   
    }

    @Override
    public int deleteCartItem(int foodID, int userID) throws RemoteException, SQLException {
        int cartID = 0;
        int response = 0;
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DcomsFOS","root","toor");
        String checkCartOwnerID = "select * from Cart where UserID = " + userID;
        Statement stm1 = conn.createStatement();
        ResultSet rs1 = stm1.executeQuery(checkCartOwnerID);
        if (rs1.next())
        {
            System.out.println(rs1.getInt("CartID"));
            cartID = rs1.getInt("CartID");
            System.out.println("CartID gotten is: " + cartID);
        }
        System.out.println("FoodID gotten is: " + foodID);
        String checkIfCartItemExistInCart = "select * from CartItem where FoodID = " + foodID + " and CartID = " + cartID;
        Statement stm2 = conn.createStatement();
        ResultSet rs2 = stm2.executeQuery(checkIfCartItemExistInCart);
        int nrow =0 ;
        if(rs2.next())
        {
            nrow = rs2.getInt(1);
        }     
        if(nrow>0)
        {
            System.out.println("Cart Item found. Deleting...");
//            String nextCartItemIdQuery = "select count(*) from CartItem";
//            Statement stm3 = conn.createStatement();
//            ResultSet rs3 = stm3.executeQuery(nextCartItemIdQuery);
//            int nextID = 0; 
//            if(rs3.next())
//            {
//                nextID = rs3.getInt(1)+1;
//            }
//            System.out.println("Next CartItemId is: " + nextID);        
            String deleteCartItem = "DELETE from CartItem where CartID = " + cartID + " and foodID = " + foodID;          
            Statement stm4 = conn.createStatement();
            int rs4 = stm4.executeUpdate(deleteCartItem);
            System.out.println(rs4);
            System.out.println("deleted cart item");
            response = 1;
        }
        else if(nrow==0)
        {
            System.out.println("No records found. Delete fail.");
            response = 2;
        }      
        else
        {
            System.out.println("Error in Server Delete CartItem");
            response = 0;
        }
        System.out.println("Response is: " + response);
        return response;
    }

    @Override
    public List<OrderItem> getOrderItem(int userID, int orderID) throws RemoteException, SQLException {
        int cartID = 0;
        List<OrderItem> orderItemList = new ArrayList<>();
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DcomsFOS","root","toor");
//        String checkCartOwnerID = "select * from Cart where UserID = " + userID;
//        Statement stm1 = conn.createStatement();
//        ResultSet rs1 = stm1.executeQuery(checkCartOwnerID);
//        if (rs1.next())
//        {
//            System.out.println(rs1.getInt("CartID"));
//            cartID = rs1.getInt("CartID");
//            System.out.println("CartID gotten is: " + cartID);
//        }
        String getOrderItem = "SELECT OrderItem.OrderItemID, OrderItem.OrderID, OrderItem.FoodID, OrderItem.Quantity, OrderItem.Price, Food.FoodName FROM OrderItem JOIN Food ON Food.FoodID = OrderItem.FoodID WHERE OrderItem.OrderID = " + orderID;
        Statement stm2 = conn.createStatement();
        ResultSet rs2 = stm2.executeQuery(getOrderItem);
        while (rs2.next()) {
                OrderItem orderItem = new OrderItem(rs2.getInt("OrderItemID"), rs2.getInt("OrderID"), rs2.getInt("FoodID"), rs2.getInt("Quantity"), rs2.getString("FoodName"), rs2.getDouble("Price"));
                orderItemList.add(orderItem);
            }
        return orderItemList;   
    }

    @Override
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
