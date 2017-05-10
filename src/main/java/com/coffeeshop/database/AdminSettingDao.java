package com.coffeeshop.database;

import com.coffeeshop.model.AdminSetting;

import java.util.List;

/**
 * Created by Amirhossein on 5/7/2017.
 */
public interface AdminSettingDao {
    public boolean createAdminSetting(AdminSetting adminSetting);
    public boolean updateAdminSetting(AdminSetting adminSetting);
    public boolean deleteAdminSetting(AdminSetting adminSetting);
    public List<AdminSetting> getAllAdminSettings();
    //get one adminsetting
    public List<AdminSetting> getAdminSettingById(long adminSettingId);
}
