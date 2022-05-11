package com.studentmanager.views;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import com.studentmanager.menus.CourseManagerMenu;
import com.studentmanager.models.Course;
import com.studentmanager.services.CourseService;
import com.studentmanager.utils.Validity;
import com.studentmanager.utils.ValueFormatter;

import static com.studentmanager.utils.Warnings.*;
import static com.studentmanager.utils.LinePrinter.*;

public class CourseView {
    static Scanner input = new Scanner(System.in);

    public static void accessCourseManager() {
        int choice = -1;
        do {
            CourseManagerMenu.displayCourseMenu();
            try {
                choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        displayCourses();
                        break;
                    case 2:
                        addCourse();
                        break;
                    case 3:
                        removeCourse();
                        break;
                    case 4:
                        editCourse();
                        break;
                    case 0:
                        break;
                    default:
                        printWrongInputError();
                }
            } catch (Exception e) {
                printWrongInputError();
            }
        } while (choice != 0);
    }

    public static void renderCourses() {
        CourseService courseService = new CourseService();
        if (courseService.getCourses().size() == 0){
            printNoCourse();
            return;
        }

        printDoubleLineBreak();
        System.out.printf("%-10s %-25s %-25s %-10s %-10s %-15s %-15s\n", "ID", "Course", "Fee", "Size", "Duration", "Start", "End");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        for (Course course : courseService.getCourses()) {
            System.out.printf("%-10s %-25s %-25s %-10s %-10s %-15s %-15s\n", course.getId(), course.getName(),
                    ValueFormatter.formatPrice(course.getFee()), course.getSize(), ValueFormatter.formatDuration(course.getDuration()),
                    ValueFormatter.formatDate(course.getStartDate()), ValueFormatter.formatDate(course.getEndDate()));
        }
        System.out.println();
    }

    public static void renderSearchedCourses(ArrayList<Course> foundCourses) {
        printDoubleLineBreak();
        System.out.printf("%-10s %-25s %-25s %-10s %-10s %-15s %-15s\n", "ID", "Course", "Fee", "Size", "Duration", "Start", "End");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        for (Course course : foundCourses) {
            System.out.printf("%-10s %-25s %-25s %-10s %-10s %-15s %-15s\n", course.getId(), course.getName(),
                    ValueFormatter.formatPrice(course.getFee()), course.getSize(), ValueFormatter.formatDuration(course.getDuration()),
                    ValueFormatter.formatDate(course.getStartDate()), ValueFormatter.formatDate(course.getEndDate()));
        }
        System.out.println();
    }

    public static void displayCourses() {
        CourseService courseList = new CourseService();
        int choice = -1;
        do {
            renderCourses();
            CourseManagerMenu.displayCourseSorters_Manager();
            try {
                choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        searchCourse();
                    case 2:
                        courseList.sortByNameASC();
                        break;
                    case 3:
                        courseList.sortByNameDESC();
                        break;
                    case 4:
                        courseList.sortByFeeASC();
                        break;
                    case 5:
                        courseList.sortByFeeDESC();
                        break;
                    case 6:
                        courseList.sortByEmptySlotASC();
                        break;
                    case 7:
                        courseList.sortByEmptySlotDESC();
                        break;
                    case 8:
                        courseList.sortByStartDateASC();
                        break;
                    case 9:
                        courseList.sortByStartDateDESC();
                        break;
                    case 0:
                        printDoubleLineBreak();
                        break;
                    default:
                        printWrongInputError();
                }
            } catch (Exception e) {
                printWrongInputError();
            }
        } while (choice != 0);
    }

    public static void searchCourse() {
        CourseService courseService = new CourseService();
        do {
            printOneReturnOption();
            System.out.print("Enter keyword: ");
            String keyword = input.nextLine().toLowerCase();
            if (keyword.equals("n"))
                break;
            ArrayList<Course> courseList = courseService.searchCourseByToString(keyword);
            if (courseList.size() == 0) {
                printNoCourse();
                continue;
            }
            renderSearchedCourses(courseList);
        } while (true);
    }

    public static void addCourse() {
        String name = null;
        double fee = -1;
        int size = -1;
        int duration = -1;
        LocalDate startDate = null;

        renderCourses();
        printTwoReturnOptions();

        String process = "addName";
        boolean flag = true;

        do {
            switch (process) {
                case "addName":
                    String tempName = getNewCourseName();
                    if (tempName.equals("n") || tempName.equals("q"))
                        return;
                    name = tempName;
                    process = "addFee";
                    break;

                case "addFee":
                    String tempFee = getNewCourseFee();
                    if (tempFee.equals("n"))
                        return;
                    if (tempFee.equals("q")) {
                        process = "addName";
                        printSingleLineBreak();
                        break;
                    }
                    fee = Double.parseDouble(tempFee);
                    process = "addSize";
                    break;

                case "addSize":
                    String tempSize = getNewCourseSize();
                    if (tempSize.equals("n"))
                        return;
                    if (tempSize.equals("q")) {
                        process = "addFee";
                        printSingleLineBreak();
                        break;
                    }
                    size = Integer.parseInt(tempSize);
                    process = "addDuration";
                    break;

                case "addDuration":
                    String tempDuration = getNewCourseDuration();
                    if (tempDuration.equals("n"))
                        return;
                    if (tempDuration.equals("q")) {
                        process = "addSize";
                        printSingleLineBreak();
                        break;
                    }
                    duration = Integer.parseInt(tempDuration);
                    process = "addStartDate";
                    break;

                case "addStartDate":
                    String tempStartDate = getNewStartDate();
                    if (tempStartDate.equals("n"))
                        return;
                    if (tempStartDate.equals("q")) {
                        process = "addDuration";
                        printSingleLineBreak();
                        break;
                    }
                    startDate = ValueFormatter.unformatDate(tempStartDate);
                    flag = false;
                    break;
            }
        } while (flag);

        Course newCourse = new Course(name, fee, size, duration, startDate);
        new CourseService().addCourse(newCourse);
        System.out.println("Course added!");
    }

    public static void removeCourse() {
        CourseService courseService = new CourseService();
        do {
            renderCourses();
            printTwoSameReturnOptions();
            System.out.print("Enter course's id: ");
            String id = input.nextLine();
            if (id.equals("n")) {
                break;
            }
            id = id.toUpperCase();
            if (!courseService.existByID(id)) {
                printWrongIDError();
                continue;
            }
            if (!confirm())
                return;
            courseService.removeCourseByID(id);
            System.out.println("Course removed!");
            break;
        } while (true);
    }

    public static void editCourse() {
        CourseService courseService = new CourseService();
        do {
            renderCourses();
            printOneReturnOption();
            System.out.print("Enter course's id: ");
            String id = input.nextLine();
            if (id.equals("n"))
                return;
            id = id.toUpperCase();
            if (!courseService.existByID(id)) {
                printWrongIDError();
                continue;
            }
            updateCourse(id);
        } while (true);
    }

    public static void updateCourse(String id) {
        CourseService courseService = new CourseService();
        int choice = -1;
        do {
            renderCourses();
            CourseManagerMenu.displayCourseEditMenu();
            try {
                choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        printTwoSameReturnOptions();
                        String newName = getNewCourseName();
                        if (newName.equals("n") || newName.equals("q")) {
                            break;
                        }
                        courseService.updateCourseName(id, newName);
                        System.out.println("Name updated!");
                        printSingleLineBreak();
                        break;

                    case 2:
                        printTwoSameReturnOptions();
                        String tempFee = getNewCourseFee();
                        if (tempFee.equals("n") || tempFee.equals("q"))
                            break;
                        double newFee = Double.parseDouble(tempFee);
                        courseService.updateCourseFee(id, newFee);
                        System.out.println("Fee updated!");
                        printSingleLineBreak();
                        break;

                    case 3:
                        printTwoSameReturnOptions();
                        String tempSize = getNewCourseSize();
                        if (tempSize.equals("n") || tempSize.equals("q"))
                            break;
                        int newSize = Integer.parseInt(tempSize);
                        if (!new CourseService().checkCourseSlot(id, newSize)) {
                            System.out.println("!!! Class size cannot be lower than current student number !!!");
                            break;
                        }
                        courseService.updateCourseSize(id, newSize);
                        System.out.println("Size updated!");
                        printSingleLineBreak();
                        break;

                    case 4:
                        printTwoSameReturnOptions();
                        String tempDuration = getNewCourseDuration();
                        if (tempDuration.equals("n") || tempDuration.equals("q"))
                            break;
                        int newDuration = Integer.parseInt(tempDuration);
                        courseService.updateCourseDuration(id, newDuration);
                        System.out.println("Duration updated!");
                        printSingleLineBreak();
                        break;

                    case 5:
                        printTwoSameReturnOptions();
                        String tempStartDate = getNewStartDate();
                        if (tempStartDate.equals("n") || tempStartDate.equals("q"))
                            break;
                        LocalDate newStartDate = ValueFormatter.unformatDate(tempStartDate);
                        courseService.updateCourseStartDate(id, newStartDate);
                        System.out.println("Start date updated!");
                        printSingleLineBreak();
                        break;

                    case 0:
                        break;
                    default:
                        printWrongInputError();
                }
            } catch (Exception e) {
                printWrongInputError();
            }
        } while (choice != 0);
    }

    public static String getNewCourseName() {
        do {
            System.out.print("Enter course's name: ");
            String name = input.nextLine();
            if (name.equals("n") || name.equals("q")) {
                return name;
            }
            if (!Validity.isCourseNameValid(name)) {
                System.out.println("The first letter must be a character and only letters or numbers are allowed. Try again.");
                continue;
            }
            return name;
        } while (true);
    }

    public static String getNewCourseFee() {
        do {
            System.out.print("Enter fee (vnd): ");
            String fee = input.nextLine();
            if (fee.equals("n") || fee.equals("q"))
                return fee;
            if (!Validity.isNumberValid(fee)) {
                System.out.println("Course's fee can only contain number. Try again.");
                continue;
            }
            return fee;
        } while (true);
    }

    public static String getNewCourseSize() {
        do {
            System.out.print("Enter size: ");
            String size = input.nextLine();
            if (size.equals("n") || size.equals("q"))
                return size;
            if (!Validity.isNumberValid(size)) {
                System.out.println("Course's size can only contain number.");
                continue;
            }
            return size;
        } while (true);
    }

    public static String getNewCourseDuration() {
        do {
            System.out.print("Enter duration (weeks): ");
            String duration = input.nextLine();
            if (duration.equals("n") || duration.equals("q"))
                return duration;
            if (!Validity.isNumberValid(duration)) {
                System.out.println("Course's fee can only contain number. Try again.");
                continue;
            }
            return duration;
        } while (true);
    }

    public static String getNewStartDate() {
        CourseService courseService = new CourseService();
        do {
            System.out.print("Starting date (dd/mm/yyyy): ");
            String newStartDate = input.nextLine();
            if (newStartDate.equals("n") || newStartDate.equals("q")) {
                return newStartDate;
            }
            if (!Validity.isDateValid(newStartDate)) {
                System.out.println("Date invalid. Try again.");
                continue;
            }
            LocalDate tempStartDate = ValueFormatter.unformatDate(newStartDate);
            if (courseService.existByDate(tempStartDate)) {
                System.out.println("Date was booked. Try another date.");
                continue;
            }
            return newStartDate;
        } while (true);
    }
}
