package com.coffeeshop.database;

import com.coffeeshop.model.Food;

import java.util.List;

/**
 * Created by Mohammad on 4/28/2017.
 */
public interface FoodDao {

    public boolean createFood(Food food);
    public boolean updateFood(Food food);
    public boolean deleteFood(Food food);
    public List<Food> getFoodsBySubCategoryId(long subCategoryId);
    public List<Food> getAllFoods();
}
