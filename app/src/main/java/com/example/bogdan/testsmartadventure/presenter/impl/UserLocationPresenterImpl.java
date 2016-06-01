package com.example.bogdan.testsmartadventure.presenter.impl;

import com.example.bogdan.testsmartadventure.model.UserLocation;
import com.example.bogdan.testsmartadventure.presenter.UserLocationPresenter;
import com.example.bogdan.testsmartadventure.view.UserLocationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 01.06.16
 */
public class UserLocationPresenterImpl implements UserLocationPresenter {
    private UserLocationView mView;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    public UserLocationPresenterImpl(UserLocationView view) {
        mView = view;
    }

    @Override
    public void onDataChanged() {
        mDatabase.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot users : dataSnapshot.getChildren()) {
                    UserLocation location = users.child("location").getValue(UserLocation.class);
                    if (location != null) {
                        mView.addNewUser(location);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
