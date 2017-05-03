package com.coffeeshop.bean;

import com.coffeeshop.database.FoodDaoImp;
import com.coffeeshop.database.SubcategoryDao;
import com.coffeeshop.database.SubcategoryDaoImp;
import com.coffeeshop.model.Food;
import com.coffeeshop.model.SubCategoryWrapper;
import com.coffeeshop.model.Subcategory;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;

@ManagedBean
@ViewScoped
public class AddFoodsBean implements Serializable{
    private FoodDaoImp foodDaoImp;
    private Food food;
    private SubcategoryDaoImp subcategoryDaoImp;
    private SubCategoryWrapper selectedSubCategoryWrapper;
    private List<SubCategoryWrapper> subCategoryWrapperList;
    private UploadedFile uploadedFile;
    private List<Food> foodList;
    private boolean editTable;


    @PostConstruct
    public void init()
    {
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
        System.out.println("done");
    }

    public void updateFoodList(ValueChangeEvent event)
    {
        System.out.println("gholam");
        foodList = foodDaoImp.getFoodsBySubCategoryId(selectedSubCategoryWrapper
                .getSubCategoryId());
    }

    public void editTable(){
        editTable=!editTable;
    }

    public void onChange()
    {
        foodList = foodDaoImp.getFoodsBySubCategoryId(selectedSubCategoryWrapper.getSubCategoryId());
        System.out.println("ridiiiiiiiiiiiiiiiiiii");
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
}
