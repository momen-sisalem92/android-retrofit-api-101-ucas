package com.example.android_retrofit_api_101_ucas.api;

import com.example.android_retrofit_api_101_ucas.models.BaseResponse;
import com.example.android_retrofit_api_101_ucas.models.Category;
import com.example.android_retrofit_api_101_ucas.models.User;

import retrofit2.Call;
import retrofit2.http.GET;

//Service Interface - Retrofit - API EndPoints
public interface RetrofitRequests {

    @GET("users")
    Call<BaseResponse<User>> getUsers();

    @GET("categories")
    Call<BaseResponse<Category>> getCategories();
}
