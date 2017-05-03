package com.coffeeshop.bean;

import com.coffeeshop.database.AdminDaoImp;
import com.coffeeshop.model.Admin;
import com.coffeeshop.validator.ValidatorMessage;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Map;

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
    private Map<String, String> urlParam;
    private String validatorMessage;


    @PostConstruct
    public void init()
    {
        adminDaoImp = new AdminDaoImp();
        getURLParam();
        checkLogin();
    }

    public void getURLParam(){
        urlParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        System.out.println(urlParam.get("login"));
    }

    public void checkLogin(){
        if(urlParam.get("login")!=null&&urlParam.get("login").equals("false")){
            validatorMessage=ValidatorMessage.getInstance().getHashMap().get(1);
        }else {
            validatorMessage="";
        }
    }

    public String login() {
        validatorMessage="";
        if(username != null){
            List admins = adminDaoImp.getAdminByUsername(username);
            if (!admins.isEmpty()){
                admin = (Admin) admins.get(0);
                if(admin.getPassword().equals(password)){
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("authority","true");
                    return "onPendingTransaction.xhtml?faces-redirect=true";
                }
                else {
                    return "AdminLogin.xhtml?faces-redirect=true&login=false";
                }
            }
            else {
                return "AdminLogin.xhtml?faces-redirect=true&login=false";
            }
        }
        return "";
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
