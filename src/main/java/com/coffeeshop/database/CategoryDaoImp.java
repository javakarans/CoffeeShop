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

    public boolean createCategory(Category category) {
        boolean done = sqlService.create(category);
        return done;
    }

    public boolean updateCategory(Category category) {
        boolean done = sqlService.update(category);
        return done;
    }

    public boolean deleteCategory(Category category) {
        boolean done = sqlService.delete(category);
        return done;
    }

    public Category getCategoryById(long categoryId) {
        List result = sqlService.getObjectsBySpecialColumn(new Category(), "categoryId", categoryId);
        if(!result.isEmpty()){
            return (Category) result.get(0);
        }
        return new Category();
    }

    public List<Category> getAllCategories() {
        List<Category> categories = sqlService.getAllObjects(new Category());
        return categories;
    }
}
