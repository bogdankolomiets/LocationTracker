package com.example.bogdan.testsmartadventure.view;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 30.05.16
 */
public interface SignInView {

    String getEmailField();

    String getPasswordField();

    void showLoading();

    void stopLoading();

    void showError(String exception);

    void goToApplication();
}
