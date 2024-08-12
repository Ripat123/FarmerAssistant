package com.sbitbd.farmerassist.Repository;

import com.sbitbd.farmerassist.DataModel.WeatherModel;

import java.util.List;

public interface WeatherRepository {
    interface DataCallback {
        void onSuccess(WeatherModel data);
        void onError(String error);
    }

    void fetchData(double lat, double lon,DataCallback callback);
}
