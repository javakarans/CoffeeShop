package com.coffeeshop.database;

import com.coffeeshop.model.Admin;

/**
 * Created by Mohammad on 4/28/2017.
 */
public interface AdminDao {

    public boolean createAdmin(Admin admin);
    public boolean updateAdmin(Admin admin);
    public boolean deleteAdmin(Admin admin);
    public Admin getAdminByUsernameAndPassword(String username,String password);
}
