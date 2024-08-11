package com.sbitbd.farmerassist.Repository;

import android.graphics.Bitmap;

import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.Executors;

import javax.inject.Inject;

public class GeminiRepositoryImpl implements GeminiRepository{

    private final GenerativeModelFutures modelFutures;

    @Inject
    public GeminiRepositoryImpl(GenerativeModelFutures modelFutures) {
        this.modelFutures = modelFutures;
    }

    private void Generator(Content content, DataCallback callback){
        try {
            ListenableFuture<GenerateContentResponse> response = modelFutures.generateContent(content);
            Futures.addCallback(response, new FutureCallback<>() {
                @Override
                public void onSuccess(GenerateContentResponse result) {
                    callback.onSuccess(result.getText());
                }

                @Override
                public void onFailure(Throwable t) {
                    callback.onError(t.getMessage());
                }
            }, Executors.newSingleThreadExecutor());
        } catch (Exception e) {
            callback.onError(e.getMessage());
        }
    }

    @Override
    public void generateText(String prompt, DataCallback callback) {
            Content content = new Content.Builder()
                    .addText(prompt)
                    .build();
            Generator(content,callback);
    }

    @Override
    public void generateTextbyImage(String prompt, Bitmap bitmap, DataCallback callback) {
        Content content = new Content.Builder()
                .addImage(bitmap)
                .addText(prompt)
                .build();
        Generator(content,callback);
    }
}
