package com.studentmanager.models;


public class OrderItem {
    private final long orderId;
    private final String id;
    private final String courseId;
    private final String courseName;
    private final double price;

    public OrderItem(long orderId, String courseId, String courseName, double price) {
        this.orderId = orderId;
        this.id = courseName.substring(0,2).toUpperCase() + orderId;
        this.courseId = courseId;
        this.courseName = courseName;
        this.price = price;
    }

    public OrderItem(String csv) {
        String[] newOrderItem = csv.split(";");
        orderId = Long.parseLong(newOrderItem[0]);
        id = newOrderItem[1];
        courseId = newOrderItem[2];
        courseName = newOrderItem[3];
        price = Double.parseDouble(newOrderItem[4]);
    }

    public String getId() {
        return id;
    }
    public double getPrice() {
        return price;
    }
    public long getOrderId() {
        return orderId;
    }
    public String getCourseId() {
        return courseId;
    }
    public String getCourseName() {
        return courseName;
    }

    @Override
    public String toString() {
        return getOrderId() + ";" + getId() + ";" + getCourseId() + ";" + getCourseName() + ";" + getPrice();
    }
}
