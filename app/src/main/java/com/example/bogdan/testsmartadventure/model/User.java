package com.example.bogdan.testsmartadventure.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 30.05.16
 */
@IgnoreExtraProperties
public class User {

    private String email;

    private String name;

    public User() {

    }

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

}
