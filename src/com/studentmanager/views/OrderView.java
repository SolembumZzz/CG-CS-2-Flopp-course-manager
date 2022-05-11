package com.studentmanager.views;

import java.util.ArrayList;
import java.util.Scanner;

import com.studentmanager.menus.CourseManagerMenu;
import com.studentmanager.menus.OrderMenu;
import com.studentmanager.models.Course;
import com.studentmanager.models.Customer;
import com.studentmanager.models.Order;
import com.studentmanager.models.OrderItem;
import com.studentmanager.services.*;
import com.studentmanager.utils.Validity;
import com.studentmanager.utils.ValueFormatter;

import static com.studentmanager.utils.LinePrinter.*;
import static com.studentmanager.utils.Warnings.*;

public class OrderView {
    static Scanner input = new Scanner(System.in);

    private static ArrayList<Course> searchedCourse = new ArrayList<>();

    public static void accessOrderManager() {
        int choice = -1;
        do {
            OrderMenu.displayOrderMenu();
            try {
                choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        createNewOrder();
                        break;
                    case 2:
                        displayOrderHistory();
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

    public static void createNewOrder() {
        int choice = -1;
        boolean searched = false;

        do {
            if (!searched)
                CourseView.renderCourses();
            else
                CourseView.renderSearchedCourses(searchedCourse);
            OrderMenu.displayNewOrderMenu();

            try {
                choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        chooseCourse();
                        break;
                    case 2:
                        searched = searchCourse();
                        break;
                    case 3:
                        openSortMenu();
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

    public static void chooseCourse() {
        ICourseService courseService = new CourseService();
        ArrayList<Course> chosenCourseList = new ArrayList<>();

        do {
            printOneReturnOption();
            System.out.print("Enter course's ID: ");
            String courseId = input.nextLine();
            if (courseId.equals("n"))
                return;
            if (!courseService.existByID(courseId)) {
                printNoCourse();
                continue;
            }

            Course chosenCourse = courseService.getCourseByID(courseId);
            boolean flag = false;
            for (Course course : chosenCourseList) {
                if (chosenCourse.getId().equals(course.getId())) {
                    printCourseAlreadyChosen();
                    flag = true;
                }
            }
            if (flag)
                continue;

            if (courseService.isCourseFull(courseId)) {
                System.out.println("!!! Course is full. !!!");
                continue;
            }
            if (courseService.isCourseExpired(courseId)) {
                System.out.println("!!! Course is expired. !!!");
                continue;
            }

            chosenCourseList.add(chosenCourse);
            printSingleLineBreak();
            System.out.println("Course added.");
            if (!confirmChooseNextCourse())
                break;
        } while (true);

        addCustomer(chosenCourseList);
    }

    public static void addCustomer(ArrayList<Course> chosenCourses) {
        ICustomerService customerService = new CustomerService();
        ICourseService courseService = new CourseService();
        String name = null;
        String email = null;
        String address = null;

        String process = "getName";
        do {
            if (process.equals("getName")) {
                printDoubleLineBreak();
                printTwoReturnOptions();
                System.out.println("Please enter students' info:");
            }
            switch (process) {
                case "getName":
                    String tempName = getNewCustomerName();
                    if (tempName.equals("n") || tempName.equals("q")) {
                        printSingleLineBreak();
                        return;
                    }
                    name = tempName;
                    process = "getEmail";
                    break;

                case "getEmail":
                    String tempEmail = getNewCustomerEmail();
                    if (tempEmail.equals("n"))
                        return;
                    if (tempEmail.equals("q")) {
                        process = "getName";
                        printSingleLineBreak();
                        break;
                    }
                    email = tempEmail;
                    process = "getAddress";
                    break;

                case "getAddress":
                    String tempAddress = getNewCustomerAddress();
                    if (tempAddress.equals("n"))
                        return;
                    if (tempAddress.equals("q")) {
                        process = "getEmail";
                        printSingleLineBreak();
                        break;
                    }
                    address = tempAddress;
                    process = "end";
                    break;

                case "confirm":
                    if (!confirmNewStudent(name)) {
                        process = "getName";
                        break;
                    }
                    process = "end";
                    break;
            }
        } while (!process.equals("end"));

        for (Course chosenCourse : chosenCourses) {
            String currentCourseId = chosenCourse.getId();
            String id = getNewCustomerId(currentCourseId);
            courseService.getOneMoreStudent(currentCourseId);
            customerService.addCustomer(new Customer(id, currentCourseId, name, email, address));
        }
        processNewOrder(chosenCourses, name, email);
    }

    public static void processNewOrder(ArrayList<Course> chosenCourses, String name, String email) {
        IOrderService orderService = new OrderService();
        ICourseService courseService = new CourseService();

        Order newOrder = new Order(name, email, UserService.userName);

        long newOrderId = newOrder.getId();
        long totalPrice = 0;

        for (Course course : chosenCourses) {
            String currentCourseID = course.getId();
            String courseName = courseService.getCourseByID(currentCourseID).getName();
            double coursePrice = courseService.getCourseByID(currentCourseID).getFee();
            orderService.addOrderItem(new OrderItem(newOrderId, currentCourseID, courseName, coursePrice));
            totalPrice += coursePrice;
        }

        newOrder.setTotalPrice(totalPrice);
        orderService.addOrder(newOrder);
        System.out.println("!!! Order created successfully !!!");

        if (!confirmPrintBill())
            return;
        printBill(newOrderId);
    }

    public static void printBill(long orderId) {
        ICourseService courseService = new CourseService();
        IOrderService orderService = new OrderService();
        IOrderItemService orderItemService = new OrderItemService();

        Order currentOrder = orderService.getOrderByID(orderId);
        ArrayList<OrderItem> itemList = orderItemService.getOrderItemByOrderId(orderId);

        printDoubleLineBreak();
        System.out.printf("%-70s %-40s\n", "Bill#" + orderId, "Date Created: "
                + ValueFormatter.formatDate(currentOrder.getDateCreated()));
        System.out.printf("%-70s %-40s\n", "Student name: " + currentOrder.getName(), "Created By: " + UserService.userName);
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.printf("%-20s %-25s %-15s %-20s %-15s\n", "STUDENT'S ID", "COURSE", "COURSEID", "PRICE", "STARTDATE");
        System.out.println("-------------------------------------------------------------------------------------------------");
        for (OrderItem item : itemList) {
            System.out.printf("%-20s %-25s %-15s %-20s %-15s\n",
                    item.getId(), item.getCourseName(), item.getCourseId(),
                    ValueFormatter.formatPrice(item.getPrice()),
                    ValueFormatter.formatDate(courseService.getCourseByID(item.getCourseId()).getStartDate()));
        }
        System.out.println();
        System.out.printf("%-62s %-35s\n", "Total price:", ValueFormatter.formatPrice(currentOrder.getTotalPrice()));
        System.out.println("-------------------------------------------------------------------------------------------------");

        int choice = -1;
        do {
            OrderMenu.displayBillMenu();
            try {
                choice = Integer.parseInt(input.nextLine());
                if (choice == 9) {
                    if (!confirm())
                        continue;
                    System.exit(0);
                }
            } catch (Exception e) {
                printWrongInputError();
            }
        } while (choice != 0);
    }

    public static String getNewCustomerName() {
        do {
            System.out.print("Name: ");
            String name = input.nextLine();
            if (name.equals("n") || name.equals("q"))
                return name;
            if (!Validity.isNameValid(name)) {
                System.out.println("Name invalid. Try again.");
                continue;
            }
            return name;
        } while (true);
    }

    public static String getNewCustomerEmail() {
        do {
            System.out.print("Email: ");
            String email = input.nextLine();
            if (email.equals("n") || email.equals("q"))
                return email;
            if (!Validity.isEmailValid(email)) {
                System.out.println("Email invalid. Try again.");
                continue;
            }
            return email;
        } while (true);
    }

    public static String getNewCustomerAddress() {
        do {
            System.out.print("Address: ");
            String address = input.nextLine();
            if (address.equals("n") || address.equals("q"))
                return address;
            if (!Validity.isAddressValid(address)) {
                System.out.println("Address invalid. Try again.");
                continue;
            }
            return address;
        } while (true);
    }

    public static String getNewCustomerId(String courseId) {
        ICustomerService customerService = new CustomerService();

        int count = 1;
        do {
            String newId = (count >= 10) ? String.valueOf(count) : ("0" + count);
            String fullId = courseId + newId;
            if (!customerService.existByID(fullId))
                return newId;
            count++;
        } while (true);
    }

    public static boolean searchCourse() {
        ICourseService courseService = new CourseService();

        System.out.print("Enter keyword: ");
        String keyword = input.nextLine().toLowerCase();
        searchedCourse = courseService.searchCourseByToString(keyword);
        if (searchedCourse.size() == 0) {
            printNoCourse();
            return false;
        }
        return true;
    }

    public static void openSortMenu() {
        ICourseService courseList = new CourseService();

        printSingleLineBreak();
        CourseManagerMenu.displayCourseSorters_Order();
        try {
            int choice = Integer.parseInt(input.nextLine());
            switch (choice) {
                case 1:
                    courseList.sortByEmptySlotASC();
                    break;
                case 2:
                    courseList.sortByEmptySlotDESC();
                    break;
                case 3:
                    courseList.sortByStartDateASC();
                    break;
                case 4:
                    courseList.sortByStartDateDESC();
                    break;
                case 5:
                    courseList.sortByFeeASC();
                    break;
                case 6:
                    courseList.sortByFeeDESC();
                    break;
                case 0:
                    break;
                default:
                    printWrongInputError();
            }
        } catch (Exception e) {
            printWrongInputError();
        }
    }

    public static void displayOrderHistory() {
        IOrderService orderService = new OrderService();
        int choice = -1;
        do {
            renderOrder();
            OrderMenu.displayOrderSorter();
            try {
                choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        orderService.sortByDateCreatedASC();
                        break;
                    case 2:
                        orderService.sortByDateCreatedDESC();
                        break;
                    case 3:
                        orderService.sortByPriceASC();
                        break;
                    case 4:
                        orderService.sortByPriceDESC();
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

    public static void renderOrder() {
        IOrderService orderService = new OrderService();
        ArrayList<Order> orderList = orderService.getOrders();
        if (orderList.size() == 0) {
            printNoOrder();
            return;
        }

        double sumPrice = 0;
        for (Order order: orderList) {
            sumPrice += order.getTotalPrice();
        }
        printDoubleLineBreak();
        System.out.printf("%-15s %-25s %-30s %-15s %-15s %-15s\n", "Order ID", "Student's Name", "Email", "Price", "Date created", "Created by");
        for (Order order : orderList) {
            System.out.printf("%-15s %-25s %-30s %-15s %-15s %-15s\n", order.getId(), order.getName(), order.getEmail(),
                    ValueFormatter.formatPrice(order.getTotalPrice()), ValueFormatter.formatDate(order.getDateCreated()),
                    order.getNameCreator());
        }
        System.out.println();
        System.out.printf("%-72s %-15s\n", "Total price:", ValueFormatter.formatPrice(sumPrice));
        System.out.println();
    }
}
