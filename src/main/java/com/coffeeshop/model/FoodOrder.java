package com.coffeeshop.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Amirhossein on 4/28/2017.
 */
@Entity
@Table
public class FoodOrder implements Serializable{

    private long foodOrderId;
    private long foodId;
    private long orderId;
    private int quantity;
    private double totalPrice;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true , nullable = false)
    public long getFoodOrderId() {
        return foodOrderId;
    }

    public void setFoodOrderId(long foodOrderId) {
        this.foodOrderId = foodOrderId;
    }

    @Column
    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }

    @Column
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    @Column
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Column
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
