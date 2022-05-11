package com.studentmanager.utils.comparators;

import java.util.Comparator;

import com.studentmanager.models.Course;

public class CourseEmptySlotDESC implements Comparator<Course> {
    @Override
    public int compare(Course o1, Course o2) {
        int e1 = Integer.parseInt(o1.getSize().split("/")[1]) - Integer.parseInt(o1.getSize().split("/")[0]);
        int e2 = Integer.parseInt(o2.getSize().split("/")[1]) - Integer.parseInt(o2.getSize().split("/")[0]);
        return Integer.compare(e2, e1);
    }
}
