package com.coffeeshop.wrapper;

import com.coffeeshop.model.FoodOrder;
import com.coffeeshop.model.Status;

/**
 * Created by amir on 5/7/2017.
 */
public class FoodOrderWrapper {
    private long foodOrderWrapperId;
    private long foodId;
    private String foodName;
    private int quantity;
    private double price;
    private double totalPrice;

    public FoodOrderWrapper() {
    }

    public FoodOrderWrapper(String foodName, int quantity, double price, double totalPrice) {
        this.foodName = foodName;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;

    }

    public FoodOrder convertToOriginalClass(FoodOrderWrapper foodOrderWrapper)
    {
        FoodOrder foodOrder = new FoodOrder();
        foodOrder.setFoodId(foodOrderWrapper.getFoodId());
        foodOrder.setQuantity(foodOrderWrapper.getQuantity());
        foodOrder.setOrderId(foodOrderWrapper.foodOrderWrapperId);
        foodOrder.setStatus(Status.FOODORDER_NOT_READY);
        return foodOrder;
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

    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }
}
