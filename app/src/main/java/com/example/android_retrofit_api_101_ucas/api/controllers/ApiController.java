package com.example.android_retrofit_api_101_ucas.api.controllers;

import android.content.Context;

import com.example.android_retrofit_api_101_ucas.api.RetrofitRequests;
import com.example.android_retrofit_api_101_ucas.api.RetrofitSettings;

public class ApiController {

    private Context context;
    private RetrofitRequests retrofitRequests;
    private static ApiController instance;

    private ApiController(Context context) {
        this.context = context;
        retrofitRequests = RetrofitSettings.getInstance().create(RetrofitRequests.class);
    }

    public static synchronized ApiController getInstance(Context context) {
        if(instance == null) {
            instance = new ApiController(context);
        }
        return instance;
    }

    public RetrofitRequests getRetrofitRequests() {
        return retrofitRequests;
    }
}
