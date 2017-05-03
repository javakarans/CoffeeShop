package com.coffeeshop.model;


import com.coffeeshop.database.CategoryDaoImp;

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
