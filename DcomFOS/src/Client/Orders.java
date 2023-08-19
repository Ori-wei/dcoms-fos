/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class Orders implements Serializable {
    int orderID;
    double total;
    String status;
    int modeID;

    public Orders(double total, String status, int modeID) {
        this.total=total;
        this.status=status;
        this.modeID=modeID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double price) {
        this.total = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getModeID() {
        return modeID;
    }

    public void setModeID(int modeID) {
        this.modeID = modeID;
    }
    
    
    
}
