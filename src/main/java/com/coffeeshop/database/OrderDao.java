package com.coffeeshop.database;

import com.coffeeshop.model.Order;

import java.util.List;

/**
 * Created by Amirhossein on 4/28/2017.
 */
public interface OrderDao {

    public boolean createOrder(Order order);
    public boolean updateOrder(Order order);
    public boolean deleteOrder(Order order);
    public List<Order> getAllOrders();
    public List<Order> getOrderByOrderId(String orderId);
}
