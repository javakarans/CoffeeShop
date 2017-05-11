package com.coffeeshop.bean;

import com.coffeeshop.model.Food;
import com.coffeeshop.wrapper.FoodOrderWrapper;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "dtUserSessionBean")
@SessionScoped
public class UserSessionBean {
    private long selectedCategory;
    private long selectedSubCategory;
    private List<FoodOrderWrapper> foodOrderWrapperList;

    @PostConstruct
    public void init()
    {
        foodOrderWrapperList=new ArrayList<FoodOrderWrapper>();
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
