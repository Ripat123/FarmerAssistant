package com.sbitbd.farmerassist.Repository;

import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Inject;

public class FirestoreRepositoryImpl implements FirestoreRepository{

    private final FirebaseFirestore firestore;

    @Inject
    public FirestoreRepositoryImpl(FirebaseFirestore firestore) {
        this.firestore = firestore;
    }

    @Override
    public void fetchDiseasesData(String id, DataCallback callback) {

    }

    @Override
    public void fetchLMDiseasesData(String limit, DataCallback callback) {

    }

    @Override
    public void fetchAgroData(DataCallback callback) {

    }

    @Override
    public void fetchLMINQuestionData(String id, String limit, DataCallback callback) {

    }

    @Override
    public void fetchINQuestionData(String limit, DataCallback callback) {

    }
}
