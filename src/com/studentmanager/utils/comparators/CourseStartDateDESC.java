package com.studentmanager.utils.comparators;

import com.studentmanager.models.Course;

import java.util.Comparator;

public class CourseStartDateDESC implements Comparator<Course> {
    @Override
    public int compare(Course o1, Course o2) {
        return o2.getStartDate().compareTo(o1.getStartDate());
    }
}
