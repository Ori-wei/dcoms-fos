/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseObject;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class OrderItem implements Serializable {
    private int orderItemId;
    private int orderId;
    private int foodId;
    private int quantity;
    private String foodname;
    private double price;
    
    public OrderItem(int orderItemId, int orderId, int foodId, int quantity, String foodName, double price) {
       this.orderItemId = orderItemId;
       this.orderId = orderId;
       this.foodId = foodId;
       this.quantity = quantity;
       this.foodname = foodName;
       this.price = price;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
    
}
