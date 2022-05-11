package com.studentmanager.views;

import java.util.Scanner;

import com.studentmanager.menus.UserMenu;

import static com.studentmanager.utils.Warnings.*;

public class UserView {
    static Scanner input = new Scanner(System.in);
    public void accessStaff() {
        int choice = -1;
        do {
            UserMenu.displayStaffMenu();
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
                    default:
                        printWrongInputError();
                }
            } catch (Exception e) {
                printWrongInputError();
            }
         } while (choice != 0);
    }


}
