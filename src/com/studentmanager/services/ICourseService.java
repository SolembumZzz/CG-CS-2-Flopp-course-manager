package com.studentmanager.services;

import java.time.LocalDate;
import java.util.ArrayList;

import com.studentmanager.models.Course;

public interface ICourseService {
    ArrayList<Course> getCourses();

    void addCourse(Course courseList);

    void removeCourseByID(String id);

    Course getCourseByID(String id);

    boolean existByID(String id);
    boolean existByDate(LocalDate date);

    void sortByNameASC();
    void sortByNameDESC();
    void sortByFeeASC();
    void sortByFeeDESC();
    void sortByEmptySlotASC();
    void sortByEmptySlotDESC();
    void sortByStartDateASC();
    void sortByStartDateDESC();

    ArrayList<Course> searchCourseByToString(String keyword);

    boolean checkCourseSlot(String id, int wantedSize);

    void updateCourseName(String id, String newName);
    void updateCourseFee(String id, double newFee);
    void updateCourseSize(String id, int newSize);
    void updateCourseDuration(String id, int newDuration);
    void updateCourseStartDate(String id, LocalDate newStartDate);

    boolean isCourseFull(String id);
    boolean isCourseExpired(String id);
    void getOneMoreStudent(String id);
}
