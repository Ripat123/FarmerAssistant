package com.sbitbd.farmerassist.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sbitbd.farmerassist.DataModel.WeatherModel;
import com.sbitbd.farmerassist.Repository.WeatherRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    private final WeatherRepository weatherRepository;
    private MutableLiveData<WeatherModel> live_data = new MutableLiveData<>();
    @Inject
    public HomeViewModel(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    protected LiveData<WeatherModel> getData(){
        return live_data;
    }

    protected void weatherData(){
        weatherRepository.fetchData(new WeatherRepository.DataCallback() {
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