package com.coffeeshop.database;

import com.coffeeshop.model.Kitchen;

import java.util.List;

/**
 * Created by Amirhossein on 4/28/2017.
 */
public class KitchenDaoImp implements KitchenDao{

    private SQLService sqlService;

    public KitchenDaoImp(){
        sqlService = new SQLService();
    }

    public boolean createKitchen(Kitchen kitchen) {
        return sqlService.create(kitchen);
    }

    public boolean updateKitchen(Kitchen kitchen) {
        return sqlService.update(kitchen);
    }

    public boolean deleteKitchen(Kitchen kitchen) {
        return sqlService.delete(kitchen);
    }

    public Kitchen getKitchenByName(String kitchenName) {
        List result = sqlService.getObjectsBySpecialColumn(new Kitchen(), "name", kitchenName);
        if(!result.isEmpty()){
            return (Kitchen) result.get(0);
        }
        return new Kitchen();
    }

    public List<Kitchen> getAllKitchen()
    {
        return sqlService.getAllObjects(new Kitchen());
    }
}
