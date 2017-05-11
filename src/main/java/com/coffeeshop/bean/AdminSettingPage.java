package com.coffeeshop.bean;

import com.coffeeshop.database.AdminDaoImp;
import com.coffeeshop.database.AdminSettingDaoImp;
import com.coffeeshop.model.Admin;
import com.coffeeshop.model.AdminSetting;
import net.bootsfaces.render.A;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amirhossein on 5/7/2017.
 */
@ManagedBean
@ViewScoped
public class AdminSettingPage {

    private AdminSettingDaoImp adminSettingDaoImp;
    private AdminDaoImp adminDaoImp;
    private List<Admin> admins;
    private List<Admin> staf;
    private List<AdminSetting> adminSettings;
    private Admin admin;
    private AdminSetting adminSetting;

    @PostConstruct
    public void init(){
        adminSettingDaoImp = new AdminSettingDaoImp();
        adminDaoImp = new AdminDaoImp();
        admins = adminDaoImp.getAllAdmins();
        admin = new Admin();
        settingData();
        showStaf();
    }

    public void showStaf(){
        staf = new ArrayList<Admin>();
        for(int i = 0; i < admins.size(); i++){
            if(Integer.parseInt(admins.get(i).getRole()) != 1){
                staf.add(admins.get(i));
            }
        }
    }

    public void EditAdminSetting(){
        List<AdminSetting> allAdminSettings = adminSettingDaoImp.getAllAdminSettings();
        if (!allAdminSettings.isEmpty()){
            adminSettingDaoImp.updateAdminSetting(adminSetting);
        }
        else {
            adminSettingDaoImp.createAdminSetting(adminSetting);
        }
    }

    public void settingData()
    {
        List<AdminSetting> result = adminSettingDaoImp.getAdminSettingById(1);
        if (!result.isEmpty())
            adminSetting=result.get(0);
        else
            adminSetting = new AdminSetting();
    }

    public void showCreateAdminModal(){
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("$('#addStaff').modal()");
    }

    public String addStaff(){
        adminDaoImp.createAdmin(admin);
        return "adminSetting.xhtml?faces-redirect=true";
    }

    public void showUpdateAdminModal(){
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("$('#updateStaff').modal()");
    }

    public String updateAdmin(){
        adminDaoImp.updateAdmin(admin);
        return "adminSetting.xhtml?faces-redirect=true";
    }

    public void showDeleteAdminModal(){
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("$('.deleteStaff').modal()");
    }

    public boolean deleteAdmin(){
        return adminDaoImp.updateAdmin(admin);
    }


    public AdminSettingDaoImp getAdminSettingDaoImp() {
        return adminSettingDaoImp;
    }

    public void setAdminSettingDaoImp(AdminSettingDaoImp adminSettingDaoImp) {
        this.adminSettingDaoImp = adminSettingDaoImp;
    }

    public AdminDaoImp getAdminDaoImp() {
        return adminDaoImp;
    }

    public void setAdminDaoImp(AdminDaoImp adminDaoImp) {
        this.adminDaoImp = adminDaoImp;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(List<Admin> admins) {
        this.admins = admins;
    }

    public List<Admin> getStaf() {
        return staf;
    }

    public void setStaf(List<Admin> staf) {
        this.staf = staf;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public AdminSetting getAdminSetting() {
        return adminSetting;
    }

    public void setAdminSetting(AdminSetting adminSetting) {
        this.adminSetting = adminSetting;
    }


    public List<AdminSetting> getAdminSettings() {
        return adminSettings;
    }

    public void setAdminSettings(List<AdminSetting> adminSettings) {
        this.adminSettings = adminSettings;
    }

}
