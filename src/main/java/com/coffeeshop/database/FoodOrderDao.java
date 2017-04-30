package com.coffeeshop.database;

import com.coffeeshop.model.FoodOrder;

import java.util.List;

/**
 * Created by Mohammad on 4/28/2017.
 */
public interface FoodOrderDao {

    public boolean createFoodOrder(FoodOrder foodOrder);
    public boolean updateFoodOrder(FoodOrder foodOrder);
    public boolean deleteFoodOrder(FoodOrder foodOrder);
}
