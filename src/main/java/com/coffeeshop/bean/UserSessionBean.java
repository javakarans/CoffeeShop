package com.coffeeshop.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "dtUserSessionBean")
@SessionScoped
public class UserSessionBean {
    private long selectedCategory;
    private long selectedSubCategory;

    @PostConstruct
    private void init()
    {

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
}
