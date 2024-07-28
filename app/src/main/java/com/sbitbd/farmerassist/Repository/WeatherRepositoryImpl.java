package com.sbitbd.farmerassist.Repository;

import com.sbitbd.farmerassist.network.Api;

import javax.inject.Inject;

public class WeatherRepositoryImpl implements WeatherRepository {
    private final Api api;

    @Inject
    public WeatherRepositoryImpl(Api api) {
        this.api = api;
    }

    @Override
    public void fetchData(DataCallback callback) {

    }
}
