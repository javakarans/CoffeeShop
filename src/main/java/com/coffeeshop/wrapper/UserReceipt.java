package com.coffeeshop.wrapper;

import java.util.Date;
import java.util.List;

/**
 * Created by amir on 5/7/2017.
 */
public class UserReceipt {
    private long orderDetailIdWrapper;
    private int trackNumber;
    private Date date;
    private List<FoodOrderWrapper> foodOrderWrapperList;

    public long getOrderDetailIdWrapper() {
        return orderDetailIdWrapper;
    }

    public void setOrderDetailIdWrapper(long orderDetailIdWrapper) {
        this.orderDetailIdWrapper = orderDetailIdWrapper;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<FoodOrderWrapper> getFoodOrderWrapperList() {
        return foodOrderWrapperList;
    }

    public void setFoodOrderWrapperList(List<FoodOrderWrapper> foodOrderWrapperList) {
        this.foodOrderWrapperList = foodOrderWrapperList;
    }
}
