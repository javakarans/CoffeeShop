package com.coffeeshop.bean;

import com.coffeeshop.database.OrderDetailDaoImp;
import com.coffeeshop.model.OrderDetail;
import com.coffeeshop.validator.ValidatorMessage;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Map;

/**
 * Created by amirhossein on 5/23/2017.
 */
@ManagedBean
@ViewScoped
public class AllOrersBean {

    private OrderDetailDaoImp orderDetailDaoImp;
    private List<OrderDetail> orderDetails;
    private Map<String, String> urlParam;
    private String validatorMessage;

    @PostConstruct
    public void init(){
        orderDetailDaoImp = new OrderDetailDaoImp();
        getURLParam();
        checkLogin();
        orderDetails = orderDetailDaoImp.getAllOrders();
    }

    public void getURLParam(){
        urlParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    }

    public void checkLogin(){
        if(urlParam.get("login")!=null&&urlParam.get("login").equals("false")){
            validatorMessage= ValidatorMessage.getInstance().getHashMap().get(1);
        }else {
            validatorMessage="";
        }
    }

    public String urlNav(){
        return "allOrders.xhtml?faces-redirect=true";
    }

    public OrderDetailDaoImp getOrderDetailDaoImp() {
        return orderDetailDaoImp;
    }

    public void setOrderDetailDaoImp(OrderDetailDaoImp orderDetailDaoImp) {
        this.orderDetailDaoImp = orderDetailDaoImp;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Map<String, String> getUrlParam() {
        return urlParam;
    }

    public void setUrlParam(Map<String, String> urlParam) {
        this.urlParam = urlParam;
    }

    public String getValidatorMessage() {
        return validatorMessage;
    }

    public void setValidatorMessage(String validatorMessage) {
        this.validatorMessage = validatorMessage;
    }

}
