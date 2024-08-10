package com.sbitbd.farmerassist.ui.question;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DashboardViewModel extends ViewModel {

    @Inject
    private GenerativeModel generativeModel;

    public void generateText(String prompt) {
        try {
            Content content = new Content.Builder()
                    .addText(prompt)
                    .build();
            ListenableFuture<GenerateContentResponse> response = generativeModel.generateContent(content);

            Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
                @Override
                public void onSuccess(GenerateContentResponse result) {

                }

                @Override
                public void onFailure(Throwable t) {

                }
            },command -> {

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}