/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Client.CartItem;
import FOSInterface.YWInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class MakePaymentServer extends UnicastRemoteObject implements YWInterface{
    
    public MakePaymentServer() throws RemoteException{
        super();
    }
    
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

    @Override
    public List<CartItem> cartItemRetrieval(int cartid) throws RemoteException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double calindifoodPrice(double price, int quantity) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double calbeforeTax(List<Double> PriceList) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double calserviceTax(double totalBFTax) throws RemoteException, InterruptedException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double calSST(double totalBFTax) throws RemoteException, InterruptedException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double calafterTax(double totalBFTax, double totalsvTax, double totalSST) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int placeOrder(int userid, int cartid, int modeid, double totalprice, String status) throws RemoteException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void moveCartItemToOrderItem(int cartID, int orderID, double price) throws RemoteException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
