/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registry;

import FOSInterface.MenuInterface;
import Server.MenuFrameServer;
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
            
            MenuInterface menuService = new MenuFrameServer();
            Registry reg = LocateRegistry.createRegistry(1072);
            reg.rebind("MenuInterface", menuService);
            System.out.println("Server is ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
