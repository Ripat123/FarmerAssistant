package com.sbitbd.farmerassist.Repository;

import android.util.Log;

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
    public void fetchData(DataCallback callback) {
        api.getForecastWithLocation(Utils.METRIC,23.8158996,90.2505284,"minutely,hourly,daily,alerts", BuildConfig.wapiKey).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    Log.d("dddd",response.message());
                    callback.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable throwable) {
                callback.onError(throwable.getMessage());
                Log.d("dddd",throwable.getMessage());
            }
        });
    }
}
