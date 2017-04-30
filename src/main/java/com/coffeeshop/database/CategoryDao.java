package com.coffeeshop.database;

import com.coffeeshop.model.Category;

import java.util.List;

/**
 * Created by Mohammad on 4/28/2017.
 */
public interface CategoryDao {

    public boolean createCategory(Category category);
    public boolean updateCategory(Category category);
    public boolean deleteCategory(Category category);
    public Category getCategoryById(long categoryId);
    public List<Category> getAllCategories();
}
