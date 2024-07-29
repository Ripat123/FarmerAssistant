package com.sbitbd.farmerassist.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sbitbd.farmerassist.DataModel.WeatherModel;
import com.sbitbd.farmerassist.Repository.WeatherRepository;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {

    private final WeatherRepository weatherRepository;
    private MutableLiveData<WeatherModel> data = new MutableLiveData<>();
    @Inject
    public HomeViewModel(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

}