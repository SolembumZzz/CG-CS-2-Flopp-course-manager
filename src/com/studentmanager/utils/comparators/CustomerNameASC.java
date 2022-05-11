package com.studentmanager.utils.comparators;

import java.util.Comparator;

import com.studentmanager.models.Customer;

public class CustomerNameASC implements Comparator<Customer> {
    @Override
    public int compare(Customer o1, Customer o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
