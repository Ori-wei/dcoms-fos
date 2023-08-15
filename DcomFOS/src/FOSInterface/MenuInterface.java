/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FOSInterface;

import java.rmi.*;
import java.net.*;
import java.sql.SQLException;
import java.util.List;
import Client.Menu;

// this interface is a Remote interface. Placing this interface in a seperated system
public interface MenuInterface extends Remote { //Must have extends Remote keyword
    public void viewMenu() throws RemoteException, SQLException;
    List<Menu> getMenu() throws RemoteException;
}