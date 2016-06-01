package com.example.bogdan.testsmartadventure.presenter.impl;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.bogdan.testsmartadventure.presenter.SignInPresenter;
import com.example.bogdan.testsmartadventure.util.DBUtils;
import com.example.bogdan.testsmartadventure.view.SignInView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 01.06.16
 */
public class SignInPresenterImpl implements SignInPresenter {
    private SignInView mView;
    private FirebaseAuth mAuth;

    public SignInPresenterImpl(SignInView view) {
        mView = view;
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onSignInClick() {
        String email = mView.getEmailField();
        String password = mView.getPasswordField();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            mView.showError("Please, enter your data");
            return;
        }

        mView.showLoading();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mView.stopLoading();

                        if (task.isSuccessful()) {
                            onAuthSuccess(task.getResult().getUser());
                        } else {
                            mView.showError(String.valueOf(task.getException()));
                        }
                    }
                });
    }

    @Override
    public void onSignUpClick() {
        String email = mView.getEmailField();
        String passworrd = mView.getPasswordField();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(passworrd)) {
            mView.showError("Please, enter your data");
            return;
        }

        mView.showLoading();
        mAuth.createUserWithEmailAndPassword(email, passworrd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mView.stopLoading();

                        if (task.isSuccessful()) {
                            onAuthSuccess(task.getResult().getUser());
                        } else {
                            mView.showError(String.valueOf(task.getException()));
                        }
                    }
                });
    }

    @Override
    public void onSignOutClick() {
        mAuth.signOut();

    }

    private void onAuthSuccess(FirebaseUser user) {
        String userName = userNameFromEmail(user.getEmail());

        DBUtils.writeNewUser(user.getUid(), user.getEmail(), userName);
        mView.goToApplication();
    }

    private String userNameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }
}
