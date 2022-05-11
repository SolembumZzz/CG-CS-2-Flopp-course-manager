package com.studentmanager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.studentmanager.views.AdminView;
import com.studentmanager.models.User;
import com.studentmanager.utils.CSVUtils;
import com.studentmanager.views.UserView;

import static com.studentmanager.utils.Warnings.*;

public class UserService implements IUserService {
    static Scanner input = new Scanner(System.in);
    public static final String path = "data/users.csv";

    public static String userName;

    @Override
    public ArrayList<User> getUsers() {
        ArrayList<User> userList = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String item : records) {
            userList.add(new User(item));
        }
        return userList;
    }

    @Override
    public void signIn() {
        printOneReturnOption();
        String username = enterUsername();
        if (username.equals("n")) return;
        String password = enterPassword();
        if (password.equals("n")) return;
        if (!exist(username) || !checkPassword(username,password)) {
            printLogInError();
            return;
        }
        UserService.userName = username;
        getRole(username);
    }

    @Override
    public void getRole(String username) {
        String role = getUserByUserName(username).getRole().toString();
        if (role.equals("ADMIN")) {
            AdminView access = new AdminView();
            access.accessAdmin();
        }
        if (role.equals("STAFF")) {
            UserView access = new UserView();
            access.accessStaff();
        }
    }

    @Override
    public User getUserByUserName(String userName) {
        for (User user : getUsers()) {
            if (userName.equals(user.getUsername())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean exist(String userName) {
        return getUserByUserName(userName) != null;
    }

    @Override
    public boolean checkPassword(String userName, String password) {
        return password.equals(getUserByUserName(userName).getPassword());
    }

    @Override
    public String enterUsername() {
        String username = null;
        do {
            System.out.print("Enter username: ");
            try {
                username = input.nextLine();
                if (username.equals("n")) {
                    return username;
                }
                break;
            } catch (Exception e) {
                printWrongInputError();
            }
        } while (username != null);
        return username;
    }

    @Override
    public String enterPassword() {
        String password = null;
        do {
            System.out.print("Enter password: ");
            try {
                password = input.nextLine();
                if (password.equals("n")) {
                    return password;
                }
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (password != null);
        return password;
    }
}
