package com.coffeeshop.bean;

import com.coffeeshop.database.FoodDaoImp;
import com.coffeeshop.database.FoodOrderDaoImp;
import com.coffeeshop.database.OrderDetailDaoImp;
import com.coffeeshop.model.Food;
import com.coffeeshop.model.FoodOrder;
import com.coffeeshop.model.OrderDetail;
import com.coffeeshop.model.Status;
import com.coffeeshop.wrapper.SubCategoryWrapper;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
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


    @PostConstruct
    public void init(){
        checkAdminIsLogin();
        foodOrderDaoImp = new FoodOrderDaoImp();
        foodOrderList = new ArrayList<FoodOrder>();
        orderDetailDaoImp=new OrderDetailDaoImp();
        allOrders = orderDetailDaoImp.getAllOrders();
        newFoodOrder = new FoodOrder();
        foodDaoImp = new FoodDaoImp();
        foodList = new ArrayList<Food>();
        subCategoryWrapperList = SubCategoryWrapper.getAll();
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
    public void editOrder(OrderDetail orderDetail)
    {
        selectedOrder = orderDetail;
        foodOrderList = foodOrderDaoImp.getFoodOrderWithOrderId(orderDetail.getOrderDetailId());
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("$('.modalPseudoClass').modal()");
    }

    public void onChange()
    {
        if (selectedSubCategoryWrapper!=null)
            foodList = foodDaoImp.getAllFoods();
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

    public List<OrderDetail> getAllOrders() {
        return allOrders;
    }

    public void setAllOrders(List<OrderDetail> allOrders) {
        this.allOrders = allOrders;
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
}
