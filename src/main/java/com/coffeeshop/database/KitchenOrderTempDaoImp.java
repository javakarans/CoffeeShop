package com.coffeeshop.database;

import com.coffeeshop.model.KitchenOrderTemp;

import java.util.List;

/**
 * Created by amirhossein on 5/5/2017.
 */
public class KitchenOrderTempDaoImp implements KitchenOrderTempDao{

    private SQLService sqlService;

    public KitchenOrderTempDaoImp(){
        sqlService = new SQLService();
    }


    public Boolean createKitchenOrderTemp(KitchenOrderTemp kitchenOrderTemp) {
        return sqlService.create(kitchenOrderTemp);
    }

    public Boolean updateKitchenOrderTemp(KitchenOrderTemp kitchenOrderTemp) {
        return sqlService.update(kitchenOrderTemp);
    }

    public Boolean deleteKitchenOrderTemp(KitchenOrderTemp kitchenOrderTemp) {
        return sqlService.delete(kitchenOrderTemp);
    }

    public List getKitchenOrderTempById(long id) {
        List result = sqlService.getObjectsBySpecialColumn(new KitchenOrderTemp(), "kitchenOrderTempId", id);
        return result;
    }

    public List getKitchenOrderTempByKitchenId(long kitchenId) {
        List result = sqlService.getObjectsBySpecialColumn(new KitchenOrderTemp(), "kitchenId", kitchenId);
        return result;
    }

    public List getAllKitchenOrderTemp() {
        List result = sqlService.getAllObjects(new KitchenOrderTemp());
        return result;
    }
}
