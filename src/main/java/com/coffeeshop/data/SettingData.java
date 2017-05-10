package com.coffeeshop.data;

import com.coffeeshop.database.AdminSettingDaoImp;
import com.coffeeshop.database.HibernateUtil;
import com.coffeeshop.model.AdminSetting;

import java.util.List;

/**
 * Created by amir on 5/10/2017.
 */
public class SettingData {

    private static SettingData settingData;
    private AdminSettingDaoImp adminSettingDaoImp;
    private AdminSetting adminSetting;


    private SettingData()
    {
        adminSettingDaoImp = new AdminSettingDaoImp();
        List<AdminSetting> result = adminSettingDaoImp.getAdminSettingById(1);
        if (!result.isEmpty())
            adminSetting=result.get(0);
        else
            adminSetting = new AdminSetting();
    }

    public static SettingData getInstance()
    {
        synchronized (SettingData.class){
            if(settingData==null){
                settingData=new SettingData();
            }
        }
        return settingData;
    }

    public int getTrackNumber()
    {
        int currentTrackNumber = adminSetting.getTrackNumber();
        adminSetting.setTrackNumber(currentTrackNumber++);
        adminSettingDaoImp.updateAdminSetting(adminSetting);
        return currentTrackNumber;
    }
}
