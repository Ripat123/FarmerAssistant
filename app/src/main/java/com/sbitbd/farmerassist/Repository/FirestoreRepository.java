package com.sbitbd.farmerassist.Repository;

import com.sbitbd.farmerassist.DataModel.AgroModel;
import com.sbitbd.farmerassist.DataModel.DiseasesModel;
import com.sbitbd.farmerassist.DataModel.QuestionModel;

import java.util.ArrayList;
import java.util.List;

public interface FirestoreRepository {
    interface AgroDataCallback {
        void onSuccess(ArrayList<AgroModel> data);
        void onError(String error);
    }
    interface DiseasesDataCallback {
        void onSuccess(ArrayList<DiseasesModel> data);
        void onError(String error);
    }
    interface QuestionDataCallback {
        void onSuccess(ArrayList<QuestionModel> data);
        void onError(String error);
    }
    void fetchDiseasesData(String id, FirestoreRepository.DiseasesDataCallback callback);
    void fetchLMDiseasesData(long limit, FirestoreRepository.DiseasesDataCallback callback);
    void fetchAgroData(FirestoreRepository.AgroDataCallback callback);
    void fetchLMINQuestionData(String id,long limit, FirestoreRepository.QuestionDataCallback callback);
    void fetchINQuestionData(long limit, FirestoreRepository.QuestionDataCallback callback);
}
