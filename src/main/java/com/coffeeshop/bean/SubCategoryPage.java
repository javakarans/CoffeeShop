package com.coffeeshop.bean;

import com.coffeeshop.database.FoodDaoImp;
import com.coffeeshop.database.SubcategoryDaoImp;
import com.coffeeshop.model.Food;
import com.coffeeshop.model.Subcategory;
import com.coffeeshop.wrapper.FoodOrderWrapper;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by H&H on 4/30/2017.
 */
@ManagedBean
@ViewScoped
public class SubCategoryPage {


    @ManagedProperty(value = "#{dtUserSessionBean}")
    private UserSessionBean userSessionBean;
    private List<Subcategory> subcategories;
    private List<Food> foodList;

    @PostConstruct
    private void init() {
        foodList=new ArrayList<Food>();
        long categoryId = userSessionBean.getSelectedCategory();
        SubcategoryDaoImp subcategoryDaoImp = new SubcategoryDaoImp();
        subcategories = subcategoryDaoImp.getSubCategoriesByCategoryId(categoryId);
    }

    public void loadFoodBySubCategory(long subCategoryId){
        FoodDaoImp foodDaoImp=new FoodDaoImp();
        foodList = foodDaoImp.getFoodsBySubCategoryId(subCategoryId);
    }

    public void addToCart(Food food){
        FoodOrderWrapper foodOrderWrapper=new FoodOrderWrapper();
        foodOrderWrapper.setFoodId(food.getFoodId());
        foodOrderWrapper.setFoodName(food.getName());
        foodOrderWrapper.setPrice(food.getPrice());
        userSessionBean.getFoodOrderWrapperList().add(foodOrderWrapper);
    }

    public boolean checkAddedToCart(Food food){
        Iterator<FoodOrderWrapper> iterator = userSessionBean.getFoodOrderWrapperList().iterator();
        while (iterator.hasNext()){
            FoodOrderWrapper next = iterator.next();
            if(next.getFoodId()==food.getFoodId()){
                return true;
            }
        }
        return false;
    }

    public double calTotalPriceForEveryFood(FoodOrderWrapper foodOrderWrapper){
        double price = foodOrderWrapper.getPrice();
        int quantity = foodOrderWrapper.getQuantity();
        foodOrderWrapper.setTotalPrice(price*quantity);
        return foodOrderWrapper.getTotalPrice();
    }

    public void removeFoodItem(FoodOrderWrapper foodOrderWrapper){
        userSessionBean.getFoodOrderWrapperList().remove(foodOrderWrapper);
    }

    public UserSessionBean getUserSessionBean() {
        return userSessionBean;
    }

    public void setUserSessionBean(UserSessionBean userSessionBean) {
        this.userSessionBean = userSessionBean;
    }

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }
}
