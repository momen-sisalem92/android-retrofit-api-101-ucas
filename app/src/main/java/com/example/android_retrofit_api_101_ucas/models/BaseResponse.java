
package com.example.android_retrofit_api_101_ucas.models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResponse<Model> {

    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public List<Model> data = null;
    @SerializedName("object")
    @Expose
    public Model object;
}
