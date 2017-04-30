package com.coffeeshop.bean;

import com.coffeeshop.database.SubcategoryDaoImp;
import com.coffeeshop.model.Subcategory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
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

    @PostConstruct
    private void init() {
        long categoryId = userSessionBean.getSelectedCategory();
        System.out.println(categoryId+"salammmmmmmmmmmmmmm");
        SubcategoryDaoImp subcategoryDaoImp = new SubcategoryDaoImp();
        subcategories = subcategoryDaoImp.getSubCategoriesByCategoryId(categoryId);


    }


    public void onClick(long SubCategoryId) {
        userSessionBean.setSelectedSubCategory(SubCategoryId);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("foodPage.xhtml");
        } catch (IOException e) {
            System.out.println("can not redirect");
        }

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

}
