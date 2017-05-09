package com.coffeeshop.database;

import com.coffeeshop.model.Admin;

import java.util.List;

/**
 * Created by Mohammad on 4/28/2017.
 */
public interface AdminDao {

    public boolean createAdmin(Admin admin);
    public boolean updateAdmin(Admin admin);
    public boolean deleteAdmin(Admin admin);
    public List<Admin> getAllAdmins();
    public Admin getAdminByUsernameAndPassword(String username,String password);
}
