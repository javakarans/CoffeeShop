package com.coffeeshop.database;

import com.coffeeshop.model.Category;

import java.util.List;

/**
 * Created by Mohammad on 4/28/2017.
 */
public interface CategoryDao {

    public boolean createAdmin(Category category);
    public boolean updateAdmin(Category category);
    public boolean deleteAdmin(Category category);
    public Category getCategoryById(long categoryId);
    public List<Category> getAllCategories();
}
