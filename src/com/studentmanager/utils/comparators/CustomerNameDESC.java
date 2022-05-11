package com.studentmanager.utils.comparators;

import com.studentmanager.models.Customer;

import java.util.Comparator;

public class CustomerNameDESC implements Comparator<Customer> {
    @Override
    public int compare(Customer o1, Customer o2) {
        return o2.getName().compareTo(o1.getName());
    }
}
