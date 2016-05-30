package com.example.bogdan.testsmartadventure;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 30.05.16
 */
public abstract class BaseActivity extends AppCompatActivity{

    private ProgressDialog mProgressDialog;

    public void showLoading() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Loading...");
        }

        mProgressDialog.show();
    }

    public void stopLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

//    protected String getUid() {
//        return FirebaseAuth.getInstance().getCurrentUser().getUid();
//    }


}
