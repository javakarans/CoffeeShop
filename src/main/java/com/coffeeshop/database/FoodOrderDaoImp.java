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

    public boolean createAdmin(FoodOrder foodOrder) {
        boolean done = sqlService.create(foodOrder);
        return done;
    }

    public boolean updateAdmin(FoodOrder foodOrder) {
        boolean done = sqlService.update(foodOrder);
        return done;
    }

    public boolean deleteAdmin(FoodOrder foodOrder) {
        boolean done = sqlService.delete(foodOrder);
        return done;
    }
}
