/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registry;

import Server.MenuServer;
import FOSInterface.MenuInterface;
import java.rmi.*;
import java.net.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class MenuRegistry {
    public static void main(String[] args) throws RemoteException 
    {
//            MenuRegistry obj = new MenuRegistry();
//            MenuInterface stub = (MenuInterface) UnicastRemoteObject.exportObject((Remote) obj, 0);
                    
            Registry reg = LocateRegistry.createRegistry(1070);
            reg.rebind("viewMenu", new MenuServer());
    }
}
