package com.sbitbd.farmerassist.ui.diseases;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sbitbd.farmerassist.Repository.GeminiRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class diseaseViewModel extends ViewModel {
    private MutableLiveData<String> ans_data = new MutableLiveData<>();
    private final GeminiRepository geminiRepository;

    @Inject
    public diseaseViewModel(GeminiRepository geminiRepository) {
        this.geminiRepository = geminiRepository;
    }

    protected LiveData<String> GenerateText(String prompt) {
        geminiRepository.generateText(prompt, new GeminiRepository.DataCallback() {
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
