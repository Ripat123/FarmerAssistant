package com.sbitbd.farmerassist.ui.question;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.sbitbd.farmerassist.BuildConfig;
import com.sbitbd.farmerassist.DataModel.WeatherModel;
import com.sbitbd.farmerassist.Repository.GeminiRepository;
import com.sbitbd.farmerassist.Repository.WeatherRepository;
import com.sbitbd.farmerassist.utils.Utils;

import java.util.concurrent.Executors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DashboardViewModel extends ViewModel {
    private final WeatherRepository weatherRepository;
    private MutableLiveData<WeatherModel> live_data = new MutableLiveData<>();
    private MutableLiveData<String> ans_data = new MutableLiveData<>();
    private final GeminiRepository geminiRepository;
    @Inject
    public DashboardViewModel(GeminiRepository geminiRepository,WeatherRepository weatherRepository) {
        this.geminiRepository = geminiRepository;
        this.weatherRepository = weatherRepository;
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
}