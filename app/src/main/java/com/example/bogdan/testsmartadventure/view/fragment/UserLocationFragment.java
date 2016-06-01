package com.example.bogdan.testsmartadventure.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bogdan.testsmartadventure.model.UserLocation;
import com.example.bogdan.testsmartadventure.presenter.UserLocationPresenter;
import com.example.bogdan.testsmartadventure.presenter.impl.UserLocationPresenterImpl;
import com.example.bogdan.testsmartadventure.view.UserLocationView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 31.05.16
 */
public class UserLocationFragment extends SupportMapFragment implements UserLocationView, OnMapReadyCallback {
    private GoogleMap mGoogleMap;
    private UserLocationPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        mPresenter = new UserLocationPresenterImpl(this);
        return super.onCreateView(layoutInflater, viewGroup, bundle);

    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mPresenter.onDataChanged();
    }

    @Override
    public void addNewUser(UserLocation location) {
        mGoogleMap.addMarker(new MarkerOptions()
                .position(new LatLng(location.getLatitude(), location.getLongitude())));
    }
}
