package com.example.bogdan.testsmartadventure.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bogdan.testsmartadventure.presenter.UserListPresenter;
import com.example.bogdan.testsmartadventure.presenter.impl.UserListPresenterImpl;
import com.example.bogdan.testsmartadventure.util.DBUtils;
import com.example.bogdan.testsmartadventure.view.base.BaseFragment;
import com.example.bogdan.testsmartadventure.R;
import com.example.bogdan.testsmartadventure.adapter.UserFirebaseAdapter;
import com.example.bogdan.testsmartadventure.view.UserListView;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 30.05.16
 */
public class UserListFragment extends BaseFragment implements UserListView {
    private final static int LAYOUT = R.layout.fragment_user_list;

    private UserListPresenter mPresenter;

    @Bind(R.id.userList)
    RecyclerView mUserList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mPresenter = new UserListPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(LAYOUT, container, false);
        ButterKnife.bind(this, rootView);
        mPresenter.onCreateView(savedInstanceState);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());

        mUserList.setLayoutManager(llm);


        return rootView;

    }

    @Override
    public void showUsers(Query users) {
        mUserList.setAdapter(new UserFirebaseAdapter(users));
    }
}
