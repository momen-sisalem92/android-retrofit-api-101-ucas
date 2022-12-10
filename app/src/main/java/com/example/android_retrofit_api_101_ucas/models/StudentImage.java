package com.example.android_retrofit_api_101_ucas.models;

import com.example.android_retrofit_api_101_ucas.api.controllers.ApiController;
import com.example.android_retrofit_api_101_ucas.interfaces.ListCallback;
import com.example.android_retrofit_api_101_ucas.interfaces.ProcessCallback;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

public class StudentImage {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("student_id")
    @Expose
    public String studentId;
    @SerializedName("image_url")
    @Expose
    public String imageUrl;

    public byte[] imageBytesArray;

    public void uploadImage(ProcessCallback callback) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imageBytesArray);
        MultipartBody.Part file = MultipartBody.Part.createFormData("image", "image-file", requestBody);
        Call<BaseResponse> call = ApiController.getInstance().getRetrofitRequests().uploadImage(file);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess("");
                } else {
                    callback.onFailure("");
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                callback.onFailure("");
            }
        });
    }

    public void deleteImage(ProcessCallback callback) {
        Call<BaseResponse> call = ApiController.getInstance().getRetrofitRequests().deleteImage(id.toString());
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    //
                    callback.onSuccess("");
                } else {
                    //
                    callback.onFailure("");
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                callback.onFailure("");
            }
        });
    }

    public static void getImages(ListCallback<StudentImage> callback) {
        Call<BaseResponse<StudentImage>> call = ApiController.getInstance().getRetrofitRequests().getImages();
        call.enqueue(new Callback<BaseResponse<StudentImage>>() {
            @Override
            public void onResponse(Call<BaseResponse<StudentImage>> call, Response<BaseResponse<StudentImage>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().data);
                } else {
                    callback.onFailure("");
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<StudentImage>> call, Throwable t) {
                callback.onFailure("");
            }
        });
    }
}
