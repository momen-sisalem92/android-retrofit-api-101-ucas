package com.example.android_retrofit_api_101_ucas.api.controllers;


import com.example.android_retrofit_api_101_ucas.interfaces.ListCallback;
import com.example.android_retrofit_api_101_ucas.interfaces.ProcessCallback;
import com.example.android_retrofit_api_101_ucas.models.BaseResponse;
import com.example.android_retrofit_api_101_ucas.models.StudentImage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImagesApiController {

    public void uploadImage(byte[] bytes, ProcessCallback callback) {
        if(bytes != null) {
            StudentImage image = new StudentImage();
            image.imageBytesArray = bytes;
            image.uploadImage(callback);
        }else {
            callback.onFailure("Select image to upload");
        }
    }

    public void deleteImage(StudentImage studentImage, ProcessCallback callback) {
        studentImage.deleteImage(callback);
    }

    public void getImages(ListCallback<StudentImage> callback) {
        StudentImage.getImages(callback);
    }
}
