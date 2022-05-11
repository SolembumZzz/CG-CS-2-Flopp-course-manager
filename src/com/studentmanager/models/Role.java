package com.studentmanager.models;

public enum Role {
    ADMIN,
    STAFF,
    STUDENT;

    public static Role getRole(String myRole) {
        for (Role role : values()) {
            if (role.toString().equals(myRole))
                return role;
        }
        throw new IllegalArgumentException("Invalid.");
    }
}
