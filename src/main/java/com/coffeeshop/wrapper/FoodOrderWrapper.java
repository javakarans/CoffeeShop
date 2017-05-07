package com.coffeeshop.wrapper;

/**
 * Created by amir on 5/7/2017.
 */
public class FoodOrderWrapper {
    private long foodOrderWrapperId;
    private String foodName;
    private int qnt;
    private double price;
    private double totalPrice;


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

    public int getQnt() {
        return qnt;
    }

    public void setQnt(int qnt) {
        this.qnt = qnt;
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
