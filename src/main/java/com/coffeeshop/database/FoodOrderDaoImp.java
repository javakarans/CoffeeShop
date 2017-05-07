package com.coffeeshop.database;

import com.coffeeshop.model.FoodOrder;

import java.util.List;

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

    public FoodOrder getFoodOrderById(long id)
    {
        List result = sqlService.getObjectsBySpecialColumn(new FoodOrder(),"foodorderid",id);
        if (!result.isEmpty())
            return (FoodOrder) result.get(0);
        else return new FoodOrder();
    }

    public List<FoodOrder> getFoodOrderWithOrderId(long id)
    {
        return sqlService.getObjectsBySpecialColumn(new FoodOrder(),"orderId",id);
    }
}
