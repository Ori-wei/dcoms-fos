/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registry;

import FOSInterface.MenuInterface;
import FOSInterface.RegisterInterface;
import Server.CheckoutServer;
import Server.MenuFrameServer;
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
            Registry reg = LocateRegistry.createRegistry(1070);
            //Kong Hao Ming server
            MenuInterface menuService = new MenuFrameServer();
            reg.rebind("MenuInterface", menuService);
            System.out.println("HM Server is ready.");
            
            //Loh Yuen Wei server
            CheckoutServer checkout = new CheckoutServer();
            reg.rebind("Checkout", checkout);        
            System.out.println("YW Server is ready.");
            
            //Malcolm Heng server
            RegisterInterface registerService = new RegisterServer();
            reg.rebind("RegisterInterface", registerService);   
            System.out.println("MH Server is ready!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}