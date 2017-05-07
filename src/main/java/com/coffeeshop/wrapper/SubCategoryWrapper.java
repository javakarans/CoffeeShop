package com.coffeeshop.wrapper;


import com.coffeeshop.database.CategoryDaoImp;
import com.coffeeshop.database.SubcategoryDaoImp;
import com.coffeeshop.model.Category;
import com.coffeeshop.model.Subcategory;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryWrapper {
    private long categoryId;
    private String categoryName;
    private long subCategoryId;
    private String subCategoryName;

    public SubCategoryWrapper()
    {}

    public SubCategoryWrapper(Subcategory subcategory)
    {
      this.categoryId=subcategory.getCategoryId();
      this.subCategoryId=subcategory.getSubCategoryId();
      this.subCategoryName = subcategory.getName();
      fill();

    }

    public Subcategory convertToOrginalClass()
    {
        Subcategory subcategory = new Subcategory();
        subcategory.setName(this.subCategoryName);
        subcategory.setSubCategoryId(this.getSubCategoryId());
        subcategory.setCategoryId(this.getCategoryId());
        return subcategory;
    }

    @Override
    public String toString() {
        return subCategoryName + "/" + categoryName;
    }

    private void fill()
    {
        CategoryDaoImp categoryDaoImp = new CategoryDaoImp();
        Category category = categoryDaoImp.getCategoryById(this.categoryId);
        if (category!=null && category.getName()!=null)
            this.categoryName=category.getName();
        else
            this.categoryName = "exception";
    }

    public static List<SubCategoryWrapper> getAll()
    {
        SubcategoryDaoImp subcategoryDaoImp = new SubcategoryDaoImp();
        List<Subcategory> subcategoryList = subcategoryDaoImp.getAllSubCategory();
        List<SubCategoryWrapper> subCategoryWrappers = new ArrayList<SubCategoryWrapper>(subcategoryList.size());
        for (Subcategory subcategory : subcategoryList) {
            subCategoryWrappers.add(new SubCategoryWrapper(subcategory));
        }
        return subCategoryWrappers;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }
}
