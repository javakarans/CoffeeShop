package com.coffeeshop.bean;

import com.coffeeshop.database.KitchenDaoImp;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * Created by Amirhossein on 5/4/2017.
 */
@ManagedBean
@SessionScoped
public class Kitchen implements Serializable{

    private KitchenDaoImp kitchenDaoImp;
    private FoodOrderWrapper foodOrderWrapper;

    @PostConstruct
    public void init(){
        kitchenDaoImp = new KitchenDaoImp();
    }

}
