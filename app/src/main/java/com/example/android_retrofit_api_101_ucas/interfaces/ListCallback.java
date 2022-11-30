package com.example.android_retrofit_api_101_ucas.interfaces;

import java.util.List;

public interface ListCallback<Model> {

    void onSuccess(List<Model> list);

    void onFailure(String message);
}
