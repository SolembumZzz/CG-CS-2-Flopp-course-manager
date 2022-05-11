package com.studentmanager.views;

import java.util.Scanner;

import com.studentmanager.menus.LoginMenu;
import com.studentmanager.services.UserService;

import static com.studentmanager.utils.Warnings.*;
import static com.studentmanager.utils.LinePrinter.*;

public class LoginView {
    static Scanner input = new Scanner(System.in);
    UserService userService = new UserService();

    public void run() {
        int choice = -1;
        LoginMenu.displayGreeting();
        do {
            try {
                LoginMenu.displayLoginMenu();
                choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        userService.signIn();
                        break;
                    case 0:
                        if (confirm()) {
                            System.out.println("Have a good day!");
                            System.exit(0);
                        } else {
                            printSingleLineBreak();
                        }
                        break;
                    default:
                        printWrongInputError();
                }
            } catch (Exception e) {
                printWrongInputError();
            }
        } while (true);
    }
}
