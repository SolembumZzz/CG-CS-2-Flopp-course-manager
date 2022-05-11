package com.studentmanager.services;

import com.studentmanager.models.Order;
import com.studentmanager.models.OrderItem;

import java.util.ArrayList;

public interface IOrderService {
    ArrayList<Order> getOrders();

    ArrayList<OrderItem> getOrderItem();

    void addOrder(Order newOrder);

    void removeOrder(long id);

    Order getOrderByID(long id);

    boolean existByID(long id);

    void addOrderItem(OrderItem newOrderItem);

    void sortByDateCreatedASC();
    void sortByDateCreatedDESC();
    void sortByPriceASC();
    void sortByPriceDESC();
}
