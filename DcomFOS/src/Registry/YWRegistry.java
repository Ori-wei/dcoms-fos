/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registry;

/**
 *
 * @author ASUS
 */

import Client.PlaceOrderClient;
import Server.PlaceOrderServer;
import java.rmi.*;
import java.net.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import FOSInterface.YWInterface;
import Server.MakePaymentServer;

public class YWRegistry {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException, NotBoundException, SQLException{
        Registry reg = LocateRegistry.createRegistry(1045);
        
        // Calculate Bill Server
        PlaceOrderServer calbillsserver = new PlaceOrderServer();
        reg.rebind("Checkout", calbillsserver);
        
        // Make Payment Server
        MakePaymentServer makepaymentserver = new MakePaymentServer();
        reg.rebind("Payment", makepaymentserver);
        
        System.out.println("Registry is ready!");
        
    }
}
