package com.coffeeshop.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Amirhossein on 4/27/2017.
 */
@Entity
@Table
public class Food implements Serializable{

    private long foodId;
    private long subCategoryId;
    private String name;
    private double price;
    private String immageUrl;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true , nullable = false)
    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }

    @Column
    public long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Column
    public String getImmageUrl() {
        return immageUrl;
    }

    public void setImmageUrl(String immageUrl) {
        this.immageUrl = immageUrl;
    }
}
