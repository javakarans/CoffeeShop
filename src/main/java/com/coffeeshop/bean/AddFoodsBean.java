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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import java.io.*;
import java.nio.file.*;
import java.util.UUID;

@ManagedBean
@ViewScoped
public class AddFoodsBean implements Serializable{
    private static String image_location = StaticSettings.imageUrl;
    private FoodDaoImp foodDaoImp;
    private Food food;
    private SubcategoryDaoImp subcategoryDaoImp;
    private SubCategoryWrapper selectedSubCategoryWrapper;
    private List<SubCategoryWrapper> subCategoryWrapperList;
    private UploadedFile uploadedFile;
    private List<Food> foodList;
    private boolean editTable=true;
    private KitchenDaoImp kitchenDaoImp;
    private List<com.coffeeshop.model.Kitchen> kitchenList;
    private String uniqueID ;
    private Food selectedFood;

    @PostConstruct
    public void init()
    {
        selectedFood = new Food();
        kitchenDaoImp = new KitchenDaoImp();
        kitchenList = kitchenDaoImp.getAllKitchen();
        editTable=false;
        foodDaoImp = new FoodDaoImp();
        food = new Food();
        uniqueID = UUID.randomUUID().toString();
        subCategoryWrapperList = new ArrayList<>();
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
        uniqueID = UUID.randomUUID().toString();
        this.uploadedFile = event.getFile();
        String[] tokens = uploadedFile.getFileName().split("\\.(?=[^\\.]+$)");
        String fileExtention = tokens[1];
        try {
            InputStream inputStream = event.getFile().getInputstream();
            String filename = image_location+uniqueID+"."+fileExtention;
            Path des = Paths.get(filename);
            Files.copy(inputStream,des);
            food.setImageUrl(filename);
            food.setSubCategoryId(selectedSubCategoryWrapper.getSubCategoryId());

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("done");
    }

    public void updateFoodList()
    {
        if (selectedSubCategoryWrapper!=null)
        foodList = foodDaoImp.getFoodsBySubCategoryId(selectedSubCategoryWrapper
                .getSubCategoryId());
        else
            foodList = foodDaoImp.getAllFoods();
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
        FacesMessage msg = null;
        if (uploadedFile== null){

            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Image is empty", "Image must be uploaded!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        if(food.getName()==null || food.getName().equals("")){
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Name is empty", "Pleas enter subcategory name!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        if(uploadedFile != null && food.getName()!=null && !food.getName().equals("")
                && foodDaoImp.createFood(food)){
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Done", "data saved successfully!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            foodList = foodDaoImp.getFoodsBySubCategoryId(selectedSubCategoryWrapper.getSubCategoryId());
        }
    }

    public void processFileUploadEdit(FileUploadEvent event) throws IOException {
        uniqueID = UUID.randomUUID().toString();
        this.uploadedFile = event.getFile();
        String[] tokens = uploadedFile.getFileName().split("\\.(?=[^\\.]+$)");
        String fileExtention = tokens[1];
        try {
            InputStream inputStream = event.getFile().getInputstream();
            String filename = image_location+uniqueID+"."+fileExtention;
            Path des = Paths.get(filename);
            Files.copy(inputStream,des);
            selectedFood.setImageUrl(filename);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("done");
    }

    public void editFood()
    {
        FacesMessage facesMessage;
        System.out.println("salam");
        boolean result = foodDaoImp.updateFood(selectedFood);
        if (result)
        {
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Done", "data update successfully!");
            foodList = foodDaoImp.getFoodsBySubCategoryId(selectedSubCategoryWrapper.getSubCategoryId());
            System.out.println("1111111111111111111111111111111111111111");
        }
        else
        {
            facesMessage= new FacesMessage(FacesMessage.SEVERITY_ERROR, "UnSuccessFully", "Pleas enter subcategory name!");
        }
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        foodList = foodDaoImp.getFoodsBySubCategoryId(selectedSubCategoryWrapper.getSubCategoryId());

    }

    public void removeFood(Food food)
    {
        foodDaoImp.deleteFood(food);
        if(selectedSubCategoryWrapper!=null){
            foodList = foodDaoImp.getFoodsBySubCategoryId(selectedSubCategoryWrapper.getSubCategoryId());
        }
        else {
            foodList=foodDaoImp.getAllFoods();
        }
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

    public Food getSelectedFood() {
        return selectedFood;
    }

    public void setSelectedFood(Food selectedFood) {
        this.selectedFood = selectedFood;
    }
}
