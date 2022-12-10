package com.example.android_retrofit_api_101_ucas.api.controllers;

import android.content.Context;

import com.example.android_retrofit_api_101_ucas.api.RetrofitRequests;
import com.example.android_retrofit_api_101_ucas.api.RetrofitSettings;

public class ApiController {

    private RetrofitRequests retrofitRequests;
    private static ApiController instance;

    private ApiController() {
        retrofitRequests = RetrofitSettings.getInstance().create(RetrofitRequests.class);
    }

    public static synchronized ApiController getInstance() {
        if(instance == null) {
            instance = new ApiController();
        }
        return instance;
    }

    public RetrofitRequests getRetrofitRequests() {
        return retrofitRequests;
    }
}
