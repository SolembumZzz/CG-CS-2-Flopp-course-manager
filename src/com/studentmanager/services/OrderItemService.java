package com.studentmanager.services;

import java.util.ArrayList;
import java.util.List;

import com.studentmanager.models.OrderItem;
import com.studentmanager.utils.CSVUtils;

public class OrderItemService implements IOrderItemService {
    static String path = "data/orderitem.csv";

    @Override
    public ArrayList<OrderItem> getOrderItems() {
        ArrayList<OrderItem> orderItemList = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String item : records) {
            orderItemList.add(new OrderItem(item));
        }
        return orderItemList;
    }

    @Override
    public void add(OrderItem newOrderItem) {
        ArrayList<OrderItem> orderItemList = getOrderItems();
        orderItemList.add(newOrderItem);
        CSVUtils.write(path,orderItemList);
    }

    @Override
    public OrderItem getOrderItemById(String id) {
        for (OrderItem item : getOrderItems()) {
            if (id.equals(item.getId()))
                return item;
        }
        return null;
    }

    public ArrayList<OrderItem> getOrderItemByOrderId(double id) {
        ArrayList<OrderItem> foundItems = new ArrayList<>();
        for (OrderItem item: getOrderItems()) {
            if (id == item.getOrderId())
                foundItems.add(item);
        }
        return foundItems;
    }
}
