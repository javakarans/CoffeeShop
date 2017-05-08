package com.coffeeshop.bean;

import com.coffeeshop.database.AdminDaoImp;
import com.coffeeshop.database.AdminSettingDaoImp;
import com.coffeeshop.model.Admin;
import com.coffeeshop.model.AdminSetting;
import net.bootsfaces.render.A;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
    private Admin admin;
    private AdminSetting adminSetting;
    private int trackingNumber;
    private String imageUrl;

    @PostConstruct
    public void init(){
        adminSettingDaoImp = new AdminSettingDaoImp();
        adminDaoImp = new AdminDaoImp();
//        adminSettings = adminSettingDaoImp.getAllAdminSettings();
        adminSetting = (AdminSetting) adminSettingDaoImp.getAdminSettingById(1);
        admins = adminDaoImp.getAllAdmins();
        showStaf();
    }

    public void showStaf(){
        for(int i = 0; i < admins.size(); i++){
            if(Integer.parseInt(admins.get(i).getRole()) != 1){
                staf.add(admins.get(i));
            }
        }
    }

    public void EditAdminSetting(){
        adminSetting.setTrackNumber(trackingNumber);
        adminSetting.setImageUrl(imageUrl);
        adminSettingDaoImp.updateAdminSetting(adminSetting);
    }

    public boolean CreateAdmin(){
        return adminDaoImp.createAdmin(admin);
    }

    public boolean updateAmin(){
        return adminDaoImp.updateAdmin(admin);
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

    public int getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(int trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
