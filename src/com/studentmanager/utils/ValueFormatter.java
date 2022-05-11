package com.studentmanager.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ValueFormatter {
    public static final String formatter = "dd/MM/yyyy";
    public static final String formatterID = "MMYYdd";


    public static String formatPrice(double price) {
        return String.format("%,.0f", price) + "vnd";
    }

    public static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(formatter));
    }

    public static LocalDate unformatDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(formatter));
    }

    public static String formatDuration(int duration) {
        return duration + ((duration == 1) ? " week" : " weeks");
    }
}
