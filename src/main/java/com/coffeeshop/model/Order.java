package com.coffeeshop.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Amirhossein on 4/28/2017.
 */
@Entity
@Table
public class Order implements Serializable{

    private long orderId;
    private int trackingNumber;
    private String status;
    private double totalPrice;
    private Date date;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true , nullable = false)
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    @Column
    public int getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(int trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    @Column
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Column
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
