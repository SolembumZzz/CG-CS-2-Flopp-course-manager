package com.studentmanager.services;

import com.studentmanager.models.User;

import java.util.ArrayList;

public interface IUserService {

    ArrayList<User> getUsers();

    public void signIn();

    public void getRole(String username);

    boolean exist(String userName);

    boolean checkPassword(String username, String password);

    User getUserByUserName(String username);

    public String enterUsername();
    public String enterPassword();

}
