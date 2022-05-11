package com.studentmanager.services;

import com.studentmanager.models.Customer;

import java.util.ArrayList;

public interface ICustomerService {

    ArrayList<Customer> getCustomers();

    void addCustomer(Customer newCustomer);

    void removeCustomerByID(String id);

    Customer getCustomerByID(String id);

    boolean existByID(String id);

    void sortByNameASC();
    void sortByNameDESC();

    ArrayList<Customer> searchCustomerByName(String keyword);
}
