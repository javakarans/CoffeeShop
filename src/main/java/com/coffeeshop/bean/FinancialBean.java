package com.coffeeshop.bean;

import com.coffeeshop.database.FoodOrderDaoImp;
import com.coffeeshop.database.OrderDetailDaoImp;
import com.coffeeshop.model.FoodOrder;
import com.coffeeshop.model.OrderDetail;
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
import java.io.IOException;
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
    List<OrderDetail> orderDetails;
    double totalPrice;

    private Date today;
    private Date from;
    private Date to;

    @PostConstruct
    public void init(){
        checkAdminIsLogin();
        orderDetails = new ArrayList<OrderDetail>();
        orderDetailDaoImp = new OrderDetailDaoImp();
        today = new Date();
        from = new Date();
        to = new Date();
        orderDetails = orderDetailDaoImp.getAllOrders();
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
        totalPrice = 0;
        for (int i = 0; i <orderDetails.size(); i++){
            if(orderDetails.get(i).getDate().compareTo(from) >= 0 &&
                    orderDetails.get(i).getDate().compareTo(to) <= 0){
                totalPrice += orderDetails.get(i).getTotalPrice();
            }
        }
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("$('#result').modal()");
    }

    public void calculateTodaySales(){
        orderDetails = orderDetailDaoImp.getTodayOrderPaid();
        totalPrice = 0;
        for(int i = 0; i < orderDetails.size(); i++){
                totalPrice += orderDetails.get(i).getTotalPrice();
        }
        createExcelFile();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("$('#todayResult').modal()");
    }

    private void createExcelFile()
    {
        FoodOrderDaoImp foodOrderDaoImp = new FoodOrderDaoImp();
        List<FoodOrder> foodOrderList = new ArrayList<>();
        for (OrderDetail orderDetail: orderDetails) {
            List<FoodOrder> foodOrders = foodOrderDaoImp.getFoodOrderWithOrderId(orderDetail.getOrderDetailId());
            if (foodOrders.size()!=0)
                foodOrderList.addAll(foodOrders);
        }
        Set<Long> foodIds = new HashSet<>();
        for (FoodOrder foodOrder : foodOrderList)
        {
            foodIds.add(foodOrder.getFoodId());
        }
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
        }
        fillExcel(foodExcelList);
    }

    private void fillExcel(List<FoodExcel> foodExcelList)
    {
        String fileLocation = StaticSettings.imageUrl + "epsprocedure.xlsx";
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("DailyReport");
        Row firstHeader = sheet.createRow(0);
        firstHeader.createCell(0).setCellValue("Food IDّ");
        firstHeader.createCell(1).setCellValue("Food Nameّ");
        firstHeader.createCell(2).setCellValue("Food Priceّ");
        firstHeader.createCell(3).setCellValue("Food Qntّ");
        firstHeader.createCell(4).setCellValue("Total Price");

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
