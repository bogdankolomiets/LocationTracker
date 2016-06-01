package com.example.bogdan.testsmartadventure.service;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.bogdan.testsmartadventure.util.DBUtils;
import com.example.bogdan.testsmartadventure.util.LocationUtils;
import com.google.android.gms.location.LocationListener;
import com.google.firebase.auth.FirebaseAuth;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 01.06.16
 */
public class LocationService extends Service {
    LocationUtils mLocationUtils;
    FirebaseAuth mFirebaseAuth;

    @Override
    public void onCreate() {
        if (mLocationUtils == null) {
            mLocationUtils = new LocationUtils(LocationService.this, locationListener);
        }
        mFirebaseAuth = FirebaseAuth.getInstance();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mLocationUtils.startLoading();
        return START_NOT_STICKY;
    }

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (mFirebaseAuth.getCurrentUser() != null) {
                Toast.makeText(LocationService.this, "Latitude = " + String.valueOf(location.getLatitude())
                        + ", Longitude = " + String.valueOf(location.getLongitude())
                        + "Current User = " + mFirebaseAuth.getCurrentUser().getEmail(), Toast.LENGTH_LONG).show();

                DBUtils.updateUserLocation(mFirebaseAuth.getCurrentUser(), location);
            }
        }

    };
}
