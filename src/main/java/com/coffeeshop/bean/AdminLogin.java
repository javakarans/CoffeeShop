package com.coffeeshop.bean;

import com.coffeeshop.database.AdminDaoImp;
import com.coffeeshop.model.Admin;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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


    @PostConstruct
    public void init()
    {
        adminDaoImp = new AdminDaoImp();
    }

    public void login(){
        admin = adminDaoImp.getAdminByUsername(username);
//        if (admin != null){
//            System.out.println("salam");
//        }
//        else {
//            System.out.println("khodafez");
//        }
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



}
