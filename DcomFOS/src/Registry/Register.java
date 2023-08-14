/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registry;


import java.net.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
public class Register {
    public static void main (String[] args) throws RemoteException
    {
        //pre-defined line 16-17 except the parameters.
        Registry reg = LocateRegistry.createRegistry(1069);
        //reg.rebind("add", new extint()); //add is the method in step 1 //extint is the object class in step 3
    }
}
