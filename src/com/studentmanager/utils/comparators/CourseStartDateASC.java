package com.studentmanager.utils.comparators;

import com.studentmanager.models.Course;

import java.util.Comparator;

public class CourseStartDateASC implements Comparator<Course> {
    @Override
    public int compare(Course o1, Course o2) {
        return o1.getStartDate().compareTo(o2.getStartDate());
    }
}
