package com.example.android_retrofit_api_101_ucas.models;

import android.util.Log;

import com.example.android_retrofit_api_101_ucas.api.controllers.ApiController;
import com.example.android_retrofit_api_101_ucas.interfaces.ProcessCallback;
import com.example.android_retrofit_api_101_ucas.prefs.AppSharedPreferences;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Student {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("full_name")
    @Expose
    public String fullName;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("verification_code")
    @Expose
    public Object verificationCode;
    @SerializedName("fcm_token")
    @Expose
    public Object fcmToken;
    @SerializedName("token")
    @Expose
    public String token;
    @SerializedName("refresh_token")
    @Expose
    public String refreshToken;
    @SerializedName("is_active")
    @Expose
    public Boolean isActive;

    public String password;

    public void login( ProcessCallback callback) {
        Call<BaseResponse<Student>> call = ApiController.getInstance().getRetrofitRequests().login(email, password);
        call.enqueue(new Callback<BaseResponse<Student>>() {
            @Override
            public void onResponse(Call<BaseResponse<Student>> call, Response<BaseResponse<Student>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    AppSharedPreferences.getInstance().save(response.body().object);
                    callback.onSuccess(response.body().message);
                } else {
                    try {
                        String error = new String(response.errorBody().bytes(), StandardCharsets.UTF_8);
                        JSONObject jsonObject = new JSONObject(error);
                        Log.e("Retrofit-API", "onResponse: " + error);
                        Log.e("Retrofit-API", "onResponse: " + jsonObject.getString("message"));
                        callback.onFailure(jsonObject.getString("message"));
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Student>> call, Throwable t) {
                callback.onFailure("Something went wrong");
            }
        });
    }
}
