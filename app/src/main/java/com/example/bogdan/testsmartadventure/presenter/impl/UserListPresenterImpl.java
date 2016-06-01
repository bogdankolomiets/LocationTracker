package com.example.bogdan.testsmartadventure.presenter.impl;

import android.os.Bundle;

import com.example.bogdan.testsmartadventure.presenter.UserListPresenter;
import com.example.bogdan.testsmartadventure.util.DBUtils;
import com.example.bogdan.testsmartadventure.view.UserListView;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 01.06.16
 */
public class UserListPresenterImpl implements UserListPresenter {
    private UserListView mView;

    public UserListPresenterImpl(UserListView view) {
        mView = view;
    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {
        mView.showUsers(DBUtils.getUsers());
    }
}

