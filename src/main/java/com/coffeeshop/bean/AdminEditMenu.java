package com.coffeeshop.bean;

import com.coffeeshop.database.CategoryDaoImp;
import com.coffeeshop.database.SubcategoryDaoImp;
import com.coffeeshop.model.Category;
import com.coffeeshop.model.Subcategory;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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

    private static String image_location = StaticSettings.imageUrl;
    private Category category;
    private Subcategory subcategory;
    private List<Category> categoryList;
    private List<Subcategory> subcategories;
    private CategoryDaoImp categoryDaoImp;
    private SubcategoryDaoImp subcategoryDaoImp;
    private long selectedCategoryid;
    private UploadedFile uploadedFile;
    private String categoryImageLocation;
    private UploadedFile uploadedImage;
    private String uniqueID ;

    @PostConstruct
    public void init() {
        categoryDaoImp = new CategoryDaoImp();
        subcategoryDaoImp = new SubcategoryDaoImp();
        category = new Category();
        subcategory = new Subcategory();
        categoryList = categoryDaoImp.getAllCategories();
        uniqueID = UUID.randomUUID().toString();
        System.out.println("init");
    }

    public void processFileUpload(FileUploadEvent event) throws IOException {
        this.uploadedImage = event.getFile();
        String[] tokens = uploadedImage.getFileName().split("\\.(?=[^\\.]+$)");
        String fileExtention = tokens[1];
        try {
            InputStream inputStream = event.getFile().getInputstream();
            String filename = image_location+uniqueID+"."+fileExtention;
            Path des = Paths.get(filename);
            Files.copy(inputStream,des);
            category.setLargeDeviceImageUrl(filename);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("done");
    }

    public void saveCategory() {
        FacesMessage msg = null;
        if (uploadedImage== null){

            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Image is empty", "Image must be uploaded!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        if(category.getName()==null || category.getName().equals("")){
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Name is empty", "Pleas enter subcategory name!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        if(uploadedImage != null && category.getName()!=null && !category.getName().equals("")
                && categoryDaoImp.createCategory(category)){
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Done", "data saved successfully!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            categoryList = categoryDaoImp.getAllCategories();
        }
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

    //cat
    public void showCategoryUpdateModal(Category category){
        this.category = category;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("$('#updateCategory').modal()");
    }

    public String updateCategory(){
        boolean result = categoryDaoImp.updateCategory(category);
        return "category.xhtml?faces-redirect=true";
    }

    //subcat
    public void showSubCategoryUpdateModal(Subcategory subcategory){
        this.subcategory = subcategory;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("$('#updateSubCategory').modal()");
    }

    public String updateSubCategory(){
        boolean result = subcategoryDaoImp.updateSubcategory(subcategory);
        return "subCategory.xhtml?faces-redirect=true";
    }

    public void showSubCategoryDeleteModal(Subcategory subcategory){
        this.subcategory = subcategory;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("$('#deleteSubCategory').modal()");
    }

    public String deleteSubCategory(){
        boolean result = subcategoryDaoImp.deleteSubcategory(subcategory);
        System.out.println(subcategory.getName());
        System.out.println(result);
        return "subCategory.xhtml?faces-redirect=true";
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
