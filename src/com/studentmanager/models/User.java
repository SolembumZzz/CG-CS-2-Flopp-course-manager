package com.studentmanager.models;

public class User {
    private String username;
    private String password;
    private Role role;

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String csv) {
        String[] newUser = csv.split(";");
        this.username = newUser[0];
        this.password = newUser[1];
        this.role = Role.getRole(newUser[2]);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return getUsername() + ";" + getPassword() + ";" + getRole();
    }
}
