package com.coffeeshop.bean;

import com.coffeeshop.database.FoodDaoImp;
import com.coffeeshop.database.SubcategoryDaoImp;
import com.coffeeshop.model.Food;
import com.coffeeshop.model.Subcategory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

    public void loadFoodBySubCategory(long subCategoryId,int index){
        FoodDaoImp foodDaoImp=new FoodDaoImp();
        List<Food> foodList = foodDaoImp.getFoodsBySubCategoryId(subCategoryId);
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
