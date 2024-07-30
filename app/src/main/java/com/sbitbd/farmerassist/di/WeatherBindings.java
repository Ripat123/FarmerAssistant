package com.sbitbd.farmerassist.di;

import com.sbitbd.farmerassist.Repository.WeatherRepository;
import com.sbitbd.farmerassist.Repository.WeatherRepositoryImpl;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class WeatherBindings {
    @Binds
    abstract WeatherRepository bindWeatherRepository(WeatherRepositoryImpl repositoryImpl);
}
