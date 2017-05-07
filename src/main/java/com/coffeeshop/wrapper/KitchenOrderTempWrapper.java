package com.coffeeshop.wrapper;


import com.coffeeshop.database.FoodDaoImp;
import com.coffeeshop.database.FoodOrderDaoImp;
import com.coffeeshop.database.OrderDetailDaoImp;
import com.coffeeshop.model.Food;
import com.coffeeshop.model.FoodOrder;
import com.coffeeshop.model.KitchenOrderTemp;
import com.coffeeshop.model.OrderDetail;

import java.util.List;

public class KitchenOrderTempWrapper {
    private long kitchenOrderTempId;
    private long kitchenId;
    private long trackNumber;
    private long foodOrderId;
    private String printerName;

    //    private long foodId;
    //    private long orderId;
    private String foodName;
    private long foodQuantity;
    private int customerTrackingNumber;
    private String foodStatus;

    public KitchenOrderTempWrapper() {

    }

    public KitchenOrderTempWrapper(KitchenOrderTemp kitchenOrderTemp) {
        this.kitchenOrderTempId = kitchenOrderTemp.getKitchenOrderId();
        this.kitchenId = kitchenOrderTemp.getKitchenId();
        this.foodOrderId = kitchenOrderTemp.getFoodOrderId();
        this.trackNumber = kitchenOrderTemp.getTrackNumber();
        fill();
    }

    private void fill() {
        FoodOrderDaoImp foodOrderDaoImp = new FoodOrderDaoImp();
        FoodDaoImp foodDaoImp = new FoodDaoImp();
        OrderDetailDaoImp orderDetailDaoImp = new OrderDetailDaoImp();
        FoodOrder foodOrder = foodOrderDaoImp.getFoodOrderById(this.foodOrderId);
        this.foodQuantity = foodOrder.getQuantity();
        this.foodStatus = foodOrder.getStatus();
        Food food = foodDaoImp.getFoodByFoodId(foodOrder.getFoodId());
        this.foodName = food.getName();
        List orderDetails = orderDetailDaoImp.getOrderDetailById(foodOrder.getOrderId());
        if (!orderDetails.isEmpty()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail = (OrderDetail) orderDetails.get(0);
            this.customerTrackingNumber = orderDetail.getTrackingNumber();
        }

    }

    public long getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(long trackNumber) {
        this.trackNumber = trackNumber;
    }

    public long getKitchenOrderTempId() {
        return kitchenOrderTempId;
    }

    public void setKitchenOrderTempId(long kitchenOrderTempWrapperId) {
        this.kitchenOrderTempId = kitchenOrderTempWrapperId;
    }

    public long getFoodOrderId() {
        return foodOrderId;
    }

    public void setFoodOrderId(long foodOrderId) {
        this.foodOrderId = foodOrderId;
    }

    public long getKitchenId() {
        return kitchenId;
    }

    public void setKitchenId(long kitchenId) {
        this.kitchenId = kitchenId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

//    public long getFoodId() {
//        return foodId;
//    }
//
//    public void setFoodId(long foodId) {
//        this.foodId = foodId;
//    }

    public long getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(long foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public String getFoodStatus() {
        return foodStatus;
    }

    public void setFoodStatus(String foodStatus) {
        this.foodStatus = foodStatus;
    }

//    public long getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(long orderId) {
//        this.orderId = orderId;
//    }

    public int getCustomerTrackingNumber() {
        return customerTrackingNumber;
    }

    public void setCustomerTrackingNumber(int customerTrackingNumber) {
        this.customerTrackingNumber = customerTrackingNumber;
    }

    public String getPrinterName() {
        return printerName;
    }

    public void setPrinterName(String printerName) {
        this.printerName = printerName;
    }
}
