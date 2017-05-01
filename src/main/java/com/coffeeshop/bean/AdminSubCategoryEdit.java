package com.coffeeshop.bean;

import com.coffeeshop.database.CategoryDaoImp;
import com.coffeeshop.database.SubcategoryDaoImp;
import com.coffeeshop.model.Category;
import com.coffeeshop.model.Subcategory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created by H&H on 5/1/2017.
 */
@ManagedBean
@ViewScoped
public class AdminSubCategoryEdit {

    private List<Category> categories;
    private List<Subcategory> subcategories;
    private Subcategory NewSubcategory;
    private SubcategoryDaoImp subcategoryDaoImp;
    private long selectedCategoryid=1;

    @PostConstruct
    private void init() {
        subcategoryDaoImp = new SubcategoryDaoImp();
        System.out.println("hi");
        CategoryDaoImp categoryDaoImp = new CategoryDaoImp();
        categories = categoryDaoImp.getAllCategories();
        System.out.println("size : " + categories.size());
    }

    public List<Subcategory> updateSubCategory() {
        subcategories = subcategoryDaoImp.getSubCategoriesByCategoryId(selectedCategoryid);
        System.out.println("subs size: " + subcategories.size());
        return subcategories;
    }

    public void save() {

        System.out.println("sub cat name: " + NewSubcategory.getName());
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }


    public Subcategory getNewSubcategory() {
        return NewSubcategory;
    }

    public void setNewSubcategory(Subcategory newSubcategory) {
        NewSubcategory = newSubcategory;
    }

    public long getSelectedCategoryid() {
        return selectedCategoryid;
    }

    public void setSelectedCategoryid(long selectedCategoryid) {
        this.selectedCategoryid = selectedCategoryid;
    }
}

