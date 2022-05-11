package com.studentmanager.utils.comparators;

import java.util.Comparator;

import com.studentmanager.models.Course;

public class CourseNameASC implements Comparator <Course> {
    @Override
    public int compare(Course o1, Course o2) {
        return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
    }
}
