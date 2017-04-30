package com.coffeeshop;

import com.coffeeshop.database.AdminDaoImp;
import com.coffeeshop.database.HibernateUtil;
import com.coffeeshop.model.Admin;
import org.hibernate.Session;

/**
 * Created by Mohammad on 4/28/2017.
 */
public class Test {

    public static void main(String[] args) {

        AdminDaoImp adminDaoImp=new AdminDaoImp();
        Admin adminByUsernameAndPassword = adminDaoImp.getAdminByUsernameAndPassword("asd", "asd");
        System.out.println(adminByUsernameAndPassword.getUsername());
    }
}
