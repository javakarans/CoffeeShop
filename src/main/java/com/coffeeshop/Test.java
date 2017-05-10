package com.coffeeshop;

import com.coffeeshop.PrinterService.PrintReceipt;
import com.coffeeshop.database.AdminDaoImp;
import com.coffeeshop.database.AdminSettingDaoImp;
import com.coffeeshop.database.FoodDaoImp;
import com.coffeeshop.database.HibernateUtil;
import com.coffeeshop.model.Admin;
import com.coffeeshop.model.AdminSetting;
import com.coffeeshop.wrapper.FoodOrderWrapper;
import com.coffeeshop.wrapper.UserReceipt;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mohammad on 4/28/2017.
 */
public class Test {

    public static void main(String[] args) {

        FoodDaoImp foodDaoImp = new FoodDaoImp();
        AdminSettingDaoImp adminSettingDaoImp = new AdminSettingDaoImp();

        foodDaoImp.getAllFoods();
        adminSettingDaoImp.getAllAdminSettings();

//        PrintReceipt printReceipt;
//        printReceipt = new PrintReceipt();
//        UserReceipt userReceipt = new UserReceipt();
//        userReceipt.setDate(new Date());
//        userReceipt.setOrderDetailIdWrapper(123);
//        userReceipt.setTrackNumber(1234);
//        userReceipt.setTotalprice(1200);
//        List<FoodOrderWrapper> foodOrderWrappers = new ArrayList<FoodOrderWrapper>();
//        for (int i=0 ; i<12;i++)
//        {
//            FoodOrderWrapper foodOrderWrapper = new FoodOrderWrapper();
//            foodOrderWrapper.setFoodName("غذل".concat(String.valueOf(i)));
//            foodOrderWrapper.setFoodOrderWrapperId(i);
//            foodOrderWrapper.setPrice(i);
//            foodOrderWrapper.setQuantity(i);
//            foodOrderWrapper.setTotalPrice(i*i);
//            foodOrderWrappers.add(foodOrderWrapper);
//        }
//        userReceipt.setFoodOrderWrapperList(foodOrderWrappers);
//        printReceipt.printUserReceipt("noori",userReceipt);

//        AdminDaoImp adminDaoImp=new AdminDaoImp();
//        Admin adminByUsernameAndPassword = adminDaoImp.getAdminByUsernameAndPassword("asd", "asd");
//        System.out.println(adminByUsernameAndPassword.getUsername());
    }
}
