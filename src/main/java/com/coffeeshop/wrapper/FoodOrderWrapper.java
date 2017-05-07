package com.coffeeshop.wrapper;

/**
 * Created by amir on 5/7/2017.
 */
public class FoodOrderWrapper {
    private long foodOrderWrapperId;
    private String foodName;
    private int quantity;
    private double price;
    private double totalPrice;

public FoodOrderWrapper(){}
public FoodOrderWrapper(String foodName,int quantity,double price,double totalPrice){
this.foodName=foodName;
this.quantity=quantity;
this.price=price;
this.totalPrice=totalPrice;

}

    public long getFoodOrderWrapperId() {
        return foodOrderWrapperId;
    }

    public void setFoodOrderWrapperId(long foodOrderWrapperId) {
        this.foodOrderWrapperId = foodOrderWrapperId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
