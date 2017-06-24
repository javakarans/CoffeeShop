package com.coffeeshop.database;

import com.coffeeshop.model.OrderDetail;
import com.coffeeshop.model.Status;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Amirhossein on 4/28/2017.
 */
public class OrderDetailDaoImp implements OrderDetailDao {

    private SQLService sqlService;

    public OrderDetailDaoImp(){
        sqlService = new SQLService();
    }

    public boolean createOrder(OrderDetail orderDetail) {
        return sqlService.create(orderDetail);
    }

    public boolean updateOrder(OrderDetail orderDetail) {
        return sqlService.update(orderDetail);
    }

    public boolean deleteOrder(OrderDetail orderDetail) {
        return sqlService.delete(orderDetail);
    }

    public List<OrderDetail> getAllOrders() {
        List orders = sqlService.getAllObjects(new OrderDetail());
        return orders;
    }

    public List<OrderDetail> getOrderDetailById(long orderDetailId) {
        List orders = sqlService.getObjectsBySpecialColumn(new OrderDetail(), "orderDetailId", orderDetailId);
        return orders;
    }

    public List<OrderDetail> getAllPendingOrder()
    {
        return sqlService.getObjectsBySpecialColumn(new OrderDetail(),"status", Status.ORDER_ONPENDING);
    }

    public List<OrderDetail> getTodayOrderPaid()
    {
        Date today =  new Date();
        SimpleDateFormat sm = new SimpleDateFormat("yyyyMMdd");
        String strDate = sm.format(today);
        try {
            today = sm.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date tDate = new java.sql.Date(today.getTime());
        List orders = sqlService.getObjectsByTwoSpecialColumns(new OrderDetail(),"date",tDate,"status",Status.ORDER_PAID);
        return orders;
    }

    public List<OrderDetail> getOrderDetailListBetweenTwoTimestamp(Timestamp time1,Timestamp time2){
        HibernateUtil.beginTransaction();
        Criteria criteria = HibernateUtil.getSession().createCriteria(OrderDetail.class);
        criteria.add(Restrictions.ge("time",time1));
        criteria.add(Restrictions.le("time",time2));
        List<OrderDetail> list = criteria.list();
        HibernateUtil.commitTransaction();
        return list;
    }

}
