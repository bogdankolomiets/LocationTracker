package com.example.bogdan.testsmartadventure;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 30.05.16
 */
public class SignInActivity extends BaseActivity implements SignInView, View.OnClickListener {
    private final static int LAYOUT = R.layout.activity_sign_in;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

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

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        mSignInButton.setOnClickListener(this);
        mSignUpButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signInBtn:
                signIn(getEmailField(), getPasswordField());
                break;
            case R.id.signUpBtn:
                signUp(getEmailField(), getPasswordField());
                break;
        }
    }

    @Override
    public void signIn(String email, String password) {
        showLoading();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        stopLoading();

                        if (task.isSuccessful()) {

                        } else  {
                            Toast.makeText(SignInActivity.this, "Sign In failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void signUp(String email, String password) {
        showLoading();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        stopLoading();

                        if (task.isSuccessful()) {
                            onAuthSuccess(task.getResult().getUser());
                        } else {
                            Toast.makeText(SignInActivity.this, "Sign Up failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    private void onAuthSuccess(FirebaseUser user) {
        String userName = userNameFromEmail(user.getEmail());

        writeNewUser(user.getUid(), user.getEmail(), userName);

    }

    private String userNameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    private void writeNewUser(String uId, String email, String name) {
        User user = new User(email, name);

        mDatabase.child("users").child(uId).setValue(user);
    }

    private String getEmailField() {
        return mEmailField.getText().toString();
    }

    private String getPasswordField() {
        return mPasswordField.getText().toString();
    }
}
