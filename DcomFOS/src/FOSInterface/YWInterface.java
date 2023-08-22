/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FOSInterface;

/**
 *
 * @author ASUS
 */

import DatabaseObject.CartItem;
import java.rmi.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface YWInterface extends Remote{
    
    // YW - Calculate Bills Function
    public List<CartItem> cartItemRetrieval(int cartid) throws RemoteException, SQLException;
    
    public double calindifoodPrice(double price, int quantity) throws RemoteException;
    
    public double calbeforeTax(List<Double> PriceList) throws RemoteException;
    
    public double calserviceTax(double totalBFTax) throws RemoteException, InterruptedException;
    
    public double calSST(double totalBFTax) throws RemoteException, InterruptedException;
    
    public double calafterTax(double totalBFTax, double totalsvTax, double totalSST) throws RemoteException;
    
    public int placeOrder(int userid, int cartid, int modeid, double totalprice, String status) throws RemoteException, SQLException;
    
    public void moveCartItemToOrderItem(int cartID, int orderID, double price) throws RemoteException, SQLException;
    
    // YW - Make Payment
    public boolean makePayment(int orderid, double amount, String paymentMethod, Timestamp paymentDT) throws RemoteException, SQLException;
    
    public boolean updateOrderPaid(int orderid) throws RemoteException, SQLException;
    
}
