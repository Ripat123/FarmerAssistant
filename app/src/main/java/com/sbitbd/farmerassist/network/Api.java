package com.sbitbd.farmerassist.network;

import com.sbitbd.farmerassist.DataModel.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("/data/2.5/weather")
    Call<WeatherModel> getForecast(@Query("q") String city, @Query("appid") String appId, @Query("units") String units);

    @GET("/data/2.5/weather")
    Call<WeatherModel> getForecastWithLocation(@Query("units") String units, @Query("lat") double lat,
                                               @Query("lon") double lon,@Query("exclude") String exclude,@Query("appid") String apiKey);
}
