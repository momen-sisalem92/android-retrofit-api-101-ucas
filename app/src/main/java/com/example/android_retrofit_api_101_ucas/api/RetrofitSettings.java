package com.example.android_retrofit_api_101_ucas.api;

import com.example.android_retrofit_api_101_ucas.prefs.AppSharedPreferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSettings {

    //Instance: Retrofit
    private static Retrofit retrofit;

    //Instance: BaseURL
    private static final String BaseURL = "https://demo-api.mr-dev.tech/api/";

    private RetrofitSettings() {
    }

    public static synchronized Retrofit getInstance() {
        if (retrofit == null) {
            //Builder DesignPattern
            retrofit = new Retrofit.Builder()
                    .baseUrl(BaseURL)
                    .client(getClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
                builder.addHeader("Accept", "application/json");
                builder.addHeader("Content-Type", "application/json");
                builder.addHeader("Authorization", AppSharedPreferences.getInstance().getToken());
                return chain.proceed(builder.build());
            }
        }).build();

        return client;
    }
}

//class Test {
//
//    public Test setFirst() {
//        return this;
//    }
//
//    public Test setSecond() {
//        return this;
//    }
//
//    public String build() {
//        return "";
//    }
//}