package com.sbitbd.farmerassist.di;

import com.sbitbd.farmerassist.network.Api;
import com.sbitbd.farmerassist.utils.Utils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class WeatherModule {

    @Provides
    @Singleton
    public Api ApiService(){
        return new Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api.class);
    }
}
