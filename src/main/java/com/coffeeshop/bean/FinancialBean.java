package com.coffeeshop.bean;

import com.coffeeshop.database.OrderDetailDaoImp;
import com.coffeeshop.model.OrderDetail;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Amirhossein on 5/11/2017.
 */
@ManagedBean
@ViewScoped
public class FinancialBean {

    private OrderDetailDaoImp orderDetailDaoImp;
    List<OrderDetail> orderDetails;
    double totalPrice;

    private Date today;
    private Date from;
    private Date to;

    @PostConstruct
    public void init(){
        checkAdminIsLogin();
        orderDetails = new ArrayList<OrderDetail>();
        orderDetailDaoImp = new OrderDetailDaoImp();
        today = new Date();
        from = new Date();
        to = new Date();
        orderDetails = orderDetailDaoImp.getAllOrders();
    }

    public String getAuthority(){
        return (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("authority");
    }

    public void checkAdminIsLogin(){
        String authority = getAuthority();
        System.out.println(authority);
        if(authority==null){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/admin/AdminLogin.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void calculateDurationSales(){
        totalPrice = 0;
        for (int i = 0; i <orderDetails.size(); i++){
            if(orderDetails.get(i).getDate().compareTo(from) >= 0 &&
                    orderDetails.get(i).getDate().compareTo(to) <= 0){
                totalPrice += orderDetails.get(i).getTotalPrice();
            }
        }
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("$('#result').modal()");
    }

    public void calculateTodaySales(){
        SimpleDateFormat sm = new SimpleDateFormat("yyyyMMdd");
        String strDate = sm.format(today);
        try {
            today = sm.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date tDate = new java.sql.Date(today.getTime());
        System.out.println(tDate);
        totalPrice = 0;
        for(int i = 0; i < orderDetails.size(); i++){
            if (orderDetails.get(i).getDate().compareTo(tDate) == 0){
                totalPrice += orderDetails.get(i).getTotalPrice();
            }
        }
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("$('#todayResult').modal()");
    }


    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
