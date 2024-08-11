package com.sbitbd.farmerassist.di;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.sbitbd.farmerassist.BuildConfig;
import com.sbitbd.farmerassist.Repository.GeminiRepository;
import com.sbitbd.farmerassist.Repository.GeminiRepositoryImpl;
import com.sbitbd.farmerassist.utils.Utils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class GeminiModule {
    @Provides
    @Singleton
    public GeminiRepository provideGeminiRepository() {
        GenerativeModel gm = new GenerativeModel(Utils.AI_MODEL, BuildConfig.apiKey);
        GenerativeModelFutures model = GenerativeModelFutures.from(gm);
        return new GeminiRepositoryImpl(model);
    }
}
