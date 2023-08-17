/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class app {

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException, SQLException
    {
        System.out.println("hello world");
//        try {
//            new CalBillsClient();
//        } catch (SQLException ex) {
//            Logger.getLogger(app.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        //HM
        //MenuFrameClient.createAndShowGUI();
        
        //YW
        CalBillsClient.createAndShowGUI(1);
    }
}
