package com.example.bogdan.testsmartadventure.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 01.06.16
 */
@IgnoreExtraProperties
public class UserLocation {
    private double latitude;
    private double longitude;

    public UserLocation() {

    }

    public UserLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
