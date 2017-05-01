package com.coffeeshop.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Mohammad on 4/28/2017.
 */
@Entity
@Table
public class Category implements Serializable{


    private long categoryId;
    private String name;
    private String largeDeviceImageUrl;
    private String smallDeviceImageUrl;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true , nullable = false)
    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLargeDeviceImageUrl() {
        return largeDeviceImageUrl;
    }

    public void setLargeDeviceImageUrl(String largeDeviceImageUrl) {
        this.largeDeviceImageUrl = largeDeviceImageUrl;
    }

    public String getSmallDeviceImageUrl() {
        return smallDeviceImageUrl;
    }

    public void setSmallDeviceImageUrl(String smallDeviceImageUrl) {
        this.smallDeviceImageUrl = smallDeviceImageUrl;
    }
}
