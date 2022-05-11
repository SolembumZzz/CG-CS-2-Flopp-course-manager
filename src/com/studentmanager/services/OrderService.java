package com.studentmanager.services;

import java.util.ArrayList;
import java.util.List;

import com.studentmanager.models.Order;
import com.studentmanager.models.OrderItem;
import com.studentmanager.utils.CSVUtils;
import com.studentmanager.utils.comparators.OrderDateCreatedASC;
import com.studentmanager.utils.comparators.OrderDateCreatedDESC;
import com.studentmanager.utils.comparators.OrderPriceASC;
import com.studentmanager.utils.comparators.OrderPriceDESC;

public class OrderService implements IOrderService {
    static String pathOrder = "data/order.csv";
    static String pathItem = "data/orderItem.csv";

    public OrderService(){};

    @Override
    public ArrayList<Order> getOrders() {
        ArrayList<Order> orderList = new ArrayList<>();
        List<String> records = CSVUtils.read(pathOrder);
        for (String item : records) {
            orderList.add(new Order(item));
        }
        return orderList;
    }

    @Override
    public void addOrder(Order newOrder) {
        ArrayList<Order> orderList = getOrders();
        orderList.add(newOrder);
        CSVUtils.write(pathOrder, orderList);
    }

    @Override
    public void removeOrder(long id) {
        ArrayList<Order> orderList = getOrders();
        for (Order item : orderList) {
            orderList.remove(item);
            break;
        }
        CSVUtils.write(pathOrder, orderList);
    }

    @Override
    public Order getOrderByID(long id) {
        ArrayList<Order> orderList = getOrders();
        for (Order item : orderList) {
            if (item.getId() == id)
                return item;
        }
        return null;
    }

    @Override
    public boolean existByID(long id) {
        return getOrderByID(id) != null;
    }

    @Override
    public ArrayList<OrderItem> getOrderItem() {
        ArrayList<OrderItem> orderItemList = new ArrayList<>();
        List<String> records = CSVUtils.read(pathItem);
        for (String item : records) {
            orderItemList.add(new OrderItem(item));
        }
        return orderItemList;
    }

    @Override
    public void addOrderItem(OrderItem newOrderItem) {
        ArrayList<OrderItem> orderItemList = getOrderItem();
        orderItemList.add(newOrderItem);
        CSVUtils.write(pathItem, orderItemList);
    }

    @Override
    public void sortByDateCreatedASC() {
        ArrayList<Order> orderList = getOrders();
        orderList.sort(new OrderDateCreatedASC());
        CSVUtils.write(pathOrder, orderList);
    }
    @Override
    public void sortByDateCreatedDESC() {
        ArrayList<Order> orderList = getOrders();
        orderList.sort(new OrderDateCreatedDESC());
        CSVUtils.write(pathOrder, orderList);
    }
    @Override
    public void sortByPriceASC() {
        ArrayList<Order> orderList = getOrders();
        orderList.sort(new OrderPriceASC());
        CSVUtils.write(pathOrder, orderList);
    }
    @Override
    public void sortByPriceDESC() {
        ArrayList<Order> orderList = getOrders();
        orderList.sort(new OrderPriceDESC());
        CSVUtils.write(pathOrder, orderList);
    }
}
