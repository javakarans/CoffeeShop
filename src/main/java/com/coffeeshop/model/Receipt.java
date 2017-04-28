package com.coffeeshop.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Mohammad on 4/28/2017.
 */
@Entity
@Table
public class Receipt implements Serializable {

    private long receiptId;
    private String trackingNumber;
    private double totalPrice;
    private String date;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true , nullable = false)
    public long getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(long receiptId) {
        this.receiptId = receiptId;
    }

    @Column
    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    @Column
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Column
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
