package com.example.android_retrofit_api_101_ucas.views;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.example.android_retrofit_api_101_ucas.R;
import com.example.android_retrofit_api_101_ucas.api.controllers.ImagesApiController;
import com.example.android_retrofit_api_101_ucas.databinding.ActivityUploadImageBinding;
import com.example.android_retrofit_api_101_ucas.interfaces.ProcessCallback;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class UploadImageActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityUploadImageBinding binding;

    private ActivityResultLauncher<String> permissionResultLauncher;
    private ActivityResultLauncher<Void> cameraResultLauncher;
    private Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUploadImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeView();
    }

    private void initializeView() {
        setupResultsLauncher();
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        binding.pickImageButton.setOnClickListener(this::onClick);
    }

    private void pickImage() {
        permissionResultLauncher.launch(Manifest.permission.CAMERA);
    }

    private void setupResultsLauncher() {
        permissionResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result) {
                    cameraResultLauncher.launch(null);
                }
            }
        });

        cameraResultLauncher = registerForActivityResult(new ActivityResultContracts.TakePicturePreview(), new ActivityResultCallback<Bitmap>() {
            @Override
            public void onActivityResult(Bitmap result) {
                if (result != null) {
                    imageBitmap = result;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.pick_image_button) {
            pickImage();
        }
    }

    private void performImageUpload() {
        if (imageBitmap != null) {
            uploadImage();
        }
    }

    private void uploadImage() {
        ImagesApiController controller = new ImagesApiController();
        controller.uploadImage(bitmapToBytes(), new ProcessCallback() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    private byte[] bitmapToBytes() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}