package com.studentmanager.utils.comparators;

import java.util.Comparator;

import com.studentmanager.models.Course;

public class CourseFeeDESC implements Comparator <Course> {
    @Override
    public int compare(Course o1, Course o2) {
        return Double.compare(o2.getFee(), o1.getFee());
    }
}
