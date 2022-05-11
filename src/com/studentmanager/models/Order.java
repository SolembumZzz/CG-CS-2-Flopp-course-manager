package com.studentmanager.models;

import java.time.LocalDate;

public class Order {
    private final long id;
    private final LocalDate dateCreated;
    private final String name;
    private final String email;
    private double totalPrice;
    private final String nameCreator;

    public Order(String name, String email, String nameCreator) {
        this.id = System.currentTimeMillis();
        this.dateCreated = LocalDate.now();
        this.name = name;
        this.email = email;
        this.totalPrice = 0;
        this.nameCreator = nameCreator;
    }

    public Order(String csv) {
        String[] newOrder = csv.split(";");
        this.id = Long.parseLong(newOrder[0]);
        this.dateCreated = LocalDate.parse(newOrder[1]);
        this.name = newOrder[2];
        this.email = newOrder[3];
        this.totalPrice = Double.parseDouble(newOrder[4]);
        this.nameCreator = newOrder[5];
    }

    public long getId() {
        return id;
    }
    public LocalDate getDateCreated() {
        return this.dateCreated;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getNameCreator() {
        return nameCreator;
    }
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return getId() + ";" + getDateCreated() + ";" + getName() + ";" + getEmail()
                + ";" + getTotalPrice() + ";" + getNameCreator();
    }
}
