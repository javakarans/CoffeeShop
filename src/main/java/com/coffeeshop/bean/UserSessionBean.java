package com.coffeeshop.bean;

import com.coffeeshop.model.Food;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "dtUserSessionBean")
@SessionScoped
public class UserSessionBean {
    private long selectedCategory;
    private long selectedSubCategory;
    private List<Food> selectedFoods;

    @PostConstruct
    public void init()
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

    public List<Food> getSelectedFoods() {
        return selectedFoods;
    }

    public void setSelectedFoods(List<Food> selectedFoods) {
        this.selectedFoods = selectedFoods;
    }
}
