/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import FOSInterface.MenuInterface;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public class MenuClient {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException, SQLException{
        MenuInterface obj = (MenuInterface)Naming.lookup("rmi://localhost:1070/viewMenu");
        obj.viewMenu();
    }
}
