package com.coffeeshop.database;

import com.coffeeshop.model.Order;

import java.util.List;

/**
 * Created by Amirhossein on 4/28/2017.
 */
public class OrderDaoImp implements OrderDao{

    private SQLService sqlService;

    public OrderDaoImp(){
        sqlService = new SQLService();
    }

    public boolean createOrder(Order order) {
        return sqlService.create(order);
    }

    public boolean updateOrder(Order order) {
        return sqlService.update(order);
    }

    public boolean deleteOrder(Order order) {
        return sqlService.delete(order);
    }

    public List<Order> getAllOrders() {
        List orders = sqlService.getAllObjects(new Order());
        return orders;
    }

    public List<Order> getOrderByOrderId(String orderId) {
        List orders = sqlService.getObjectsBySpecialColumn(new Order(), "orderId", orderId);
        return orders;
    }
}
