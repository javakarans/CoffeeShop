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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hibernate.internal.util.io.StreamCopier.BUFFER_SIZE;

/**
 * Created by H&H on 4/30/2017.
 */
@ManagedBean
@SessionScoped
public class AdminEditMenu  implements Serializable {

    private Category category;
    private Subcategory subcategory;
    private List<Category> categoryList;
    private List<Subcategory> subcategories;
    private CategoryDaoImp categoryDaoImp;
    private SubcategoryDaoImp subcategoryDaoImp;
    private long selectedCategoryid;
    private String filePath;
    private static final String url = "C:\\Users\\amir\\Desktop\\image" ;


    @PostConstruct
    public void init() {
        categoryDaoImp = new CategoryDaoImp();
        subcategoryDaoImp = new SubcategoryDaoImp();
        category = new Category();
        subcategory = new Subcategory();
        categoryList = categoryDaoImp.getAllCategories();

    }



    public void saveCategory() {
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

    private void handleFileUpload(UploadedFile uploaded, String location) throws IOException {
        location = url;
        File result = new File(location);

        //file.mkdirs(); //!wrong
        result.getParentFile().mkdirs();//!correct
        if (!result.exists()){
            result.createNewFile();
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(result);

            byte[] buffer = new byte[BUFFER_SIZE];

            int bulk;
            InputStream inputStream = uploaded.getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }

            fileOutputStream.close();
            inputStream.close();

            FacesMessage msg = new FacesMessage("Succesful", uploaded.getFileName()
                    + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (IOException e1) {
            e1.printStackTrace();

            FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "The files were not uploaded!", "");
            FacesContext.getCurrentInstance().addMessage(null, error);
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

    public void saveSubCategory() {
        boolean result = subcategoryDaoImp.createSubcategory(subcategory);
        subcategory = new Subcategory();
    }

    public void uploadLargeImage(FileUploadEvent largeImageUploded) throws IOException {
        String uniqueID = UUID.randomUUID().toString();
        if (largeImageUploded!=null)
        {
            System.out.println("gholam");
            handleFileUpload(largeImageUploded.getFile(),"");
        }
        else
        {
            //show message in xhtml
        }
    }

    public void uploadSmallImage(FileUploadEvent smallImageUploded) {
        String uniqueID = UUID.randomUUID().toString();
        if (smallImageUploded != null) {

        } else {
            //show message in xhtml
        }
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
