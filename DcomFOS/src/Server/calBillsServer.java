/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

/**
 *
 * @author ASUS
 */

import FOSInterface.CalBillsInterface;
import java.rmi.*;
import java.net.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class calBillsServer extends UnicastRemoteObject implements CalBillsInterface{
    
    public calBillsServer() throws RemoteException{
        super();
    }
    
    // function calindifoodPrice
    double totalindifoodprice;
    
    @Override
    public double calindifoodPrice(double price, double quantity) throws RemoteException{
        return totalindifoodprice = price * quantity;
    }
    
    // function calbeforeTax
    double totalbfTax = 0;
    @Override
    public double calbeforeTax(List<Double> PriceList) throws RemoteException{
        while (!PriceList.isEmpty()) {  
            totalbfTax += PriceList.remove(0);
        }
        return totalbfTax;
    }
    
    // function calserviceTax
    double totalsvTax;
    
    @Override
    public double calserviceTax(double totalBFTax) throws RemoteException{
        return totalsvTax = 10/100 * totalbfTax;
    }
    
    // function calSST
    double totalSST;
    @Override
    public double calSST(double totalbfTax) throws RemoteException{
        return totalSST = 6/100 * totalbfTax; 
    }
    
    // function calafterTax
    double totalafTax;
    @Override
    public double calafterTax(double totalbfTax, double totalsvTax, double totalSST) throws RemoteException{
        return totalafTax = totalbfTax + totalsvTax + totalSST;
    }
}
