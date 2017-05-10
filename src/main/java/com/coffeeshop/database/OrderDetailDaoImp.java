package com.coffeeshop.database;

import com.coffeeshop.model.OrderDetail;
import com.coffeeshop.model.Status;

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

}
