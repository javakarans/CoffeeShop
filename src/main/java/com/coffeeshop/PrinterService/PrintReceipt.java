package com.coffeeshop.PrinterService;

import com.coffeeshop.database.AdminDaoImp;
import com.coffeeshop.database.AdminSettingDaoImp;
import com.coffeeshop.model.AdminSetting;
import com.coffeeshop.wrapper.FoodOrderWrapper;
import com.coffeeshop.wrapper.KitchenReceipt;
import com.coffeeshop.wrapper.UserReceipt;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by H&H on 5/7/2017.
 */
public class PrintReceipt {

    public PrintReceipt()
    {

    }

public boolean printUserReceipt(String printerName, UserReceipt userReceipt){

    PrinterService service = new PrinterService();

    String sourceFileName =
            this.getClass().getClassLoader().getResource("usertemp.jasper").getPath();


    List<FoodOrderWrapper> foodOrderWrappers = new ArrayList<FoodOrderWrapper>();
    foodOrderWrappers.add(null);
    foodOrderWrappers.addAll(userReceipt.getFoodOrderWrapperList());

    JRBeanCollectionDataSource beanColDataSource = new
            JRBeanCollectionDataSource(foodOrderWrappers);

    AdminSettingDaoImp adminSettingDaoImp = new AdminSettingDaoImp();
    List<AdminSetting> adminSettings = adminSettingDaoImp.getAdminSettingById(1);

    Map parameters = new HashMap();
    parameters.put("info", beanColDataSource);
    parameters.put("trackingNumber", userReceipt.getTrackNumber());
    parameters.put("tprice", userReceipt.getTotalprice());
    parameters.put("AdminSetting",adminSettings.get(0));
    JasperPrint jasperPrint = null;
    try {
        jasperPrint = JasperFillManager.fillReport(
                sourceFileName, parameters, beanColDataSource);
    } catch (JRException e) {
        e.printStackTrace();
        return false;
    }

    return service.print(jasperPrint,printerName);
}

    public boolean printKitchenReceipt(KitchenReceipt kitchenReceipt){

        PrinterService service = new PrinterService();

        String sourceFileName =
                this.getClass().getClassLoader().getResource("kitchentemp.jasper").getPath();


        List<FoodOrderWrapper> foodOrderWrappers = new ArrayList<FoodOrderWrapper>();
        foodOrderWrappers.add(null);
        foodOrderWrappers.addAll(kitchenReceipt.getFoodOrderWrapperList());

        JRBeanCollectionDataSource beanColDataSource = new
                JRBeanCollectionDataSource(foodOrderWrappers);
        Map parameters = new HashMap();
        parameters.put("info", beanColDataSource);
        parameters.put("trackingNumber", kitchenReceipt.getTrackNumber());
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport(
                    sourceFileName, parameters, beanColDataSource);
        } catch (JRException e) {
            e.printStackTrace();
            return false;
        }

        return service.print(jasperPrint,kitchenReceipt.getPrinterName());
    }

//    public static void main(String[] args) {
//        List<FoodOrderWrapper> foodOrderWrappers = new ArrayList<FoodOrderWrapper>();
//
//        FoodOrderWrapper f1 = new FoodOrderWrapper("سالاد",2,1000,2000);
//        FoodOrderWrapper f2 = new FoodOrderWrapper("سالاد",2,1000,2000);
//        FoodOrderWrapper f3 = new FoodOrderWrapper("سالاد",2,1000,2000);
//
//        foodOrderWrappers.add(f1);
//        foodOrderWrappers.add(f2);
//        foodOrderWrappers.add(f3);
//
//        UserReceipt userReceipt = new UserReceipt();
//        userReceipt.setFoodOrderWrapperList(foodOrderWrappers);
//        userReceipt.setTotalprice(6000);
//        userReceipt.setTrackNumber(85);
//
//        KitchenReceipt kitchenReceipt = new KitchenReceipt();
//        kitchenReceipt.setTrackNumber(85);
//        kitchenReceipt.setFoodOrderWrapperList(foodOrderWrappers);
//        PrintReceipt printReceipt = new PrintReceipt();
//        printReceipt.printUserReceipt("Foxit Reader PDF Printer",userReceipt);
//        printReceipt.printKitchenReceipt("Foxit Reader PDF Printer",kitchenReceipt);
//
//    }
}
