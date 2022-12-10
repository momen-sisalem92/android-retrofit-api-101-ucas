package com.example.android_retrofit_api_101_ucas.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_retrofit_api_101_ucas.R;
import com.example.android_retrofit_api_101_ucas.api.controllers.AuthApiController;
import com.example.android_retrofit_api_101_ucas.databinding.ActivityMainBinding;
import com.example.android_retrofit_api_101_ucas.interfaces.ListCallback;
import com.example.android_retrofit_api_101_ucas.interfaces.ProcessCallback;
import com.example.android_retrofit_api_101_ucas.models.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    private UsersRecyclerAdapter adapter;
    private List<User> users = new ArrayList<>();
    private ActivityMainBinding activityMainBinding;
//    UsersApiController usersApiController = new UsersApiController();
    AuthApiController authApiController = new AuthApiController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        initializeView();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_logout) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeView() {
        initializeRecyclerAdapter();
        getUsers();
    }

    private void initializeRecyclerAdapter() {
//        adapter = new UsersRecyclerAdapter(users);
//        activityMainBinding.usersRecyclerView.setAdapter(adapter);
    }

    private void getUsers() {
    }

    private void logout() {
        authApiController.logout(new ProcessCallback() {
            @Override
            public void onSuccess(String message) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }
}