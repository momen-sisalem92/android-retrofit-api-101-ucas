package com.example.android_retrofit_api_101_ucas.models;

public class ApiResponse {

    final String message;
    final boolean success;

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
