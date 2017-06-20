package com.coffeeshop;

import com.coffeeshop.PrinterService.PrintReceipt;
import com.coffeeshop.database.*;
import com.coffeeshop.model.Admin;
import com.coffeeshop.model.AdminSetting;
import com.coffeeshop.wrapper.FoodOrderWrapper;
import com.coffeeshop.wrapper.UserReceipt;
import org.eclipse.jdt.internal.core.SourceType;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Created by Mohammad on 4/28/2017.
 */
public class Test {

    public static void main(String [] args){

//        Date today =  new Date();
//        SimpleDateFormat sm = new SimpleDateFormat("yyyyMMdd");
//        String strDate = sm.format(today);
//        try {
//            today = sm.parse(strDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        java.sql.Date tDate = new java.sql.Date(today.getTime());
//        System.out.println(tDate.toString());

        OrderDetailDaoImp orderDetailDaoImp = new OrderDetailDaoImp();
        System.out.println(orderDetailDaoImp.getTodayOrderPaid().size());
//        String to = "2noori759@gmail.com";//change accordingly
//        String from = "am.m.noori@gmail.com";
//        String host = "8.8.8.8";//or IP address
//
//        //Get the session object
//        Properties properties = System.getProperties();
//        properties.setProperty("mail.smtp.host", host);
//        Session session = Session.getDefaultInstance(properties);
//
//        //compose the message
//        try{
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(from));
//            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
//            message.setSubject("Ping");
//            message.setText("Hello, this is example of sending email  ");
//
//            // Send message
//            Transport.send(message);
//            System.out.println("message sent successfully....");
//
//        }catch (MessagingException mex) {mex.printStackTrace();}
    }
//    public static void main(String[] args) {
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.socketFactory.class",
//                "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "465");
//
//        Session session = Session.getDefaultInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication("username","password");
//                    }
//                });
//
//        try {
//
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("work.noori.amir72@gmail.com"));
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse("am.m.noori@gmail.com"));
//            message.setSubject("Testing Subject");
//            message.setText("Dear Mail Crawler," +
//                    "\n\n No spam to my email, please!");
//
//            Transport.send(message);
//
//            System.out.println("Done");
//
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    public static void main(String[] args) {
//
//        FoodDaoImp foodDaoImp = new FoodDaoImp();
//        AdminSettingDaoImp adminSettingDaoImp = new AdminSettingDaoImp();
//
//        foodDaoImp.getAllFoods();
//        adminSettingDaoImp.getAllAdminSettings();
//
////        PrintReceipt printReceipt;
////        printReceipt = new PrintReceipt();
////        UserReceipt userReceipt = new UserReceipt();
////        userReceipt.setDate(new Date());
////        userReceipt.setOrderDetailIdWrapper(123);
////        userReceipt.setTrackNumber(1234);
////        userReceipt.setTotalprice(1200);
////        List<FoodOrderWrapper> foodOrderWrappers = new ArrayList<FoodOrderWrapper>();
////        for (int i=0 ; i<12;i++)
////        {
////            FoodOrderWrapper foodOrderWrapper = new FoodOrderWrapper();
////            foodOrderWrapper.setFoodName("غذل".concat(String.valueOf(i)));
////            foodOrderWrapper.setFoodOrderWrapperId(i);
////            foodOrderWrapper.setPrice(i);
////            foodOrderWrapper.setQuantity(i);
////            foodOrderWrapper.setTotalPrice(i*i);
////            foodOrderWrappers.add(foodOrderWrapper);
////        }
////        userReceipt.setFoodOrderWrapperList(foodOrderWrappers);
////        printReceipt.printUserReceipt("noori",userReceipt);
//
////        AdminDaoImp adminDaoImp=new AdminDaoImp();
////        Admin adminByUsernameAndPassword = adminDaoImp.getAdminByUsernameAndPassword("asd", "asd");
////        System.out.println(adminByUsernameAndPassword.getUsername());
//    }
}
