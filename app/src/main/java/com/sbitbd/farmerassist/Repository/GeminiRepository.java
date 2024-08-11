package com.sbitbd.farmerassist.Repository;


import android.graphics.Bitmap;

public interface GeminiRepository {
    interface DataCallback {
        void onSuccess(String data);
        void onError(String error);
    }
    void generateText(String prompt,DataCallback callback);
    void generateTextbyImage(String prompt, Bitmap bitmap, DataCallback callback);
}
