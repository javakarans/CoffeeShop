package com.coffeeshop.database;

import com.coffeeshop.model.Subcategory;

import java.util.List;

/**
 * Created by Amirhossein on 4/28/2017.
 */
public class SubcategoryDaoImp implements SubcategoryDao{

    private SQLService sqlService;

    public SubcategoryDaoImp(){
        sqlService = new SQLService();
    }

    public boolean createSubcategory(Subcategory subcategory) {
        return sqlService.create(subcategory);
    }

    public boolean updateSubcategory(Subcategory subcategory) {
        return sqlService.update(subcategory);
    }

    public boolean deleteSubcategory(Subcategory subcategory) {
        return sqlService.delete(subcategory);
    }

    public List<Subcategory> getSubCategoriesByCategoryId(long categoryId) {
        List subcategories = sqlService.getObjectsBySpecialColumn(new Subcategory(), "categoryId", categoryId);
        return subcategories;
    }

    public List<Subcategory> getAllSubCategory()
    {
        return  sqlService.getAllObjects(new Subcategory());
    }
}
