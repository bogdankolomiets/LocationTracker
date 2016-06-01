package com.example.bogdan.testsmartadventure.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.bogdan.testsmartadventure.service.LocationService;
import com.example.bogdan.testsmartadventure.view.base.BaseActivity;
import com.example.bogdan.testsmartadventure.R;

import butterknife.Bind;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 30.05.16
 */
public class MainActivity extends BaseActivity {
    private final static int LAYOUT = R.layout.activity_main;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        setSupportActionBar(toolbar);

        Intent locationServiceIntent = new Intent(this, LocationService.class);
        startService(locationServiceIntent);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_fragment, menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
