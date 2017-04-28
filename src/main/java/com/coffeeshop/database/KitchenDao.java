package com.coffeeshop.database;

import com.coffeeshop.model.Kitchen;

/**
 * Created by Amirhossein on 4/28/2017.
 */
public interface KitchenDao {

    public boolean createKitchen(Kitchen kitchen);
    public boolean updateKitchen(Kitchen kitchen);
    public boolean deleteKitchen(Kitchen kitchen);
    public Kitchen getKitchenByName(String kitchenName);
}
