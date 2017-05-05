package com.coffeeshop.wrapper;

import com.coffeeshop.database.FoodDaoImp;
import com.coffeeshop.model.Food;
import com.coffeeshop.model.FoodOrder;

/**
 * Created by Amirhossein on 5/4/2017.
 */
public class FoodOrderWrapper {

    private long foodOrderId;
    private long foodId;
    private long orderId;
    private int quantity;
    private double totalPrice;
    private String status;
    private String foodName;

    public FoodOrderWrapper(FoodOrder foodOrder){
        this.foodOrderId = foodOrder.getFoodOrderId();
        this.foodId = foodOrder.getFoodId();
        this.orderId = foodOrder.getOrderId();
        this.quantity = foodOrder.getQuantity();
        this.totalPrice = foodOrder.getTotalPrice();
        this.status = foodOrder.getStatus();
        fill();
    }

    public void fill(){
        Food food = new Food();
        FoodDaoImp foodDaoImp = new FoodDaoImp();
        food = foodDaoImp.getFoodByFoodId(this.foodId);
        this.foodName = food.getName();
    }

    public long getFoodOrderId() {
        return foodOrderId;
    }

    public void setFoodOrderId(long foodOrderId) {
        this.foodOrderId = foodOrderId;
    }

    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

}
