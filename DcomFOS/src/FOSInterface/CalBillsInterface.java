/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FOSInterface;

/**
 *
 * @author ASUS
 */

import java.rmi.*;
import java.util.List;

public interface CalBillsInterface extends Remote{
    
    
    public double calindifoodPrice(double price, double quantity) throws RemoteException;
    
    public double calbeforeTax(List<Double> PriceList) throws RemoteException;
    
    public double calserviceTax(double totalBFTax) throws RemoteException;
    
    public double calSST(double totalBFTax) throws RemoteException;
    
    public double calafterTax(double totalbfTax, double totalsvTax, double totalSST) throws RemoteException;
}
