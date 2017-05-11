package com.coffeeshop.wrapper;

import com.coffeeshop.model.FoodOrder;
import com.coffeeshop.model.Status;

import javax.faces.event.ValueChangeEvent;

/**
 * Created by amir on 5/7/2017.
 */
public class FoodOrderWrapper {

    private long foodOrderWrapperId;
    private long foodId;
    private long orderDetailId;
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

    public double calTotalPrice(){
        totalPrice=price*quantity;
        System.out.println(totalPrice);
        return totalPrice;
    }

    public FoodOrder convertToOriginalClass()
    {
        FoodOrder foodOrder = new FoodOrder();
        foodOrder.setFoodId(this.foodId);
        foodOrder.setQuantity(this.quantity);
        foodOrder.setOrderId(this.foodOrderWrapperId);
        foodOrder.setStatus(Status.FOODORDER_NOT_READY);
        foodOrder.setOrderId(this.orderDetailId);
        foodOrder.setTotalPrice(calTotalPrice());
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

    public long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }
}
