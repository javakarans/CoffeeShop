package com.coffeeshop.database;

import com.coffeeshop.model.Food;

import java.util.List;

/**
 * Created by Mohammad on 4/28/2017.
 */
public class FoodDaoImp implements FoodDao{

    private SQLService sqlService;

    public FoodDaoImp(){
        sqlService=new SQLService();
    }

    public boolean createFood(Food food) {
        boolean done = sqlService.create(food);
        return done;
    }

    public boolean updateFood(Food food) {
        boolean done = sqlService.update(food);
        return done;
    }

    public boolean deleteFood(Food food) {
        boolean done = sqlService.delete(food);
        return done;
    }

    public List<Food> getFoodsBySubCategoryId(long subCategoryId) {
        List<Food> foods= sqlService.getObjectsBySpecialColumn(new Food(),"subCategoryId",subCategoryId);
        return foods;
    }

    public Food getFoodByFoodId(long foodId){
        Food food = (Food) sqlService.getObjectsBySpecialColumn(new Food(), "foodId", foodId).get(0);
        return food;
    }

    public List<Food> getAllFoods() {
        List<Food> foods= sqlService.getAllObjects(new Food());
        return foods;
    }
}
