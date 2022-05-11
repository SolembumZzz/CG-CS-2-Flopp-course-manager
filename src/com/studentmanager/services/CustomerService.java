package com.studentmanager.services;

import com.studentmanager.models.Customer;
import com.studentmanager.utils.CSVUtils;
import com.studentmanager.utils.comparators.CustomerNameASC;
import com.studentmanager.utils.comparators.CustomerNameDESC;

import java.util.ArrayList;
import java.util.List;

public class CustomerService implements ICustomerService {
    public static String path = "data/customers.csv";

    public CustomerService(){
    }

    @Override
    public ArrayList<Customer> getCustomers() {
        ArrayList<Customer> customerList = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String item : records) {
            customerList.add(new Customer(item));
        }
        return customerList;
    }

    @Override
    public void addCustomer(Customer newCustomer) {
        ArrayList<Customer> customerList = getCustomers();
        customerList.add(newCustomer);
        CSVUtils.write(path, customerList);
    }

    @Override
    public void removeCustomerByID(String id) {
        ArrayList<Customer> customerList = getCustomers();
        for (int i = 0; i < customerList.size(); i++) {
            if (id.equals(customerList.get(i).getId())) {
                customerList.remove(i);
                break;
            }
        }
        CSVUtils.write(path, customerList);
    }

    public void removeCustomerByCourseID(String id) {
        ArrayList<Customer> customerList = getCustomers();
        for (int i = customerList.size()-1; i >= 0; i--) {
            if (id.equals(customerList.get(i).getCourseID()))
                customerList.remove(customerList.get(i));
        }
        CSVUtils.write(path,customerList);
    }

    @Override
    public Customer getCustomerByID(String id) {
        for (Customer customer : getCustomers()) {
            if (id.equals(customer.getId())) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public boolean existByID(String id) {
        return getCustomerByID(id) != null;
    }

    @Override
    public void sortByNameASC() {
        ArrayList<Customer> customerList = getCustomers();
        customerList.sort(new CustomerNameASC());
        CSVUtils.write(path, customerList);
    }

    @Override
    public void sortByNameDESC() {
        ArrayList<Customer> customerList = getCustomers();
        customerList.sort(new CustomerNameDESC());
        CSVUtils.write(path, customerList);
    }

    @Override
    public ArrayList<Customer> searchCustomerByName(String keyword) {
        String lwKeyword = keyword.toLowerCase();
        ArrayList<Customer> searchedCustomers = new ArrayList<>();
        for (Customer item : getCustomers()) {
            if (item.getName().toLowerCase().contains(lwKeyword)) {
                searchedCustomers.add(item);
            }
        }
        return searchedCustomers;
    }
}
