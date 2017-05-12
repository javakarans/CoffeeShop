package com.coffeeshop.bean;


import com.coffeeshop.database.CategoryDaoImp;
import com.coffeeshop.database.FoodOrderDaoImp;
import com.coffeeshop.database.OrderDetailDaoImp;
import com.coffeeshop.model.Category;
import com.coffeeshop.model.OrderDetail;
import com.coffeeshop.model.Status;
import com.coffeeshop.wrapper.FoodOrderWrapper;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

@ManagedBean(name = "dtCategoryBean")
@ViewScoped
public class CategoryBean {

    private CategoryDaoImp categoryDaoImp;
    private List<Category> categoryList;
    @ManagedProperty(value = "#{dtUserSessionBean}")
    private UserSessionBean userSessionBean;

    @PostConstruct
    public void init()
    {
        categoryDaoImp = new CategoryDaoImp();
        categoryList = categoryDaoImp.getAllCategories();
    }

    public void redirectToSubCtegoryPage(long categoryId)
    {
        try {
            userSessionBean.setSelectedCategory(categoryId);
            FacesContext.getCurrentInstance().getExternalContext().redirect("subcategoryPage.xhtml");
        } catch (IOException e) {
            System.out.println("can not redirect");
        }
    }

    public void removeFoodItem(FoodOrderWrapper foodOrderWrapper){
        userSessionBean.getFoodOrderWrapperList().remove(foodOrderWrapper);
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public UserSessionBean getUserSessionBean() {
        return userSessionBean;
    }

    public void setUserSessionBean(UserSessionBean userSessionBean) {
        this.userSessionBean = userSessionBean;
    }
}
