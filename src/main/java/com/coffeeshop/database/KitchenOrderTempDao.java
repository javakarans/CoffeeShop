package com.coffeeshop.database;

import com.coffeeshop.model.KitchenOrderTemp;

import java.util.List;

/**
 * Created by amirhossein on 5/5/2017.
 */
public interface KitchenOrderTempDao {
    public Boolean createKitchenOrderTemp(KitchenOrderTemp kitchenOrderTemp);
    public Boolean updateKitchenOrderTemp(KitchenOrderTemp kitchenOrderTemp);
    public Boolean deleteKitchenOrderTemp(KitchenOrderTemp kitchenOrderTemp);
    //need one object
    public List getKitchenOrderTempById(long id);
    //need one object
    public List getKitchenOrderTempByKitchenId(long kitchenId);
    public List getAllKitchenOrderTemp();
}
