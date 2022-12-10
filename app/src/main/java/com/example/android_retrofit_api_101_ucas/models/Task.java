package com.example.android_retrofit_api_101_ucas.models;

import com.example.android_retrofit_api_101_ucas.api.controllers.ApiController;
import com.example.android_retrofit_api_101_ucas.interfaces.ListCallback;
import com.example.android_retrofit_api_101_ucas.interfaces.ProcessCallback;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Task extends Model {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("student_id")
    @Expose
    public String studentId;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("is_done")
    @Expose
    public Boolean isDone;

    public static String name = "";

    public static void all(ListCallback<Task> callback) {
        Call<BaseResponse<Task>> call = ApiController.getInstance().getRetrofitRequests().getTasks();
        call.enqueue(new Callback<BaseResponse<Task>>() {
            @Override
            public void onResponse(Call<BaseResponse<Task>> call, Response<BaseResponse<Task>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().data);
                } else {
                    callback.onFailure("");
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Task>> call, Throwable t) {
                callback.onFailure("");
            }
        });
    }

    //Async
    public void save(ProcessCallback callback) {
        Call<BaseResponse> call = ApiController.getInstance().getRetrofitRequests().saveTask(title);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                callback.onSuccess("");
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                callback.onFailure("");
            }
        });
    }

    public ApiResponse saveSync() {
        Call<BaseResponse> call = ApiController.getInstance().getRetrofitRequests().saveTask(title);
        try {
            Response<BaseResponse> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                return new ApiResponse(response.body().message, true);
            } else {
                return new ApiResponse(getErrorResponse(response.errorBody().bytes()), false);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ApiResponse("Something went wrong, try again", false);
        }
    }

    public void delete(ProcessCallback callback) {
        Call<BaseResponse> call = ApiController.getInstance().getRetrofitRequests().deleteTask(id);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
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

    public void update(ProcessCallback callback) {
        Call<BaseResponse> call = ApiController.getInstance().getRetrofitRequests().updateTask(id, title);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
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
}
