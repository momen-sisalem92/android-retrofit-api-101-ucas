package com.example.android_retrofit_api_101_ucas.api;

import com.example.android_retrofit_api_101_ucas.models.BaseResponse;
import com.example.android_retrofit_api_101_ucas.models.Category;
import com.example.android_retrofit_api_101_ucas.models.Student;
import com.example.android_retrofit_api_101_ucas.models.StudentImage;
import com.example.android_retrofit_api_101_ucas.models.Task;
import com.example.android_retrofit_api_101_ucas.models.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

//Service Interface - Retrofit - API EndPoints
public interface RetrofitRequests {

    @GET("users")
    Call<BaseResponse<User>> getUsers();

    @GET("categories")
    Call<BaseResponse<Category>> getCategories();

    @FormUrlEncoded
    @POST("students/auth/login")
    Call<BaseResponse<Student>> login(@Field("email") String email, @Field("password") String password);

    @GET("students/auth/logout")
//    Call<BaseResponse> logout(@Header("Authorization") String token);
    Call<BaseResponse> logout();

    //***********************
    @GET("tasks/{id}")
    Call<BaseResponse> getTask(@Path("id") int id);

    @GET("tasks")
    Call<BaseResponse<Task>> getTasks();

    @DELETE("tasks/{id}")
    Call<BaseResponse> deleteTask(@Path("id") int id);

    @FormUrlEncoded
    @PUT("tasks/{id}")
    Call<BaseResponse> updateTask(@Path("id") int id, @Field("title") String title);

    @FormUrlEncoded
    @POST("tasks")
    Call<BaseResponse> saveTask(@Field("title") String title);

    //***********************
    @Multipart
    @POST("student/images")
    Call<BaseResponse> uploadImage(@Part MultipartBody.Part image);

    @GET("student/images")
    Call<BaseResponse<StudentImage>> getImages();

    @DELETE("student/images/{id}")
    Call<BaseResponse> deleteImage(@Path("id") String id);
}
