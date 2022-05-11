package com.studentmanager.utils;

import com.studentmanager.services.CourseService;

import java.util.Scanner;

public class Warnings {
    static Scanner input = new Scanner(System.in);

    public static void printWrongInputError() {
        System.out.println("!!! Wrong input! Try again. !!!");
    }

    public static void printLogInError() {
        System.out.println("!!! Wrong username or password! Try again. !!!");
    }

    public static void printWrongIDError() {
        System.out.println("!!! Wrong ID! Try again. !!!");
    }

    public static void printOneReturnOption() {
        System.out.println("~ Input 'n' to return to the previous menu. ~");
    }

    public static void printTwoSameReturnOptions() {
        System.out.println("~ Input 'q' or 'n' to return to the previous menu. ~");
    }

    public static void printTwoReturnOptions() {
        System.out.println("~ Input 'q' to return to previous field, 'n' to return to the previous menu. ~");
    }

    public static boolean confirm() {
        System.out.println("!!! Are you sure? (y to confirm | n to cancel). !!!");
        String confirmation;
        do {
            System.out.print("Select: ");
            confirmation = input.nextLine().toLowerCase();
            if (Validity.isConfirmValid(confirmation))
                break;
            printWrongInputError();
        } while (true);
        return confirmation.equals("y");
    }

    public static boolean confirmChooseNextCourse() {
        System.out.println("~ Choose another? (y to choose another course | n to proceed to next step) ~");
        String confirmation;
        do {
            System.out.print("Select: ");
            confirmation = input.nextLine().toLowerCase();
            if (Validity.isConfirmValid(confirmation))
                break;
            printWrongInputError();
        } while (true);
        return confirmation.equals("y");
    }

    public static boolean confirmNewStudent(String newCustomerName) {
        System.out.println("!!! Are you sure you want to add " + newCustomerName +
                "? (y to confirm | n to cancel). !!!");
        String confirmation;
        do {
            System.out.print("Select: ");
            confirmation = input.nextLine().toLowerCase();
            if (Validity.isConfirmValid(confirmation))
                break;
            printWrongInputError();
        } while (true);
        return confirmation.equals("y");
    }

    public static boolean confirmPrintBill() {
        System.out.println("~Do you want to print a bill? (y to confirm | n to return | z to exit). ~");
        String confirmation;
        do {
            System.out.print("Select: ");
            confirmation = input.nextLine().toLowerCase();
            if (confirmation.equals("z")) {
                if (!confirm())
                    continue;
                System.exit(0);
            }
            if (Validity.isConfirmValid(confirmation))
                break;
            printWrongInputError();
        } while (true);
        return confirmation.equals("y");
    }

    public static void printNoCourse() {
        System.out.println("!!! No course founded !!!");
    }
    public static void printNoOrder() {
        System.out.println("!!! No order found !!!");
    }

    public static void printCourseAlreadyChosen() {
        System.out.println("!!! This course is already chosen !!!!");
    }
}
