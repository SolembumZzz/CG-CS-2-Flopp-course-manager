package com.studentmanager.views;

import java.util.Scanner;

import com.studentmanager.menus.AdminMenu;

import static com.studentmanager.utils.Warnings.*;

public class AdminView {
    static Scanner input = new Scanner(System.in);

    public void accessAdmin() {
        int choice = -1;
        do {
            AdminMenu.displayAdminMenu();
            try {
                choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        CourseView.accessCourseManager();
                        break;
                    case 2:
                        OrderView.accessOrderManager();
                        break;
                    case 0:
                        break;
                }
            } catch (Exception e) {
                printWrongInputError();
            }
        } while (choice != 0);
    }
}
