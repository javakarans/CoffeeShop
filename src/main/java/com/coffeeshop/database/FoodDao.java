package com.coffeeshop.database;

import com.coffeeshop.model.Food;

import java.util.List;

/**
 * Created by Mohammad on 4/28/2017.
 */
public interface FoodDao {

    public boolean createAdmin(Food food);
    public boolean updateAdmin(Food food);
    public boolean deleteAdmin(Food food);
    public List<Food> getFoodsBySubCategoryId(long subCategoryId);
    public List<Food> getAllFoods();
}
