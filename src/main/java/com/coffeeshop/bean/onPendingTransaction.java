package com.coffeeshop.bean;

import com.coffeeshop.database.OrderDetailDaoImp;
import com.coffeeshop.model.OrderDetail;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by amir on 5/3/2017.
 */
@ManagedBean
@ViewScoped
public class onPendingTransaction {

    private List<OrderDetail> allOrders;
    private Map<String, String> urlParam;

    @PostConstruct
    public void init(){
        loadOrderDetail();
        checkAdminIsLogin();
    }

    public String getAuthority(){
        return (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("authority");
    }

    public void checkAdminIsLogin(){
        String authority = getAuthority();
        if(authority==null){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/admin/AdminLogin.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
