package com.sbitbd.farmerassist.ui.diseases;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sbitbd.farmerassist.DataModel.QuestionModel;
import com.sbitbd.farmerassist.Repository.GeminiRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class diseaseViewModel extends ViewModel {
    private MutableLiveData<String> ans_data = new MutableLiveData<>();
    private final GeminiRepository geminiRepository;
    private MutableLiveData<ArrayList<QuestionModel>> ques_data = new MutableLiveData<>();

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

    protected LiveData<String> GenerateTextbyImage(String prompt, Bitmap bitmap) {
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

    protected LiveData<ArrayList<QuestionModel>> getQuestion(){
        ArrayList<QuestionModel> models = new ArrayList<>();
        models.add(new QuestionModel("1","How to crop rice?","1"));
        models.add(new QuestionModel("2","How to recover diseases to crop rice?","1"));
        models.add(new QuestionModel("3","How to crop Ginger?","2"));

        ques_data.setValue(models);
        return ques_data;
    }
}
