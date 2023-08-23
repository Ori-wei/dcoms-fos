/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import FOSInterface.CheckoutInterface;

/**
 *
 * @author ASUS
 */
public class CalculateTaxesMultithreading {
    
    public static class svTaxCalculator extends Thread{
        private double totalBFTax;
        private double totalsvTax;
        
        public svTaxCalculator(){
        }

        public void setTotalBFTax(double totalBFTax) {
            this.totalBFTax = totalBFTax;
        }
        
        public void run(){
            totalsvTax = 10.0 * totalBFTax / 100.0;
        }

        public double getTotalsvTax() {
            return totalsvTax;
        }
    }
    
    public static class SSTCalculator extends Thread{
        private double totalBFTax;
        private double totalSST;
        
        public SSTCalculator(){
        }

        public void setTotalBFTax(double totalBFTax) {
            this.totalBFTax = totalBFTax;
        }
        
        public void run(){
            totalSST = 6.0 * totalBFTax / 100;
        }

        public double getTotalSST() {
            return totalSST;
        }
    }
}
