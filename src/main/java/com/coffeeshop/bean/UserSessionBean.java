package com.coffeeshop.bean;

import com.coffeeshop.PrinterService.PrintReceipt;
import com.coffeeshop.data.SettingData;
import com.coffeeshop.database.FoodOrderDaoImp;
import com.coffeeshop.database.OrderDetailDaoImp;
import com.coffeeshop.model.Food;
import com.coffeeshop.model.OrderDetail;
import com.coffeeshop.model.Status;
import com.coffeeshop.wrapper.FoodOrderWrapper;
import com.coffeeshop.wrapper.UserReceipt;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

@ManagedBean(name = "dtUserSessionBean")
@SessionScoped
public class UserSessionBean {
    private long selectedCategory;
    private long selectedSubCategory;
    private List<FoodOrderWrapper> foodOrderWrapperList;
    private SettingData settingData;

    @PostConstruct
    public void init()
    {
        foodOrderWrapperList=new ArrayList<FoodOrderWrapper>();
        settingData=SettingData.getInstance();
    }

    public void invalidSession(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public double calTotalPrice(){
        Iterator<FoodOrderWrapper> iterator = foodOrderWrapperList.iterator();
        double totalFoodsPrice=0;
        while (iterator.hasNext()){
            totalFoodsPrice = totalFoodsPrice+iterator.next().getTotalPrice();
        }
        return totalFoodsPrice;
    }

    public String makeOrder(){
        FoodOrderDaoImp foodOrderDaoImp=new FoodOrderDaoImp();
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setTotalPrice(calTotalPrice());
        orderDetail.setTrackingNumber(settingData.getTrackNumber());
        orderDetail.setDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        orderDetail.setStatus(Status.ORDER_ONPENDING);
        OrderDetailDaoImp orderDetailDaoImp=new OrderDetailDaoImp();
        orderDetailDaoImp.createOrder(orderDetail);
        Iterator<FoodOrderWrapper> iterator = foodOrderWrapperList.iterator();
        while (iterator.hasNext()){
            FoodOrderWrapper next = iterator.next();
            next.setOrderDetailId(orderDetail.getOrderDetailId());
            foodOrderDaoImp.createFoodOrder(next.convertToOriginalClass());
        }
        printReceipt(orderDetail);
        invalidSession();
        return "/user/categoryPage.xhtml?faces-redirect=true";
    }

    private boolean printReceipt(OrderDetail orderDetail){
        PrintReceipt printReceipt=new PrintReceipt();
        UserReceipt userReceipt=new UserReceipt();
        userReceipt.setFoodOrderWrapperList(foodOrderWrapperList);
        userReceipt.setTotalprice(calTotalPrice());
        userReceipt.setTrackNumber(orderDetail.getTrackingNumber());
        return printReceipt.printUserReceipt("",userReceipt);
    }

    public boolean canMakeOrder(){
        if(!foodOrderWrapperList.isEmpty()){
            return true;
        }
        return false;
    }

    public long getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(long selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public long getSelectedSubCategory() {
        return selectedSubCategory;
    }

    public void setSelectedSubCategory(long selectedSubCategory) {
        this.selectedSubCategory = selectedSubCategory;
    }

    public List<FoodOrderWrapper> getFoodOrderWrapperList() {
        return foodOrderWrapperList;
    }

    public void setFoodOrderWrapperList(List<FoodOrderWrapper> foodOrderWrapperList) {
        this.foodOrderWrapperList = foodOrderWrapperList;
    }
}
