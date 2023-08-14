/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FOSInterface;

import java.rmi.*;
import java.net.*;

// this interface is a Remote interface. Placing this interface in a seperated system
public interface PlaceholderInterface extends Remote { //Must have extends Remote keyword
    public int add(int x, int y) throws RemoteException; //must use Exception Handling, throw into Remote Exception
}