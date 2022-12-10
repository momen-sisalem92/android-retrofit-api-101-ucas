package com.example.android_retrofit_api_101_ucas.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class Model {

    protected String getErrorResponse(byte[] errorBytesArray) {
        try {
            String errorResponse = new String(errorBytesArray, StandardCharsets.UTF_8);
            JSONObject object = new JSONObject(errorResponse);
            return object.getString("message");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "Something went wrong, try again!";
    }
}
