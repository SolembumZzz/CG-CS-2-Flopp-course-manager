package com.studentmanager.models;

public class Customer {
    private String id;
    private String courseID;
    private String name;
    private String email;
    private String address;


    public Customer(String id, String courseID, String name, String email, String address) {
        this.id = courseID + id;
        this.courseID = courseID;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Customer(String csv) {
        String[] newCustomer = csv.split(";");
        this.id = newCustomer[0];
        this.courseID = newCustomer[1];
        this.name = newCustomer[2];
        this.email = newCustomer[3];
        this.address = newCustomer[4];
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = this.courseID + id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return getId() + ";" + getCourseID() + ";" +  getName() + ";" +  getEmail() + ";" +  getAddress();
    }
}
