package com.sbitbd.farmerassist.Repository;

import com.sbitbd.farmerassist.DataModel.WeatherModel;

import java.util.List;

public interface WeatherRepository {
    interface DataCallback {
        void onSuccess(List<WeatherModel> data);
        void onError(String error);
    }

    void fetchData(DataCallback callback);
}
