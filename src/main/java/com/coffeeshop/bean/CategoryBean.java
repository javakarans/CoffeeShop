package com.coffeeshop.bean;


import com.coffeeshop.database.CategoryDaoImp;
import com.coffeeshop.model.Category;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.IOException;
import java.util.List;

@ManagedBean(name = "dtCategoryBean")
@ViewScoped
public class CategoryBean {
    private CategoryDaoImp categoryDaoImp;
    private List<Category> categoryList;
    @ManagedProperty(value = "dtUserSessionBean")
    private UserSessionBean userSessionBean;


    @PostConstruct
    public void init()
    {
        categoryDaoImp = new CategoryDaoImp();
        categoryList = categoryDaoImp.getAllCategories();
        System.out.println( categoryDaoImp.getAllCategories().size());
    }

    public void redirectToSubCtegoryPage()
    {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("subcategoryPage.xhtml");
        } catch (IOException e) {
            System.out.println("can not redirect");
        }
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
