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
/**
 *
 * @author User
 */
public interface RegisterInterface extends Remote {
    boolean registerAccount(String username, String password, String icnumber, String firstname, String lastname) throws RemoteException;
    boolean loginAccount(String username, String password) throws RemoteException;
    int getUserID(String username) throws RemoteException;
}
