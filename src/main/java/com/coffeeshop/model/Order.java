package com.coffeeshop.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Amirhossein on 4/28/2017.
 */
@Entity
@Table
public class Order implements Serializable{

    private long orderId;
    private long recieptId;
    private String status;

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
    public long getRecieptId() {
        return recieptId;
    }

    public void setRecieptId(long recieptId) {
        this.recieptId = recieptId;
    }

    @Column
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
