package com.studentmanager.utils;

public class LinePrinter {
    public static void printSingleLineBreak() {
        int length = 155;
        for (int i = 0; i < length; i++) {
            System.out.print('-');
        }
        System.out.print("\n");
    }
    public static void printDoubleLineBreak() {
        int length = 155;
        int line = 2;
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print('-');
            }
            System.out.print("\n");
        }
    }
}
