package com.coffeeshop.database;

import com.coffeeshop.model.AdminSetting;

import java.util.List;

/**
 * Created by Amirhossein on 5/7/2017.
 */
public class AdminSettingDaoImp implements AdminSettinDao{

    private SQLService sqlService;

    public AdminSettingDaoImp(){
        sqlService = new SQLService();
    }

    public boolean createAdminSetting(AdminSetting adminSetting) {
        return sqlService.create(adminSetting);
    }

    public boolean updateAdminSetting(AdminSetting adminSetting) {
        return sqlService.update(adminSetting);
    }

    public boolean deleteAdminSetting(AdminSetting adminSetting) {
        return sqlService.delete(adminSetting);
    }

    public List<AdminSetting> getAllAdminSettings() {
        List<AdminSetting> result = sqlService.getAllObjects(new AdminSetting());
        return result;
    }

    //for one adminsetting
    public List<AdminSetting> getAdminSettingById(long adminSettingId) {
        List<AdminSetting> result = sqlService.getObjectsBySpecialColumn(new AdminSetting(), "adminSettingId", adminSettingId);
        return result;
    }
}
