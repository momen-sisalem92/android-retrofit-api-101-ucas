package com.example.android_retrofit_api_101_ucas.views;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_retrofit_api_101_ucas.R;
import com.example.android_retrofit_api_101_ucas.api.controllers.AuthApiController;
import com.example.android_retrofit_api_101_ucas.databinding.ActivityRegisterBinding;
import com.example.android_retrofit_api_101_ucas.interfaces.ProcessCallback;
import com.example.android_retrofit_api_101_ucas.models.Student;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityRegisterBinding binding;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeView();
    }

    private void initializeView() {
        setOnClickListeners();
        controlGenderSelection();
    }

    private void setOnClickListeners() {
        binding.registerButton.setOnClickListener(this);
    }

    private void controlGenderSelection() {
        binding.genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                gender = checkedId == R.id.male_radio_button ? "M" : "F";
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.register_button) {
            performRegister();
        }
    }

    private void performRegister() {
        if (checkData()) {
            register();
        }
    }

    private boolean checkData() {
        if (!binding.fullNameEditText.getText().toString().isEmpty() &&
                !binding.emailEditText.getText().toString().isEmpty() &&
                !binding.passwordEditText.getText().toString().isEmpty() &&
                gender != null) {
            return true;
        }
        return false;
    }

    private void register() {

    }

    private Student getStudent() {
        Student student = new Student();
        student.fullName = binding.fullNameEditText.getText().toString();
        student.email = binding.emailEditText.getText().toString();
        student.password = binding.passwordEditText.getText().toString();
        student.gender = gender;
        return student;
    }
}