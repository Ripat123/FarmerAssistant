package com.sbitbd.farmerassist.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sbitbd.farmerassist.DataModel.AgroModel;
import com.sbitbd.farmerassist.DataModel.DiseasesModel;
import com.sbitbd.farmerassist.DataModel.QuestionModel;
import com.sbitbd.farmerassist.DataModel.WeatherModel;
import com.sbitbd.farmerassist.Repository.FirestoreRepository;
import com.sbitbd.farmerassist.Repository.WeatherRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    private final WeatherRepository weatherRepository;
    private final FirestoreRepository firestoreRepository;
    private MutableLiveData<WeatherModel> live_data = new MutableLiveData<>();
    private MutableLiveData<ArrayList<DiseasesModel>> diseases_data = new MutableLiveData<>();
    private MutableLiveData<ArrayList<AgroModel>> agro_data = new MutableLiveData<>();
    private MutableLiveData<ArrayList<QuestionModel>> ques_data = new MutableLiveData<>();
    @Inject
    public HomeViewModel(WeatherRepository weatherRepository,FirestoreRepository firestoreRepository) {
        this.weatherRepository = weatherRepository;
        this.firestoreRepository = firestoreRepository;
    }

    protected LiveData<WeatherModel> getData(){
        return live_data;
    }

    protected void weatherData(double lat, double lon){
        weatherRepository.fetchData(lat,lon,new WeatherRepository.DataCallback() {
            @Override
            public void onSuccess(WeatherModel data) {
                Log.d("dddd",data.toString());
                live_data.setValue(data);
            }

            @Override
            public void onError(String error) {
                Log.d("dddd",error);
                live_data.setValue(null);
            }
        });
    }

    protected LiveData<ArrayList<DiseasesModel>> getDiseases(){
        firestoreRepository.fetchLMDiseasesData(10, new FirestoreRepository.DiseasesDataCallback() {
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
    protected LiveData<ArrayList<AgroModel>> getAgro(){
        firestoreRepository.fetchAgroData(new FirestoreRepository.AgroDataCallback() {
            @Override
            public void onSuccess(ArrayList<AgroModel> data) {
                agro_data.setValue(data);
            }

            @Override
            public void onError(String error) {
            }
        });
        return agro_data;
    }

    protected LiveData<ArrayList<QuestionModel>> getQuestion(){
        firestoreRepository.fetchINQuestionData(10, new FirestoreRepository.QuestionDataCallback() {
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