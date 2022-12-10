package com.example.android_retrofit_api_101_ucas.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_retrofit_api_101_ucas.R;
import com.example.android_retrofit_api_101_ucas.api.controllers.AuthApiController;
import com.example.android_retrofit_api_101_ucas.databinding.ActivityLoginBinding;
import com.example.android_retrofit_api_101_ucas.interfaces.ProcessCallback;
import com.example.android_retrofit_api_101_ucas.models.Student;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityLoginBinding activityLoginBinding;
    AuthApiController authApiController = new AuthApiController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(activityLoginBinding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeView();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        finish();
    }

    private void initializeView() {
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        activityLoginBinding.loginButton.setOnClickListener(this);
        activityLoginBinding.createNow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_button) {
            performLogin();
        } else if (v.getId() == R.id.create_now) {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
        }
    }

    private void performLogin() {
        login();
    }

    private void login() {
        authApiController.login(getStudent(), new ProcessCallback() {
            @Override
            public void onSuccess(String message) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(String message) {
                //TODO: must show error message
                Log.d("Retrofit", "onFailure: ");
                Snackbar.make(activityLoginBinding.getRoot(), message, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private Student getStudent() {
        Student student = new Student();
        student.email = activityLoginBinding.emailEditText.getText().toString().trim();
        student.password = activityLoginBinding.passwordEditText.getText().toString().trim();
        return student;
    }
}