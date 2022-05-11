package com.studentmanager.utils.comparators;

import com.studentmanager.models.Order;

import java.util.Comparator;

public class OrderPriceDESC implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return Double.compare(o2.getTotalPrice(), o1.getTotalPrice());
    }
}
