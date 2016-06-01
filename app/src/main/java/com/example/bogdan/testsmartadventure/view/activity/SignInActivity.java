package com.example.bogdan.testsmartadventure.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bogdan.testsmartadventure.presenter.SignInPresenter;
import com.example.bogdan.testsmartadventure.presenter.impl.SignInPresenterImpl;
import com.example.bogdan.testsmartadventure.util.DBUtils;
import com.example.bogdan.testsmartadventure.view.base.BaseActivity;
import com.example.bogdan.testsmartadventure.R;
import com.example.bogdan.testsmartadventure.view.SignInView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 30.05.16
 */
public class SignInActivity extends BaseActivity implements SignInView, View.OnClickListener {
    private final static int LAYOUT = R.layout.activity_sign_in;

    private FirebaseAuth mAuth;

    private SignInPresenter mPresenter;

    @Bind(R.id.emailField)
    EditText mEmailField;

    @Bind(R.id.passwordField)
    EditText mPasswordField;

    @Bind(R.id.signInBtn)
    Button mSignInButton;

    @Bind(R.id.signUpBtn)
    Button mSignUpButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        ButterKnife.bind(this);

        mPresenter = new SignInPresenterImpl(this);

        mAuth = FirebaseAuth.getInstance();

        mSignInButton.setOnClickListener(this);
        mSignUpButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signInBtn:
                mPresenter.onSignInClick();
                break;
            case R.id.signUpBtn:
                mPresenter.onSignUpClick();
                break;
        }
    }

    @Override
    public String getEmailField() {
        return mEmailField.getText().toString();
    }

    @Override
    public String getPasswordField() {
        return mPasswordField.getText().toString();
    }

    @Override
    public void showError(String exception) {
        Toast.makeText(this, exception, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToApplication() {

        startActivity(new Intent(SignInActivity.this, MainActivity.class));
        finish();
    }
}
