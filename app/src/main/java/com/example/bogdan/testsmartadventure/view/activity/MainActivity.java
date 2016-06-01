package com.example.bogdan.testsmartadventure.view.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.bogdan.testsmartadventure.R;
import com.example.bogdan.testsmartadventure.service.LocationService;
import com.example.bogdan.testsmartadventure.view.MainView;
import com.example.bogdan.testsmartadventure.view.base.BaseActivity;
import com.example.bogdan.testsmartadventure.view.fragment.UserListFragment;
import com.google.firebase.auth.FirebaseAuth;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 30.05.16
 */
public class MainActivity extends BaseActivity implements MainView {
    private final static int LAYOUT = R.layout.activity_main;
    private Configuration mConfig;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mConfig = getResources().getConfiguration();
        if (isMobileDevice()) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new UserListFragment())
                    .commit();
        }
        Intent locationServiceIntent = new Intent(this, LocationService.class);
        startService(locationServiceIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        if (!isMobilePortraiteOrientation() || !isMobileDevice()) {
            menu.findItem(R.id.goMap).setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.signOut:
                signOut();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void signOut() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MainActivity.this, SignInActivity.class));
        finish();
    }

    private boolean isMobileDevice() {
        return mConfig.orientation == 1 && mConfig.smallestScreenWidthDp < 600;
    }

    private boolean isMobilePortraiteOrientation() {
        return mConfig.orientation == 1;
    }



}
