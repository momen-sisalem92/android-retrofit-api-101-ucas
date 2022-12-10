package com.example.android_retrofit_api_101_ucas.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.android_retrofit_api_101_ucas.AppController;
import com.example.android_retrofit_api_101_ucas.models.Student;

enum PrefKeys {
    id, loggedIn, fullName, email, token
}

public class AppSharedPreferences {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private AppSharedPreferences() {
        sharedPreferences = AppController.getInstance().getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
    }

    private static AppSharedPreferences instance;

    public static AppSharedPreferences getInstance() {
        if (instance == null) {
            instance = new AppSharedPreferences();
        }

        return instance;
    }

    public void save(Student student) {
        editor = sharedPreferences.edit();
        editor.putBoolean(PrefKeys.loggedIn.name(), true);
        editor.putInt(PrefKeys.id.name(), student.id);
        editor.putString(PrefKeys.fullName.name(), student.fullName);
        editor.putString(PrefKeys.email.name(), student.email);
        editor.putString(PrefKeys.token.name(), "Bearer " + student.token);
        editor.apply();
    }

    public String getToken() {
        return sharedPreferences.getString(PrefKeys.token.name(), "");
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(PrefKeys.loggedIn.name(), false);
    }

    public void clear() {
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
