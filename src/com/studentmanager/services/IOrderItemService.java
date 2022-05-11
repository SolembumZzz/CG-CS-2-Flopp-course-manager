package com.studentmanager.services;

import com.studentmanager.models.OrderItem;

import java.util.ArrayList;
import java.util.List;

public interface IOrderItemService {

    ArrayList<OrderItem> getOrderItems();

    void add(OrderItem newOrderItem);

    OrderItem getOrderItemById(String id);

}
