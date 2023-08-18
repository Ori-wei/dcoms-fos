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
public class CartItem implements Serializable{
    private int cartItemId;
    private int cartId;
    private int foodId;
    private int quantity;
    private String foodname;
    private double price;

    public CartItem(int cartItemId, int cartId, int foodId, int quantity) {
        this.cartItemId = cartItemId;
        this.cartId = cartId;
        this.foodId = foodId;
        this.quantity = quantity;
    }
    
    public CartItem(String foodname, int quantity, double price) {
        this.foodname = foodname;
        this.quantity = quantity;
        this.price = price;
    }

    public CartItem(int cartId, String foodname, int quantity, double price) {
        this.cartId = cartId;
        this.foodname = foodname;
        this.quantity = quantity;
        this.price = price;
    }
    
    public CartItem(int cartItemId, int cartId, int foodId, int quantity, String foodName, double price) {
       this.cartItemId = cartItemId;
       this.cartId = cartId;
       this.foodId = foodId;
       this.quantity = quantity;
       this.foodname = foodName;
       this.price = price;
    }
    
    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
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