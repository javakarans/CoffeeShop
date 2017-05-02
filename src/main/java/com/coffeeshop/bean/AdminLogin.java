package com.coffeeshop.bean;

import com.coffeeshop.database.AdminDaoImp;
import com.coffeeshop.model.Admin;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;

/**
 * Created by H&H on 4/30/2017.
 */
@ManagedBean
@ViewScoped
public class AdminLogin {

    private AdminDaoImp adminDaoImp;
    private Admin admin;
    private String username;
    private String password;
    private String validatorMessage;


    @PostConstruct
    public void init()
    {
        adminDaoImp = new AdminDaoImp();
    }

    public String login() {
        validatorMessage = "";
        if(username != null){
            List admins = adminDaoImp.getAdminByUsername(username);
            if (!admins.isEmpty()){
                admin = new Admin();
                admin = (Admin) admins.get(0);
                if(admin.getPassword().equals(password)){
                    return "mainAdmin.xhtml?faces-redirect=true";
                }
                else {
                    validatorMessage = "نام کاربری یا کلمه عبور نادرست است";
                }
            }
            else {
                validatorMessage = "نام کاربری یا کلمه عبور نادرست است";
            }
        }
        return validatorMessage;
    }

    public AdminDaoImp getAdminDaoImp() {
        return adminDaoImp;
    }

    public void setAdminDaoImp(AdminDaoImp adminDaoImp) {
        this.adminDaoImp = adminDaoImp;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValidatorMessage() {
        return validatorMessage;
    }

    public void setValidatorMessage(String validatorMessage) {
        this.validatorMessage = validatorMessage;
    }

}
