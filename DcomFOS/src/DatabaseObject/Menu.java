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
public class Menu implements Serializable{
    private int foodId;
    private String foodName; 
    private double price;
    private String description;
    private String category;

    public Menu(int foodID, String foodName, double foodPrice) {
        this.foodId = foodID;
        this.foodName=foodName;
        this.price=foodPrice;
    }
    public Menu(int foodID, String foodName, double foodPrice, String description, String category) {
        this.foodId = foodID;
        this.foodName=foodName;
        this.price=foodPrice;
        this.description=description;
        this.category=category;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
}
