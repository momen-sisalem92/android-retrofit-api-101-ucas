package com.example.android_retrofit_api_101_ucas.api.controllers;

import android.util.Log;

import com.example.android_retrofit_api_101_ucas.interfaces.ProcessCallback;
import com.example.android_retrofit_api_101_ucas.models.BaseResponse;
import com.example.android_retrofit_api_101_ucas.models.Student;
import com.example.android_retrofit_api_101_ucas.models.User;
import com.example.android_retrofit_api_101_ucas.prefs.AppSharedPreferences;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthApiController {

    public void login(Student student, ProcessCallback callback) {
        if (!student.email.isEmpty() && !student.password.isEmpty()) {
            student.login(callback);
        }else {
            callback.onFailure("Enter required data!");
        }
    }

    public void logout(ProcessCallback callback) {
        Call<BaseResponse> call = ApiController.getInstance().getRetrofitRequests().logout();
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200 || response.code() == 401) {
                    AppSharedPreferences.getInstance().clear();
                    callback.onSuccess(response.code() == 200 ? response.body().message : "Logged out successfully");
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                callback.onFailure("Something went wrong");
            }
        });
    }
}
