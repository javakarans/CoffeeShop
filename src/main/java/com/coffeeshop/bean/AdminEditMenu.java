package com.coffeeshop.bean;

import com.coffeeshop.database.CategoryDaoImp;
import com.coffeeshop.database.SubcategoryDaoImp;
import com.coffeeshop.model.Category;
import com.coffeeshop.model.Subcategory;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServlet;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hibernate.internal.util.io.StreamCopier.BUFFER_SIZE;

/**
 * Created by H&H on 4/30/2017.
 */
@ManagedBean
@ViewScoped
public class AdminEditMenu  implements Serializable {

    private Category category;
    private Subcategory subcategory;
    private List<Category> categoryList;
    private List<Subcategory> subcategories;
    private CategoryDaoImp categoryDaoImp;
    private SubcategoryDaoImp subcategoryDaoImp;
    private long selectedCategoryid;
    private UploadedFile uploadedFile;
    private String categoryImageLocation;

    @PostConstruct
    public void init() {
        categoryDaoImp = new CategoryDaoImp();
        subcategoryDaoImp = new SubcategoryDaoImp();
        category = new Category();
        subcategory = new Subcategory();
        categoryList = categoryDaoImp.getAllCategories();

    }

    public void processFileUpload(FileUploadEvent event) throws IOException {
        uploadedFile = event.getFile();
        InputStream inputStream = uploadedFile.getInputstream();
        String uniqueID = UUID.randomUUID().toString();
        categoryImageLocation = uniqueID.concat(uniqueID).concat(uploadedFile.getFileName());
        Path des = Paths.get(StaticSettings.imageUrl.concat(categoryImageLocation));
        //if directory exists?
        try {
            Files.copy(inputStream,des);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCategory() {
        category.setLargeDeviceImageUrl(categoryImageLocation);
        boolean result = categoryDaoImp.createCategory(category);
        if (result) {
            categoryList.add(category);
            FacesMessage msg = new FacesMessage("Succesful", category.getName()
                    + " is saves.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "can not save the category with name : ", category.getName());
            FacesContext.getCurrentInstance().addMessage(null, error);
        }
        category = new Category();
    }

    public void removeCategory(Category category)
    {
        boolean result = categoryDaoImp.deleteCategory(category);
        if (result) {
            categoryList.remove(category);
            //show message
            FacesMessage msg = new FacesMessage("Succesful", category.getName()
                    + " is removed.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "can not remove the category with name : ", category.getName());
            FacesContext.getCurrentInstance().addMessage(null, error);
            //show message
        }
    }

    public void saveSubCategory() {

        boolean result = subcategoryDaoImp.createSubcategory(subcategory);
        subcategory = new Subcategory();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }

    public long getSelectedCategoryid() {
        return selectedCategoryid;
    }

    public void setSelectedCategoryid(long selectedCategoryid) {
        selectedCategoryid = selectedCategoryid;
    }
}
