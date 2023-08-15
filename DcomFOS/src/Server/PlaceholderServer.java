/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import FOSInterface.PlaceholderInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author user
 */

public class PlaceholderServer extends UnicastRemoteObject implements PlaceholderInterface{ //must have extends UnicastRemoteObject and implements the Interface
    public PlaceholderServer() throws RemoteException //must have this declaration
    {
        super(); //must have this constructor
    }
    //method definition for function add
//    @Override
//    public int add(int x, int y) throws RemoteException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}