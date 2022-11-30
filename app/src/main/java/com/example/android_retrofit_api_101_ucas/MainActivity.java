package com.example.android_retrofit_api_101_ucas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.android_retrofit_api_101_ucas.api.controllers.ContentApiController;
import com.example.android_retrofit_api_101_ucas.interfaces.ListCallback;
import com.example.android_retrofit_api_101_ucas.models.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUsers();
    }

    private void getUsers() {
        ContentApiController controller = new ContentApiController();
        controller.getUsers(new ListCallback<User>() {
            @Override
            public void onSuccess(List<User> list) {
                Log.d("API-RETROFIT-REQUEST", "onSuccess: "+list.size());
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }
}