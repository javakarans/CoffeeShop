package com.coffeeshop.database;

import com.coffeeshop.model.FoodOrder;

/**
 * Created by Mohammad on 4/28/2017.
 */
public class FoodOrderDaoImp implements FoodOrderDao{

    private SQLService sqlService;

    public FoodOrderDaoImp(){
        sqlService=new SQLService();
    }

    public boolean createFoodOrder(FoodOrder foodOrder) {
        boolean done = sqlService.create(foodOrder);
        return done;
    }

    public boolean updateFoodOrder(FoodOrder foodOrder) {
        boolean done = sqlService.update(foodOrder);
        return done;
    }

    public boolean deleteFoodOrder(FoodOrder foodOrder) {
        boolean done = sqlService.delete(foodOrder);
        return done;
    }
}
