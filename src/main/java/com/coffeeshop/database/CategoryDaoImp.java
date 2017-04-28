package com.coffeeshop.database;

import com.coffeeshop.model.Category;

import java.util.List;

/**
 * Created by Mohammad on 4/28/2017.
 */
public class CategoryDaoImp implements CategoryDao {

    private SQLService sqlService;

    public CategoryDaoImp(){
        sqlService=new SQLService();
    }

    public boolean createAdmin(Category category) {
        boolean done = sqlService.create(category);
        return done;
    }

    public boolean updateAdmin(Category category) {
        boolean done = sqlService.update(category);
        return done;
    }

    public boolean deleteAdmin(Category category) {
        boolean done = sqlService.delete(category);
        return done;
    }

    public Category getCategoryById(long categoryId) {
        Category category = (Category) sqlService.getObjectsBySpecialColumn(new Category(), "categoryId", categoryId).get(0);
        return category;
    }

    public List<Category> getAllCategories() {
        List<Category> categories = sqlService.getAllObjects(new Category());
        return categories;
    }
}
