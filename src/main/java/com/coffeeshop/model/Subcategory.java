package com.coffeeshop.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Mohammad on 4/28/2017.
 */
@Entity
@Table
public class Subcategory implements Serializable{

    private long subCategoryId;
    private long categoryId;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true , nullable = false)
    public long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    @Column
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
}
