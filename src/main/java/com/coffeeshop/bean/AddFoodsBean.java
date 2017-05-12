package com.coffeeshop.bean;

import com.coffeeshop.database.FoodDaoImp;
import com.coffeeshop.database.KitchenDaoImp;
import com.coffeeshop.database.SubcategoryDaoImp;
import com.coffeeshop.model.*;
import com.coffeeshop.model.Kitchen;
import com.coffeeshop.wrapper.SubCategoryWrapper;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import java.io.*;
import java.nio.file.*;

@ManagedBean
@SessionScoped
public class AddFoodsBean implements Serializable{
    private FoodDaoImp foodDaoImp;
    private Food food;
    private SubcategoryDaoImp subcategoryDaoImp;
    private SubCategoryWrapper selectedSubCategoryWrapper;
    private List<SubCategoryWrapper> subCategoryWrapperList;
    private UploadedFile uploadedFile;
    private List<Food> foodList;
    private boolean editTable;
    private KitchenDaoImp kitchenDaoImp;
    private List<com.coffeeshop.model.Kitchen> kitchenList;

    @PostConstruct
    public void init()
    {
        kitchenDaoImp = new KitchenDaoImp();
        kitchenList = kitchenDaoImp.getAllKitchen();
        editTable=false;
        foodDaoImp = new FoodDaoImp();
        food = new Food();
        subCategoryWrapperList = new ArrayList<SubCategoryWrapper>();
        subcategoryDaoImp = new SubcategoryDaoImp();
        List<Subcategory> subcategories = subcategoryDaoImp.getAllSubCategory();
        for (Subcategory subcategory : subcategories) {
            subCategoryWrapperList.add(new SubCategoryWrapper(subcategory));
        }
        foodList = foodDaoImp.getAllFoods();
    }

    public void addFood()
    {

    }

    public void processFileUpload(FileUploadEvent event) throws IOException {
        uploadedFile = event.getFile();
        InputStream inputStream = uploadedFile.getInputstream();

        Path des = Paths.get("C:/image/".concat(uploadedFile.getFileName()));
        //if directory exists?
        try {
            Files.copy(inputStream,des);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateFoodList(ValueChangeEvent event)
    {
        foodList = foodDaoImp.getFoodsBySubCategoryId(selectedSubCategoryWrapper
                .getSubCategoryId());
    }

    public void editTable(){
        editTable=!editTable;
    }

    public void onChange()
    {
        foodList = foodDaoImp.getFoodsBySubCategoryId(selectedSubCategoryWrapper.getSubCategoryId());
    }

    public void saveFood()
    {

    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public SubCategoryWrapper getSelectedSubCategoryWrapper() {
        return selectedSubCategoryWrapper;
    }

    public void setSelectedSubCategoryWrapper(SubCategoryWrapper selectedSubCategoryWrapper) {
        this.selectedSubCategoryWrapper = selectedSubCategoryWrapper;
    }

    public List<SubCategoryWrapper> getSubCategoryWrapperList() {
        return subCategoryWrapperList;
    }

    public void setSubCategoryWrapperList(List<SubCategoryWrapper> subCategoryWrapperList) {
        this.subCategoryWrapperList = subCategoryWrapperList;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public boolean isEditTable() {
        return editTable;
    }

    public void setEditTable(boolean editTable) {
        this.editTable = editTable;
    }

    public List<Kitchen> getKitchenList() {
        return kitchenList;
    }

    public void setKitchenList(List<Kitchen> kitchenList) {
        this.kitchenList = kitchenList;
    }
}
