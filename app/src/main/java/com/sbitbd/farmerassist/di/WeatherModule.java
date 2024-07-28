package com.sbitbd.farmerassist.di;

import com.sbitbd.farmerassist.network.Api;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;

@Module
@InstallIn(SingletonComponent.class)
public class WeatherModule {

    @Provides
    @Singleton
    public Api ApiService(){
        return new Retrofit.Builder()
                .baseUrl("")
                .build()
                .create(Api.class);
    }
}
