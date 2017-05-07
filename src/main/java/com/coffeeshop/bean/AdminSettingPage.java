package com.coffeeshop.bean;

import com.coffeeshop.database.AdminSettingDaoImp;
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

    AdminSettingDaoImp adminSettingDaoImp;
    List<AdminSetting> adminSettings;
    AdminSetting adminSetting;

    @PostConstruct
    public void init(){
        adminSettingDaoImp = new AdminSettingDaoImp();
        adminSettings = adminSettingDaoImp.getAllAdminSettings();
        adminSetting = (AdminSetting) adminSettingDaoImp.getAdminSettingById(1);
    }

    public List<AdminSetting> showStaf(){
        return null;
    }

}
