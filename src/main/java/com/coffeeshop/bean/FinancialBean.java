package com.coffeeshop.bean;

import com.coffeeshop.database.AdminSettingDaoImp;
import com.coffeeshop.database.FoodDaoImp;
import com.coffeeshop.database.FoodOrderDaoImp;
import com.coffeeshop.database.OrderDetailDaoImp;
import com.coffeeshop.model.AdminSetting;
import com.coffeeshop.model.FoodOrder;
import com.coffeeshop.model.OrderDetail;
import com.coffeeshop.services.GmailSMTP;
import com.coffeeshop.wrapper.FoodExcel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Amirhossein on 5/11/2017.
 */
@ManagedBean
@ViewScoped
public class FinancialBean {

    private OrderDetailDaoImp orderDetailDaoImp;
    private List<OrderDetail> orderDetails;
    private double totalPrice;
    private GmailSMTP gmailSMTP;
    private AdminSettingDaoImp adminSettingDaoImp;
    private AdminSetting adminSetting;

    private Date today;
    private Date from;
    private Date to;

    @PostConstruct
    public void init(){
        checkAdminIsLogin();
        orderDetailDaoImp = new OrderDetailDaoImp();
        today = new Date();
        from = new Date();
        to = new Date();
        adminSettingDaoImp = new AdminSettingDaoImp();
        List admin = adminSettingDaoImp.getAllAdminSettings();
        if (!admin.isEmpty())
            adminSetting = (AdminSetting) admin.get(0);
        else adminSetting = new AdminSetting();
        gmailSMTP = new GmailSMTP(adminSetting.getGmailUser(),adminSetting.getGmailPassWord());
    }

    public String getAuthority(){
        return (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("authority");
    }

    public void checkAdminIsLogin(){
        String authority = getAuthority();
        System.out.println(authority);
        if(authority==null){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/admin/AdminLogin.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void calculateDurationSales(){
        Timestamp firstTimestamp = new Timestamp(from.getTime());
        Timestamp secondTimestamp = new Timestamp(to.getTime());
        orderDetails = orderDetailDaoImp.getOrderDetailListBetweenTwoTimestamp(firstTimestamp,secondTimestamp);
        System.out.println(orderDetails.size());
        totalPrice = 0;
        for (int i = 0; i <orderDetails.size(); i++){
                totalPrice += orderDetails.get(i).getTotalPrice();
        }
        createExcelFile(totalPrice,from.toString(),to.toString());
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("$('#result').modal()");
    }

    public void calculateTodaySales(){
        orderDetails = orderDetailDaoImp.getTodayOrderPaid();
        totalPrice = 0;
        for(int i = 0; i < orderDetails.size(); i++){
            totalPrice += orderDetails.get(i).getTotalPrice();
        }
        String date = "" ;
        if (!orderDetails.isEmpty())
            date = orderDetails.get(0).getDate().toString();
        //createExcelFile(totalPrice,date);
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("$('#todayResult').modal()");
    }

    private void createExcelFile(double totalPrice,String dateFrom,String dateTo)
    {
        FoodOrderDaoImp foodOrderDaoImp = new FoodOrderDaoImp();
        List<FoodOrder> foodOrderList = new ArrayList<>();
        for (OrderDetail orderDetail: orderDetails) {
            List<FoodOrder> foodOrders = foodOrderDaoImp.getFoodOrderWithOrderId(orderDetail.getOrderDetailId());
            foodOrderList.addAll(foodOrders);
        }
        System.out.println(foodOrderList.size()+"@@@@@@@@@@@@@@@@@@@@2");
        Set<Long> foodIds = new HashSet<>();
        for (FoodOrder foodOrder : foodOrderList)
        {
            foodIds.add(foodOrder.getFoodId());
        }
        System.out.println(foodIds.size()+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        List<FoodExcel> foodExcelList = new ArrayList<>();
        for(Long id : foodIds)
        {
            FoodExcel foodExcel = new FoodExcel();
            foodExcel.setFoodCount(0);
            foodExcel.setFoodId(id);
            for (FoodOrder foodOrder : foodOrderList)
            {
                if (foodOrder.getFoodId()==id)
                    foodExcel.setFoodCount(foodExcel.getFoodCount()+foodOrder.getQuantity());
            }
            foodExcelList.add(foodExcel);
        }
        fillExcel(foodExcelList,totalPrice,dateFrom,dateTo);
    }

    private void fillExcel(List<FoodExcel> foodExcelList,double totalPrice,String dateFrom,String dateTo)
    {
        FoodDaoImp foodDaoImp = new FoodDaoImp();
        System.out.println(foodExcelList.size()+"#####################");
        String fileLocation = StaticSettings.imageUrl + "DailyReport.xlsx";
        InputStream stream = null;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("DailyReport");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Branch Nameّ");
        header.createCell(3).setCellValue(adminSetting.getBranchNanme());
        Row firstHeader = sheet.createRow(1);
        firstHeader.createCell(0).setCellValue("Food IDّ");
        firstHeader.createCell(1).setCellValue("Food Nameّ");
        firstHeader.createCell(2).setCellValue("Food Priceّ");
        firstHeader.createCell(3).setCellValue("Food Qntّ");
        firstHeader.createCell(4).setCellValue("Total Price");
        int i = 2;
        for (FoodExcel foodExcel : foodExcelList )
        {
            Row nextRow = sheet.createRow(i);
            i++;
            nextRow.createCell(0).setCellValue(foodExcel.getFoodId());
            nextRow.createCell(1).setCellValue(foodDaoImp.getFoodByFoodId(foodExcel.getFoodId()).getName());
            nextRow.createCell(2).setCellValue(foodDaoImp.getFoodByFoodId(foodExcel.getFoodId()).getPrice());
            nextRow.createCell(3).setCellValue(foodExcel.getFoodCount());
            nextRow.createCell(4).setCellValue(foodDaoImp.getFoodByFoodId(foodExcel.getFoodId()).getPrice()*foodExcel.getFoodCount());
        }

        Row nextRow = sheet.createRow(i);
        i++;
        Row nextRow1 = sheet.createRow(i);
        nextRow1.createCell(0).setCellValue("Total Price od all Sales");
        nextRow1.createCell(1).setCellValue(totalPrice);
        nextRow1.createCell(2).setCellValue("From");
        nextRow1.createCell(4).setCellValue(dateFrom);
        nextRow1.createCell(6).setCellValue("To");
        nextRow1.createCell(8).setCellValue(dateTo);

        try {
            //Write the workbook in file system
            File excelFile = new File(fileLocation);
            FileOutputStream out = new FileOutputStream(excelFile);
            workbook.write(out);
            stream = new FileInputStream(excelFile);
            List<File> fileList = new ArrayList<>();
            fileList.add(excelFile);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Daily Report");
            gmailSMTP.sendMessage(adminSetting.getReceiverEmail(),"Daily Report",stringBuilder,fileList);
            stream.close();
            out.close();
            excelFile.exists();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public OrderDetailDaoImp getOrderDetailDaoImp() {
        return orderDetailDaoImp;
    }

    public void setOrderDetailDaoImp(OrderDetailDaoImp orderDetailDaoImp) {
        this.orderDetailDaoImp = orderDetailDaoImp;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
