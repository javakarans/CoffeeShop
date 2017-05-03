package com.coffeeshop.database;

import com.coffeeshop.model.OrderDetail;

import java.util.List;

/**
 * Created by Amirhossein on 4/28/2017.
 */
public interface OrderDetailDao {

    public boolean createOrder(OrderDetail orderDetail);
    public boolean updateOrder(OrderDetail orderDetail);
    public boolean deleteOrder(OrderDetail orderDetail);
    public List<OrderDetail> getAllOrders();
    public List<OrderDetail> getOrderDetailById(long orderDetailId);
}
