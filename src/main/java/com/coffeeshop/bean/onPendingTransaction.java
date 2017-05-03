package com.coffeeshop.bean;

import com.coffeeshop.database.OrderDetailDaoImp;
import com.coffeeshop.model.OrderDetail;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created by amir on 5/3/2017.
 */
@ManagedBean
@ViewScoped
public class onPendingTransaction {

    private List<OrderDetail> allOrders;

    @PostConstruct
    public void init(){
        loadOrderDetail();
    }


    public void loadOrderDetail(){
        OrderDetailDaoImp orderDetailDaoImp=new OrderDetailDaoImp();
        allOrders = orderDetailDaoImp.getAllOrders();
    }

    public List<OrderDetail> getAllOrders() {
        return allOrders;
    }

    public void setAllOrders(List<OrderDetail> allOrders) {
        this.allOrders = allOrders;
    }
}
