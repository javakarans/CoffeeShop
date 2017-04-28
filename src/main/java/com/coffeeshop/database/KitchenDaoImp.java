package com.coffeeshop.database;

import com.coffeeshop.model.Kitchen;

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
        Kitchen kitchen = (Kitchen) sqlService.getObjectsBySpecialColumn(new Kitchen(), "name", kitchenName).get(0);
        return kitchen;
    }
}
