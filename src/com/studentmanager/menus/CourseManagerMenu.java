package com.studentmanager.menus;

public class CourseManagerMenu {

    public static void displayCourseMenu() {
        System.out.println("✽ ✽ ✽ ✽ ✽ COURSE::MANAGER ✽ ✽ ✽ ✽ ✽");
        System.out.println("✽                                     ✽");
        System.out.println("✽         1. Show courses.            ✽");
        System.out.println("✽         2. Add courses.             ✽");
        System.out.println("✽         3. Remove courses.          ✽");
        System.out.println("✽         4. Edit courses.            ✽");
        System.out.println("✽         0. Return.                  ✽");
        System.out.println("✽                                     ✽");
        System.out.println("✽ ✽ ✽ ✽ ✽ ✽ ✽ ✽  ✽ ✽ ✽ ✽ ✽ ✽ ✽ ✽");
        System.out.print("Select: ");
    }

    public static void displayCourseSorters_Manager() {
        System.out.println("1. Search course.");
        System.out.println("2. Sort by name (ascend).");
        System.out.println("3. Sort by name (descend).");
        System.out.println("4. Sort by fee (ascend).");
        System.out.println("5. Sort by fee (descend).");
        System.out.println("6. Sort by empty slots (ascend).");
        System.out.println("7. Sort by empty slots (descend).");
        System.out.println("8. Sort by start date (ascend).");
        System.out.println("9. Sort by start date (descend).");
        System.out.println("0. Return.");
        System.out.print("Select: ");
    }

    public static void displayCourseSorters_Order() {
        System.out.println("1. Sort by empty slots (ascend).");
        System.out.println("2. Sort by empty slots (descend).");
        System.out.println("3. Sort by start date (ascend).");
        System.out.println("4. Sort by start date (descend).");
        System.out.println("5. Sort by fee (ascend).");
        System.out.println("6. Sort by fee (descend).");
        System.out.println("0. Return.");
        System.out.print("Select: ");
    }

    public static void displayCourseEditMenu() {
        System.out.println("1. Edit name.");
        System.out.println("2. Edit fee.");
        System.out.println("3. Edit class size.");
        System.out.println("4. Edit duration.");
        System.out.println("5. Edit start date.");
        System.out.println("0. Return.");
        System.out.print("Select: ");
    }
}
