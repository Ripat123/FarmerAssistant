package com.sbitbd.farmerassist.Repository;

public interface FirestoreRepository {
    interface DataCallback {
        void onSuccess(String data);
        void onError(String error);
    }
    void fetchDiseasesData(String id, FirestoreRepository.DataCallback callback);
    void fetchLMDiseasesData(String limit, FirestoreRepository.DataCallback callback);
    void fetchAgroData(FirestoreRepository.DataCallback callback);
    void fetchLMINQuestionData(String id,String limit, FirestoreRepository.DataCallback callback);
    void fetchINQuestionData(String limit, FirestoreRepository.DataCallback callback);
}
