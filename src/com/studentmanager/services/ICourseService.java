package com.studentmanager.services;

import java.util.ArrayList;

import com.studentmanager.models.Course;

public interface ICourseService {
    ArrayList<Course> getCourses();

    void addCourse(Course courseList);

    void removeCourseByID(String id);

    Course getCourseByID(String id);

    boolean existByID(String id);

}
