package com.sbitbd.farmerassist.ui.recognition;

import static android.app.Activity.RESULT_OK;
import static androidx.activity.result.ActivityResultCallerKt.registerForActivityResult;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.sbitbd.farmerassist.Repository.GeminiRepository;

import java.io.IOException;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class NotificationsViewModel extends ViewModel {
    private MutableLiveData<String> ans_data = new MutableLiveData<>();
    private final GeminiRepository geminiRepository;
    @Inject
    public NotificationsViewModel(GeminiRepository geminiRepository) {
        this.geminiRepository = geminiRepository;
    }

    protected LiveData<String> GenerateTextbyImage(String prompt,Bitmap bitmap) {
        geminiRepository.generateTextbyImage(prompt, bitmap, new GeminiRepository.DataCallback() {
            @Override
            public void onSuccess(String data) {
                ans_data.postValue(data);
            }

            @Override
            public void onError(String error) {
                ans_data.postValue(error);
            }
        });
        return ans_data;
    }
}