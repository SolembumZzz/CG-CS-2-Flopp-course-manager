package com.studentmanager.utils.comparators;

import java.util.Comparator;

import com.studentmanager.models.Course;

public class CourseFeeASC implements Comparator <Course> {
    @Override
    public int compare(Course o1, Course o2) {
        return Double.compare(o1.getFee(), o2.getFee());
    }
}
