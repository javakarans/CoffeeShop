package com.coffeeshop.wrapper;

import java.util.Date;
import java.util.List;

/**
 * Created by amir on 5/7/2017.
 */
public class KitchenReceipt {
    private String printerName;
    private List<FoodOrderWrapper> foodOrderWrapperList;
    private int trackNumber;
    private Date date;

    public String getPrinterName() {
        return printerName;
    }

    public void setPrinterName(String printerName) {
        this.printerName = printerName;
    }

    public List<FoodOrderWrapper> getFoodOrderWrapperList() {
        return foodOrderWrapperList;
    }

    public void setFoodOrderWrapperList(List<FoodOrderWrapper> foodOrderWrapperList) {
        this.foodOrderWrapperList = foodOrderWrapperList;
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
}
