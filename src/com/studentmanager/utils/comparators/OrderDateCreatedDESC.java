package com.studentmanager.utils.comparators;

import com.studentmanager.models.Order;

import java.util.Comparator;

public class OrderDateCreatedDESC implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return o2.getDateCreated().compareTo(o1.getDateCreated());
    }
}
