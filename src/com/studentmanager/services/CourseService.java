package com.studentmanager.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.studentmanager.models.Course;
import com.studentmanager.utils.CSVUtils;
import com.studentmanager.utils.comparators.*;

public class CourseService implements ICourseService {
    public static final String path = "data/courses.csv";

    public CourseService() {
    }

    @Override
    public ArrayList<Course> getCourses() {
        ArrayList<Course> courseList = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String item : records) {
            courseList.add(new Course(item));
        }
        return courseList;
    }

    @Override
    public Course getCourseByID(String id) {
        for (Course course : getCourses()) {
            if (id.equals(course.getId()))
                return course;
        }
        return null;
    }

    @Override
    public boolean existByID(String id) {
        return getCourseByID(id) != null;
    }

    public boolean existByDate(LocalDate date) {
        for (Course course : getCourses()) {
            if (date.equals(course.getStartDate()))
                return true;
        }
        return false;
    }

    @Override
    public void addCourse(Course newCourse) {
        ArrayList<Course> courseList = getCourses();
        courseList.add(newCourse);
        CSVUtils.write(path, courseList);
    }

    @Override
    public void removeCourseByID(String id) {
        ArrayList<Course> courseList = getCourses();
        ICustomerService customerService = new CustomerService();
        for (int i = 0; i < courseList.size(); i++) {
            if (id.equals(courseList.get(i).getId())) {
                courseList.remove(i);
                customerService.removeCustomerByCourseID(id);
                break;
            }
        }
        CSVUtils.write(path, courseList);
    }

    public void sortByNameASC() {
        ArrayList<Course> courseList = getCourses();
        courseList.sort(new CourseNameASC());
        CSVUtils.write(path, courseList);
    }

    @Override
    public void sortByNameDESC() {
        ArrayList<Course> courseList = getCourses();
        courseList.sort(new CourseNameDESC());
        CSVUtils.write(path, courseList);
    }

    @Override
    public void sortByFeeASC() {
        ArrayList<Course> courseList = getCourses();
        courseList.sort(new CourseFeeASC());
        CSVUtils.write(path, courseList);
    }

    @Override
    public void sortByFeeDESC() {
        ArrayList<Course> courseList = getCourses();
        courseList.sort(new CourseFeeDESC());
        CSVUtils.write(path, courseList);
    }

    @Override
    public void sortByEmptySlotASC() {
        ArrayList<Course> courseList = getCourses();
        courseList.sort(new CourseEmptySlotASC());
        CSVUtils.write(path, courseList);
    }

    @Override
    public void sortByStartDateASC() {
        ArrayList<Course> courseList = getCourses();
        courseList.sort(new CourseStartDateASC());
        CSVUtils.write(path, courseList);
    }

    @Override
    public void sortByStartDateDESC() {
        ArrayList<Course> courseList = getCourses();
        courseList.sort(new CourseStartDateDESC());
        CSVUtils.write(path, courseList);
    }

    @Override
    public void sortByEmptySlotDESC() {
        ArrayList<Course> courseList = getCourses();
        courseList.sort(new CourseEmptySlotDESC());
        CSVUtils.write(path, courseList);
    }

    @Override
    public ArrayList<Course> searchCourseByToString(String keyword) {
        String lwKeyword = keyword.toLowerCase();
        ArrayList<Course> searchedCourses = new ArrayList<>();
        for (Course item : getCourses()) {
            if (item.toString().toLowerCase().contains(lwKeyword))
                searchedCourses.add(item);
        }
        return searchedCourses;
    }

    @Override
    public boolean checkCourseSlot(String id, int wantedSize) {
        Course currentCourse = getCourseByID(id);
        int studentNumber = Integer.parseInt(currentCourse.getSize().split("/")[0]);
        return wantedSize >= studentNumber;
    }

    @Override
    public void updateCourseName(String id, String newName) {
        ArrayList<Course> courseList = new CourseService().getCourses();
        for (int index = 0; index < courseList.size(); index++) {
            if (id.equals(courseList.get(index).getId())) {
                courseList.get(index).setName(newName);

                CSVUtils.write(CourseService.path, courseList);
                break;
            }
        }
    }

    @Override
    public void updateCourseFee(String id, double newFee) {
        ArrayList<Course> courseList = new CourseService().getCourses();
        for (int index = 0; index < courseList.size(); index++) {
            if (id.equals(courseList.get(index).getId())) {
                courseList.get(index).setFee(newFee);
                CSVUtils.write(CourseService.path, courseList);
                break;
            }
        }
    }

    @Override
    public void updateCourseSize(String id, int newSize) {
        ArrayList<Course> courseList = new CourseService().getCourses();
        for (int index = 0; index < courseList.size(); index++) {
            if (id.equals(courseList.get(index).getId())) {
                courseList.get(index).setSize(newSize);
                CSVUtils.write(CourseService.path, courseList);
                break;
            }
        }
    }

    @Override
    public void updateCourseDuration(String id, int newDuration) {
        ArrayList<Course> courseList = new CourseService().getCourses();
        for (int index = 0; index < courseList.size(); index++) {
            if (id.equals(courseList.get(index).getId())) {
                courseList.get(index).setDuration(newDuration);
                courseList.get(index).setEndDate();
                CSVUtils.write(CourseService.path, courseList);
                break;
            }
        }
    }

    @Override
    public void updateCourseStartDate(String id, LocalDate newStartDate) {
        ArrayList<Course> courseList = new CourseService().getCourses();
        for (int index = 0; index < courseList.size(); index++) {
            if (id.equals(courseList.get(index).getId())) {
                courseList.get(index).setStartDate(newStartDate);
                courseList.get(index).setEndDate();
                CSVUtils.write(CourseService.path, courseList);
                break;
            }
        }
    }

    @Override
    public boolean isCourseFull(String id) {
        Course targetCourse = getCourseByID(id);
        String[] currentSize = targetCourse.getSize().split("/");

        int studentNum = Integer.parseInt(currentSize[0]);
        int size = Integer.parseInt(currentSize[1]);

        return studentNum >= size;
    }

    @Override
    public boolean isCourseExpired(String id) {
        Course targetCourse = getCourseByID(id);

        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = targetCourse.getStartDate();
        return currentDate.isAfter(startDate);
    }

    @Override
    public void getOneMoreStudent(String id) {
        ArrayList<Course> courseList = getCourses();
        for (int index = 0; index < courseList.size(); index++) {
            if (id.equals(courseList.get(index).getId())) {
                courseList.get(index).addStudent(1);
                CSVUtils.write(CourseService.path, courseList);
                break;
            }
        }
    }
}
