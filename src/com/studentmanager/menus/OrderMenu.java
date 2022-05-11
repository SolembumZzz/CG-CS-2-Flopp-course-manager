package com.studentmanager.menus;

public class OrderMenu {
    public static void displayOrderMenu() {
        System.out.println("✽ ✽ ✽ ✽ ✽ ORDER : MANAGER ✽ ✽ ✽ ✽ ✽");
        System.out.println("✽                                     ✽");
        System.out.println("✽         1. Create new order.        ✽");
        System.out.println("✽         2. Orders history.          ✽");
        System.out.println("✽         0. Return.                  ✽");
        System.out.println("✽                                     ✽");
        System.out.println("✽ ✽ ✽ ✽ ✽ ✽ ✽ ✽  ✽ ✽ ✽ ✽ ✽ ✽ ✽ ✽");
        System.out.print("Select: ");
    }

    public static void displayNewOrderMenu() {
        System.out.println("1. Choose course.");
        System.out.println("2. Search course.");
        System.out.println("3. Sort list.");
        System.out.println("0. Return.");
        System.out.print("Select: ");
    }

    public static void displayBillMenu() {
        System.out.println("9. Exit.");
        System.out.println("0. Return.");
        System.out.print("Select: ");
    }

    public static void displayOrderSorter() {
        System.out.println("1. Sort by date (ascend).");
        System.out.println("2. Sort by date (descend).");
        System.out.println("3. Sort by total price (ascend).");
        System.out.println("4. Sort by total price (descend).");
        System.out.println("0. Return.");
        System.out.print("Select");
    }
}
