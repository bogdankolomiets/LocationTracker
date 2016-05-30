package com.example.bogdan.testsmartadventure;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 30.05.16
 */
public class User {

    private String mEmail;

    private String mName;

    public User(String email, String name) {
        mEmail = email;
        mName = name;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getName() {
        return mName;
    }

}
