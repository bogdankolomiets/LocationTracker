package com.example.bogdan.testsmartadventure.util;

import android.location.Location;

import com.example.bogdan.testsmartadventure.model.User;
import com.example.bogdan.testsmartadventure.model.UserLocation;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 30.05.16
 */
public class DBUtils {
    private static DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    private DBUtils() {

    }

    public static void writeNewUser(String uId, String email, String name) {
        User user = new User(email, name);

        mDatabase.child("users").child(uId).setValue(user);
    }

    public static Query getUsers() {
        return mDatabase.child("users").orderByPriority();
    }

    public static void updateUserLocation(FirebaseUser currentUser, Location location) {
        UserLocation userLocation = new UserLocation(location.getLatitude(), location.getLongitude());
        mDatabase.child("users").child(currentUser.getUid()).child("location").setValue(userLocation);
    }
}
