package com.coffeeshop.database;

import com.coffeeshop.model.FoodOrder;

import java.util.List;

/**
 * Created by Mohammad on 4/28/2017.
 */
public interface FoodOrderDao {

    public boolean createAdmin(FoodOrder foodOrder);
    public boolean updateAdmin(FoodOrder foodOrder);
    public boolean deleteAdmin(FoodOrder foodOrder);
}
