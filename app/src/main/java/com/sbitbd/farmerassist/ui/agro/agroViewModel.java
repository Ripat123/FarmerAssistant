package com.sbitbd.farmerassist.ui.agro;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sbitbd.farmerassist.DataModel.DiseasesModel;
import com.sbitbd.farmerassist.DataModel.QuestionModel;
import com.sbitbd.farmerassist.Repository.FirestoreRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class agroViewModel extends ViewModel {
    private final FirestoreRepository firestoreRepository;
    private MutableLiveData<ArrayList<QuestionModel>> ques_data = new MutableLiveData<>();
    private MutableLiveData<ArrayList<DiseasesModel>> diseases_data = new MutableLiveData<>();

    @Inject
    public agroViewModel(FirestoreRepository firestoreRepository) {
        this.firestoreRepository = firestoreRepository;
    }

    protected LiveData<ArrayList<DiseasesModel>> getDiseases(String id){
        firestoreRepository.fetchDiseasesData(id, new FirestoreRepository.DiseasesDataCallback() {
            @Override
            public void onSuccess(ArrayList<DiseasesModel> data) {
                diseases_data.setValue(data);
            }

            @Override
            public void onError(String error) {
            }
        });
        return diseases_data;
    }

    protected LiveData<ArrayList<QuestionModel>> getQuestion(String id){
        firestoreRepository.fetchLMINQuestionData(id, 10, new FirestoreRepository.QuestionDataCallback() {
            @Override
            public void onSuccess(ArrayList<QuestionModel> data) {
                ques_data.setValue(data);
            }

            @Override
            public void onError(String error) {
            }
        });
        return ques_data;
    }
}
