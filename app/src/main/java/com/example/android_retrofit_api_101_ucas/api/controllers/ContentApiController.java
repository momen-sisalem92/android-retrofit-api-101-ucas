package com.example.android_retrofit_api_101_ucas.api.controllers;

import com.example.android_retrofit_api_101_ucas.AppController;
import com.example.android_retrofit_api_101_ucas.interfaces.ListCallback;
import com.example.android_retrofit_api_101_ucas.models.BaseResponse;
import com.example.android_retrofit_api_101_ucas.models.Category;
import com.example.android_retrofit_api_101_ucas.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContentApiController {

    private ApiController apiController;

    public ContentApiController() {
        apiController = ApiController.getInstance();
    }

    public void getUsers(ListCallback<User> callback) {
        Call<BaseResponse<User>> call = apiController.getRetrofitRequests().getUsers();
        call.enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().data);
                } else {
                    callback.onFailure("No data");
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<User>> call, Throwable t) {

            }
        });
    }

    public void getCategories() {
        Call<BaseResponse<Category>> call = apiController.getRetrofitRequests().getCategories();
        call.enqueue(new Callback<BaseResponse<Category>>() {
            @Override
            public void onResponse(Call<BaseResponse<Category>> call, Response<BaseResponse<Category>> response) {

            }

            @Override
            public void onFailure(Call<BaseResponse<Category>> call, Throwable t) {

            }
        });
    }
}
