/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.rmi.RemoteException;

/**
 *
 * @author ASUS
 */
public class testservicetax {

    public static void main(String[] args) throws RemoteException {
        calBillsServer obj = new calBillsServer();
        double hi = obj.calserviceTax(33.50);
        System.out.println("test" + hi);

    }
}
