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

import Client.CalBillsClient;
import Server.calBillsServer;
import FOSInterface.CalBillsInterface;
import java.rmi.*;
import java.net.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class CalBillsRegistry {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException, NotBoundException, SQLException{
        Registry reg = LocateRegistry.createRegistry(1044);
        calBillsServer calbillsserver = new calBillsServer();
        CalBillsInterface stub = (CalBillsInterface)UnicastRemoteObject.exportObject(calbillsserver, 0);
        reg.bind("getMenu", stub);
    }
}
