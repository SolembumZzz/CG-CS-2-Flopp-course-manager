package com.studentmanager.utils.comparators;

import com.studentmanager.models.Course;

import java.util.Comparator;

public class CourseNameDESC implements Comparator <Course> {
    @Override
    public int compare(Course o1, Course o2) {
        return o2.getName().toLowerCase().compareTo(o1.getName().toLowerCase());
    }
}
