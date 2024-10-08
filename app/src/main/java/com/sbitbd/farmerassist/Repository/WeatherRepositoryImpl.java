package com.sbitbd.farmerassist.Repository;


import com.sbitbd.farmerassist.BuildConfig;
import com.sbitbd.farmerassist.DataModel.WeatherModel;
import com.sbitbd.farmerassist.network.Api;
import com.sbitbd.farmerassist.utils.Utils;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepositoryImpl implements WeatherRepository {
    private final Api api;

    @Inject
    public WeatherRepositoryImpl(Api api) {
        this.api = api;
    }

    @Override
    public void fetchData(double lat,double lon,DataCallback callback) {
        api.getForecastWithLocation(lat,lon, BuildConfig.wapiKey,Utils.METRIC).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable throwable) {
                callback.onError(throwable.getMessage());
            }
        });
    }
}
