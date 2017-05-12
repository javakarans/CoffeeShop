package com.coffeeshop.bean;

import com.coffeeshop.data.SettingData;
import com.coffeeshop.database.FoodDaoImp;
import com.coffeeshop.database.FoodOrderDaoImp;
import com.coffeeshop.database.OrderDetailDaoImp;
import com.coffeeshop.model.Food;
import com.coffeeshop.model.FoodOrder;
import com.coffeeshop.model.OrderDetail;
import com.coffeeshop.model.Status;
import com.coffeeshop.wrapper.FoodOrderWrapper;
import com.coffeeshop.wrapper.SubCategoryWrapper;
import com.coffeeshop.wrapper.UserReceipt;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.coffeeshop.PrinterService.*;
/**
 * Created by amir on 5/3/2017.
 */
@ManagedBean
@ViewScoped
public class onPendingTransaction {

    private List<OrderDetail> pendingOrders;
    private Map<String, String> urlParam;
    private OrderDetailDaoImp orderDetailDaoImp;
    private List<FoodOrder> foodOrderList;
    private FoodOrderDaoImp foodOrderDaoImp;
    private OrderDetail selectedOrder;
    private FoodOrder newFoodOrder;
    private SubCategoryWrapper selectedSubCategoryWrapper;
    private Food selectedFood;
    private List<SubCategoryWrapper> subCategoryWrapperList;
    private List<Food> foodList;
    private FoodDaoImp foodDaoImp;
    private PrintReceipt printReceipt;
    //new order data
    private OrderDetail newOrderDetail;
    private FoodOrder newFoodOrderForNewOrder;
    private List<FoodOrder> newFoodOrderList;
    private int qntForNewFoodOrder;
    private SettingData settingData;

    @PostConstruct
    public void init(){
        checkAdminIsLogin();
        printReceipt = new PrintReceipt();
        foodOrderDaoImp = new FoodOrderDaoImp();
        foodOrderList = new ArrayList<FoodOrder>();
        orderDetailDaoImp=new OrderDetailDaoImp();
        pendingOrders = orderDetailDaoImp.getAllPendingOrder();
        newFoodOrder = new FoodOrder();
        foodDaoImp = new FoodDaoImp();
        foodList = new ArrayList<Food>();
        subCategoryWrapperList = SubCategoryWrapper.getAll();
        settingData = SettingData.getInstance();
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
    public void editOrder(OrderDetail orderDetail)
    {
        selectedOrder = orderDetail;
        foodOrderList = foodOrderDaoImp.getFoodOrderWithOrderId(orderDetail.getOrderDetailId());
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("$('.modalPseudoClass').modal()");
    }

    public void onChange()
    {
        if (selectedSubCategoryWrapper == null)
            foodList = foodDaoImp.getAllFoods();
        else
            foodList = foodDaoImp.getFoodsBySubCategoryId(selectedSubCategoryWrapper.getSubCategoryId());
    }

    public void addFoodOrderToSelectedOrder()
    {
        newFoodOrder.setOrderId(selectedOrder.getOrderDetailId());
        newFoodOrder.setStatus(Status.FOODORDER_NOT_READY);
        newFoodOrder.setFoodId(selectedFood.getFoodId());
        boolean result = foodOrderDaoImp.createFoodOrder(newFoodOrder);
        //show message
        newFoodOrder = new FoodOrder();
    }

    public void newOrder()
    {
        System.out.println("gholam");
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setStatus(Status.ORDER_ONPENDING);
        orderDetail.setDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        orderDetail.setTrackingNumber(settingData.getTrackNumber());
        orderDetailDaoImp.createOrder(orderDetail);

        updatePendingOrderOrderTable();
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("pendingOrderRow");
    }

    public void prepareDataForNewOrder()
    {
        newOrderDetail = new OrderDetail();
        newFoodOrderList = new ArrayList<FoodOrder>();
        newFoodOrderForNewOrder = new FoodOrder();
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("newOrder");
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("$('.modalNewOrder').modal()");
    }

    public void addNewFoodOrderToNewOrder()
    {
        if (selectedFood!=null)
        {
            newFoodOrderForNewOrder = new FoodOrder();
            newFoodOrderForNewOrder.setStatus(Status.FOODORDER_NOT_READY);
            newFoodOrderForNewOrder.setQuantity(qntForNewFoodOrder);
            newFoodOrderForNewOrder.setFoodId(selectedFood.getFoodId());
            newFoodOrderList.add(newFoodOrderForNewOrder);
        }
        else
        {
            FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Food Not Selected !!!", "");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }

    }

    public void print()
    {
        UserReceipt userReceipt = new UserReceipt();
        userReceipt.setDate(new Date());
        userReceipt.setOrderDetailIdWrapper(123);
        userReceipt.setTrackNumber(1234);
        userReceipt.setTotalprice(1200);
        List<FoodOrderWrapper> foodOrderWrappers = new ArrayList<FoodOrderWrapper>();
        for (int i=0 ; i<12;i++)
        {
            FoodOrderWrapper foodOrderWrapper = new FoodOrderWrapper();
            foodOrderWrapper.setFoodName("غذل".concat(String.valueOf(i)));
            foodOrderWrapper.setFoodOrderWrapperId(i);
            foodOrderWrapper.setPrice(i);
            foodOrderWrapper.setQuantity(i);
            foodOrderWrapper.setTotalPrice(i*i);
            foodOrderWrappers.add(foodOrderWrapper);
        }
        userReceipt.setFoodOrderWrapperList(foodOrderWrappers);
         printReceipt.printUserReceipt("noori",userReceipt);
    }

    public void updatePendingOrderOrderTable()
    {
        pendingOrders = orderDetailDaoImp.getAllPendingOrder();
    }

    public List<OrderDetail> getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(List<OrderDetail> pendingOrders) {
        this.pendingOrders = pendingOrders;
    }

    public OrderDetail getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(OrderDetail selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    public List<FoodOrder> getFoodOrderList() {
        return foodOrderList;
    }

    public void setFoodOrderList(List<FoodOrder> foodOrderList) {
        this.foodOrderList = foodOrderList;
    }

    public FoodOrder getNewFoodOrder() {
        return newFoodOrder;
    }

    public void setNewFoodOrder(FoodOrder newFoodOrder) {
        this.newFoodOrder = newFoodOrder;
    }

    public SubCategoryWrapper getSelectedSubCategoryWrapper() {
        return selectedSubCategoryWrapper;
    }

    public void setSelectedSubCategoryWrapper(SubCategoryWrapper selectedSubCategoryWrapper) {
        this.selectedSubCategoryWrapper = selectedSubCategoryWrapper;
    }

    public Food getSelectedFood() {
        return selectedFood;
    }

    public void setSelectedFood(Food selectedFood) {
        this.selectedFood = selectedFood;
    }

    public List<SubCategoryWrapper> getSubCategoryWrapperList() {
        return subCategoryWrapperList;
    }

    public void setSubCategoryWrapperList(List<SubCategoryWrapper> subCategoryWrapperList) {
        this.subCategoryWrapperList = subCategoryWrapperList;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public OrderDetail getNewOrderDetail() {
        return newOrderDetail;
    }

    public void setNewOrderDetail(OrderDetail newOrderDetail) {
        this.newOrderDetail = newOrderDetail;
    }

    public FoodOrder getNewFoodOrderForNewOrder() {
        return newFoodOrderForNewOrder;
    }

    public void setNewFoodOrderForNewOrder(FoodOrder newFoodOrderForNewOrder) {
        this.newFoodOrderForNewOrder = newFoodOrderForNewOrder;
    }

    public List<FoodOrder> getNewFoodOrderList() {
        return newFoodOrderList;
    }

    public void setNewFoodOrderList(List<FoodOrder> newFoodOrderList) {
        this.newFoodOrderList = newFoodOrderList;
    }

    public int getQntForNewFoodOrder() {
        return qntForNewFoodOrder;
    }

    public void setQntForNewFoodOrder(int qntForNewFoodOrder) {
        this.qntForNewFoodOrder = qntForNewFoodOrder;
    }
}
