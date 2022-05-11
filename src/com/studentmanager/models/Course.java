package com.studentmanager.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.studentmanager.utils.ValueFormatter;

public class Course {
    private final String id;
    private String name;
    private double fee;
    private String size;
    private int duration;
    private LocalDate startDate;
    private LocalDate endDate;

    public Course(String name, double fee, int size, int duration, LocalDate startDate) {
        this.name = name;
        this.fee = fee;
        this.size = "0/" + size;
        this.duration = duration;
        this.startDate = startDate;
        this.endDate = this.startDate.plusDays(duration * 7L);
        this.id = name.substring(0,2).toUpperCase() + this.startDate.format(DateTimeFormatter.ofPattern(ValueFormatter.formatterID));
    }

    public Course(String csv) {
        String[] newCourse = csv.split(";");
        this.id = newCourse[0];
        this.name = newCourse[1];
        this.fee = Double.parseDouble(newCourse[2]);
        this.size = newCourse[3];
        this.duration = Integer.parseInt(newCourse[4]);
        this.startDate = LocalDate.parse(newCourse[5]);
        this.endDate = LocalDate.parse(newCourse[6]);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFee() {
        return this.fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public int getDuration() {
        return this.duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate() {
        this.endDate = this.startDate.plusDays(this.duration * 7L);
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getSize() {
        return size;
    }

    public void setSize(int size) {
        String[] oldSize = this.size.split("/");
        this.size = oldSize[0] + "/" + size;
    }

    public void addStudent(int customerNumber) {
        String[] oldSize = this.size.split("/");
        oldSize[0] = String.valueOf(Integer.parseInt(oldSize[0]) + customerNumber);
        this.size = oldSize[0] + "/" + oldSize[1];
    }


    @Override
    public String toString() {
        return getId() + ';' + getName() + ';' + getFee() + ';'
                + getSize() + ';' + getDuration() + ';'
                + getStartDate() + ';' + getEndDate();
    }
}
