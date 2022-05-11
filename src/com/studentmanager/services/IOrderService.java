package com.studentmanager.services;

import com.studentmanager.models.Order;

import java.util.ArrayList;

public interface IOrderService {
    ArrayList<Order> getOrders();

    void addOrder(Order newOrder);

    void removeOrder(long id);

    Order getOrderByID(long id);

    boolean existByID(long id);

}
