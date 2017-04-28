package com.coffeeshop.database;

import com.coffeeshop.model.Admin;

/**
 * Created by Mohammad on 4/28/2017.
 */
public class AdminDaoImp implements AdminDao {

    private SQLService sqlService;

    public AdminDaoImp(){
        sqlService=new SQLService();
    }

    public boolean createAdmin(Admin admin) {
        boolean done = sqlService.create(admin);
        return done;
    }

    public boolean updateAdmin(Admin admin) {
        boolean done=sqlService.update(admin);
        return done;
    }

    public boolean deleteAdmin(Admin admin) {
        boolean done=sqlService.delete(admin);
        return done;
    }

    public Admin getAdminByUsernameAndPassword(String username, String password) {
        Admin admin = (Admin) sqlService.getObjectsByTwoSpecialColumns
                (new Admin(), "username", username, "password", password).get(0);
        return admin;
    }
}
