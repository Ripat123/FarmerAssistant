package com.sbitbd.farmerassist.di;

import com.google.ai.client.generativeai.GenerativeModel;
import com.sbitbd.farmerassist.BuildConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class GenerativeModelModule {

    @Provides
    @Singleton
    public GenerativeModel provideGenerativeModel(String apiKey) {
        return new GenerativeModel("gemini-1.5-flash", apiKey);
    }

    @Provides
    @Singleton
    public String provideApiKey() {
        return BuildConfig.apiKey;
    }
}
