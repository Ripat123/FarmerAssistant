package com.sbitbd.farmerassist.ui.question;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.sbitbd.farmerassist.BuildConfig;
import com.sbitbd.farmerassist.utils.Utils;

import java.util.concurrent.Executors;



public class DashboardViewModel extends ViewModel {
    private MutableLiveData<String> ans_data = new MutableLiveData<>();


    public LiveData<String> generateText(String prompt) {
        try {
            GenerativeModel gm = new GenerativeModel(Utils.AI_MODEL, BuildConfig.apiKey);
            GenerativeModelFutures model = GenerativeModelFutures.from(gm);

            Content content = new Content.Builder()
                    .addText(prompt)
                    .build();
            ListenableFuture<GenerateContentResponse> response = model.generateContent(content);

            Futures.addCallback(response, new FutureCallback<>() {
                @Override
                public void onSuccess(GenerateContentResponse result) {
                    ans_data.postValue(result.getText());
                }

                @Override
                public void onFailure(Throwable t) {
                    ans_data.postValue(t.getMessage());
                }
            }, Executors.newSingleThreadExecutor());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans_data;
    }
}