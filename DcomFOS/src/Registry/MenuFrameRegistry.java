/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registry;

import FOSInterface.MenuInterface;
import FOSInterface.RegisterInterface;
import Server.MakePaymentServer;
import Server.MenuFrameServer;
import Server.PlaceOrderServer;
import Server.RegisterServer;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author user
 */
public class MenuFrameRegistry {
    public static void main(String[] args) throws RemoteException {
        try {
//            MenuFrameServer obj = new MenuFrameServer();
//            Registry reg = LocateRegistry.createRegistry(1072);
//            reg.rebind("MenuInterface", new MenuFrameServer());
//            System.out.println("Service is ready.");
            
            Registry reg = LocateRegistry.createRegistry(1072);
            //HM server
            MenuInterface menuService = new MenuFrameServer();
            reg.rebind("MenuInterface", menuService);
            System.out.println("HM Server is ready.");
            //YW server
            // Calculate Bill Server
            PlaceOrderServer calbillsserver = new PlaceOrderServer();
            reg.rebind("Checkout", calbillsserver);
            // Make Payment Server
            MakePaymentServer makepaymentserver = new MakePaymentServer();
            reg.rebind("Payment", makepaymentserver);
            System.out.println("YW Server is ready.");
            //MH server
            RegisterInterface registerService = new RegisterServer();
            reg.rebind("RegisterInterface", registerService);   
            System.out.println("MH Server is ready!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
