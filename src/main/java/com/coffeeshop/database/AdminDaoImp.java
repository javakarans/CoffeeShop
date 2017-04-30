package com.coffeeshop.database;

import com.coffeeshop.model.Admin;

import java.util.List;

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
        List result = sqlService.getObjectsByTwoSpecialColumns
                (new Admin(), "username", username, "password", password);
        if(!result.isEmpty()){
            return (Admin) result.get(0);
        }
        return new Admin();
    }

    public Admin getAdminByUsername(String username) {
        List result = sqlService.getObjectsBySpecialColumn(new Admin(), "username", username);
        if(!result.isEmpty()){
            return (Admin) result.get(0);
        }
        return new Admin();
    }
}
