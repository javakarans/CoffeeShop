package com.coffeeshop.bean;

import com.coffeeshop.database.FoodDaoImp;
import com.coffeeshop.model.Food;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created by Mohammad on 4/30/2017.
 */
@ManagedBean
@ViewScoped
public class FoodPage {

    @ManagedProperty(value = "#{dtUserSessionBean}")
    private UserSessionBean userSessionBean;
    private long selectedSubCategoryId;
    private List<Food> FoodList;

    @PostConstruct
    public void init(){
        selectedSubCategoryId = userSessionBean.getSelectedSubCategory();
        loadFoods();
    }

    public void loadFoods(){
        FoodDaoImp foodDaoImp=new FoodDaoImp();
        FoodList = foodDaoImp.getFoodsBySubCategoryId(selectedSubCategoryId);
    }

    public UserSessionBean getUserSessionBean() {
        return userSessionBean;
    }

    public void setUserSessionBean(UserSessionBean userSessionBean) {
        this.userSessionBean = userSessionBean;
    }

    public List<Food> getFoodList() {
        return FoodList;
    }

    public void setFoodList(List<Food> foodList) {
        FoodList = foodList;
    }
}
