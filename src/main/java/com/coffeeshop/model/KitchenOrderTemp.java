package com.coffeeshop.model;

import javax.persistence.*;

/**
 * Created by Amirhossein on 5/4/2017.
 */
@Table
@Entity
public class KitchenOrderTemp {
    private long kitchenId;
    private long kitchenOrderTempId;
    private long trackNumber;
    private long foodOrderId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true , nullable = false)
    public long getKitchenOrderId() {
        return kitchenOrderTempId;
    }

    public void setKitchenOrderId(long kitchenOrderId) {
        this.kitchenOrderTempId = kitchenOrderId;
    }

    @Column
    public long getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(long trackNumber) {
        this.trackNumber = trackNumber;
    }

    @Column
    public long getFoodOrderId() {
        return foodOrderId;
    }

    public void setFoodOrderId(long foodOrderId) {
        this.foodOrderId = foodOrderId;
    }

    public long getKitchenId() {
        return kitchenId;
    }

    public void setKitchenId(long kitchenId) {
        this.kitchenId = kitchenId;
    }
}
