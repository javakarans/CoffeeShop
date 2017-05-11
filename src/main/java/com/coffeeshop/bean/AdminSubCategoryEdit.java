package com.coffeeshop.bean;

import com.coffeeshop.database.CategoryDaoImp;
import com.coffeeshop.database.SubcategoryDaoImp;
import com.coffeeshop.model.Category;
import com.coffeeshop.model.Subcategory;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by H&H on 5/1/2017.
 */
@ManagedBean
@ViewScoped
public class AdminSubCategoryEdit implements Serializable{

    private static String image_location = "C:\\Users\\H&H\\Desktop\\coffeeshop\\image";

    private List<Category> categories;
    private List<Subcategory> subcategories;
    private Subcategory newSubcategory;
    private SubcategoryDaoImp subcategoryDaoImp;
    private Category selectedCategory;
    private CategoryDaoImp categoryDaoImp;
    private UploadedFile uploadedImage;
    private String uniqueID ;


    @PostConstruct
    private void init() {
        subcategoryDaoImp = new SubcategoryDaoImp();
        categoryDaoImp = new CategoryDaoImp();
        categories = categoryDaoImp.getAllCategories();
        subcategories = subcategoryDaoImp.getAllSubCategory();
        newSubcategory = new Subcategory();
        uniqueID = UUID.randomUUID().toString();
    }

    public void onChange() {
        if (selectedCategory!=null)
        {
            subcategories = subcategoryDaoImp.getSubCategoriesByCategoryId(selectedCategory.getCategoryId());
            newSubcategory.setCategoryId(selectedCategory.getCategoryId());
        }
    }

    public void save() {

        FacesMessage msg = null;
        if (uploadedImage== null){

            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Image is empty", "Image must be uploaded!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        if(newSubcategory.getName()==null || newSubcategory.getName().equals("")){
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Name is empty", "Pleas enter subcategory name!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        if(uploadedImage != null && newSubcategory.getName()!=null && !newSubcategory.getName().equals("")
                && subcategoryDaoImp.createSubcategory(newSubcategory)){
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Done", "data saved successfully!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }


    public void uploadImageHandler(FileUploadEvent event){
        this.uploadedImage = event.getFile();
        String[] tokens = uploadedImage.getFileName().split("\\.(?=[^\\.]+$)");
        String fileExtention = tokens[1];
        try {
        InputStream inputStream = event.getFile().getInputstream();
        String filename = image_location+uniqueID+"."+fileExtention;
        Path des = Paths.get(filename);
            Files.copy(inputStream,des);
            newSubcategory.setImageLocation(filename);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("done");

    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        System.out.println(event.getRowKey().toString());
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }


    public Subcategory getNewSubcategory() {
        return newSubcategory;
    }

    public void setNewSubcategory(Subcategory newSubcategory) {
        this.newSubcategory = newSubcategory;
    }

    public Category getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Category selectedCategory) {
        this.selectedCategory = selectedCategory;
    }
}

