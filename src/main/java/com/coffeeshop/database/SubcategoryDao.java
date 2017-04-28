package com.coffeeshop.database;

import com.coffeeshop.model.Subcategory;

import java.util.List;

/**
 * Created by Amirhossein on 4/28/2017.
 */
public interface SubcategoryDao {

    public boolean createSubcategory(Subcategory subcategory);
    public boolean updateSubcategory(Subcategory subcategory);
    public boolean deleteSubcategory(Subcategory subcategory);
    public List<Subcategory> getSubCategoriesByCategoryId(long categoryId);

}
