package com.example.android_retrofit_api_101_ucas.api.controllers;

import com.example.android_retrofit_api_101_ucas.interfaces.ListCallback;
import com.example.android_retrofit_api_101_ucas.interfaces.ProcessCallback;
import com.example.android_retrofit_api_101_ucas.models.ApiResponse;
import com.example.android_retrofit_api_101_ucas.models.Task;

public class TaskController {

    public void all(ListCallback<Task> callback) {
        Task.all(callback);
    }

    public void save(String title, ProcessCallback callback) {
        if (!title.trim().isEmpty()) {
            Task task = new Task();
            task.title = title;
            task.save(callback);
        } else {
            callback.onFailure("Enter required data");
        }
    }

    public ApiResponse saveSync(String title) {
        if (!title.trim().isEmpty()) {
            Task task = new Task();
            task.title = title;
            return task.saveSync();
        } else {
            return new ApiResponse("Enter required data", false);
        }
    }

    public void update(Task task, ProcessCallback callback) {
        if (task != null && !task.title.isEmpty()) {
            task.update(callback);
        } else {
            callback.onFailure("Enter required data");
        }
    }

    public void delete(Task task, ProcessCallback callback) {
        task.delete(callback);
    }
}