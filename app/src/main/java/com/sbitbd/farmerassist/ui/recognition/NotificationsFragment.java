package com.sbitbd.farmerassist.ui.recognition;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.sbitbd.farmerassist.databinding.FragmentNotificationsBinding;
import com.sbitbd.farmerassist.utils.Utils;

import java.io.IOException;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private NotificationsViewModel notificationsViewModel;
    private ActivityResultLauncher<Intent> pickImageLauncher;
    private Bitmap selectedImageBitmap;
    private ActivityResultLauncher<Intent> cameraActivityResultLauncher;
    private ActivityResultLauncher<String> requestPermissionLauncher;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        initView();
    }

    private void initView() {
        try {
            binding.imgBtn.setOnClickListener(v -> imageChooser(1));
            binding.camBtn.setOnClickListener(v -> imageChooser(2));
            binding.quesBtn.setOnClickListener(v -> {
                if (!binding.questId.getText().toString().isEmpty()) {
                    binding.quesBtn.setEnabled(false);
                    binding.ansT.setText("");
                    binding.loader.setVisibility(View.VISIBLE);
                    if (selectedImageBitmap != null)
                        notificationsViewModel.GenerateTextbyImage(binding.questId.getText().toString()
                                , selectedImageBitmap).observe(getViewLifecycleOwner(), this::setView);
                    else
                        notificationsViewModel.GenerateText(binding.questId.getText().toString()).observe(getViewLifecycleOwner(), this::setView);
                } else binding.questId.setError("Write something.");
            });
            imageLauncher();
            cameraLauncher();
            permissionLauncher();
        } catch (Exception e) {
        }
    }

    private void imageChooser(int status) {
        Intent i;
        if (status == 1) {
            i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            pickImageLauncher.launch(i);
        }
        else {
            i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                requestPermissionLauncher.launch(Manifest.permission.CAMERA);
            else {
                cameraActivityResultLauncher.launch(i);
            }

        }
    }

    private void imageLauncher() {
        pickImageLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri selectedImageUri = result.getData().getData();
                        try {
                            selectedImageBitmap
                                    = MediaStore.Images.Media.getBitmap(
                                    getContext().getContentResolver(),
                                    selectedImageUri);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // Load the image into the ImageView using Glide
                        Glide.with(this)
                                .load(selectedImageUri)
                                .into(binding.srcImg);
                    }
                });
    }

    private void cameraLauncher() {
        cameraActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode()
                            == RESULT_OK && result.getData() != null) {
                        Bundle extras = result.getData().getExtras();
                        selectedImageBitmap = null;
                        selectedImageBitmap = (Bitmap) extras.get(Utils.DATA);

                        binding.srcImg.setImageBitmap(selectedImageBitmap);
                    }
                });
    }

    private void permissionLauncher(){
        requestPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted){
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        cameraActivityResultLauncher.launch(intent);
                    }
                     else
                        Snackbar.make(binding.getRoot(),"Permission required.",Snackbar.LENGTH_SHORT).show();
                }
        );
    }

    private void setView(String s) {
        binding.ansT.setText(s);
        binding.loader.setVisibility(View.GONE);
        binding.questId.setText("");
        binding.quesBtn.setEnabled(true);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}