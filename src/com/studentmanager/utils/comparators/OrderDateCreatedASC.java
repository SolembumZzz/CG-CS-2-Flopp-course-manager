package com.studentmanager.utils.comparators;

import com.studentmanager.models.Order;

import java.util.Comparator;

public class OrderDateCreatedASC implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return o1.getDateCreated().compareTo(o2.getDateCreated());
    }
}
