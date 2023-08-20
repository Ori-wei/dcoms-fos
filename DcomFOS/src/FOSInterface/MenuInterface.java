/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FOSInterface;

import Client.CartItem;
import java.rmi.*;
import java.net.*;
import java.sql.SQLException;
import java.util.List;
import Client.Menu;
import Client.OrderItem;
import Client.Orders;

// this interface is a Remote interface. Placing this interface in a seperated system
public interface MenuInterface extends Remote { //Must have extends Remote keyword
    public void viewMenu() throws RemoteException, SQLException;
    List<Menu> getMenu() throws RemoteException;
    List<Menu> getFoodDetail(int foodID) throws RemoteException, SQLException;
    List<CartItem> getCartItem(int foodID, int userID) throws RemoteException, SQLException;
    public int updateCartItem(int foodID, int userID, int quantity) throws RemoteException, SQLException;
    List<CartItem> getCartItem(int userID) throws RemoteException, SQLException;
    public int deleteCartItem(int foodID, int userID) throws RemoteException, SQLException;
    List<OrderItem> getOrderItem(int userID, int orderID) throws RemoteException, SQLException;
    List <Orders> getOrderDetails(int orderID) throws RemoteException, SQLException;
    List <Orders> getOrder(int userID) throws RemoteException, SQLException;
    public int getCartIdOnly(int userID) throws RemoteException, SQLException;
}