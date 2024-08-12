package com.sbitbd.farmerassist.Repository;

import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.sbitbd.farmerassist.DataModel.AgroModel;
import com.sbitbd.farmerassist.DataModel.DiseasesModel;
import com.sbitbd.farmerassist.DataModel.QuestionModel;
import com.sbitbd.farmerassist.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FirestoreRepositoryImpl implements FirestoreRepository {

    private final FirebaseFirestore firestore;

    @Inject
    public FirestoreRepositoryImpl(FirebaseFirestore firestore) {
        this.firestore = firestore;
    }

    @Override
    public void fetchDiseasesData(String id, DiseasesDataCallback callback) {
        firestore.collection(Utils.DISEASES_MODEL).whereEqualTo(Utils.AGRO_ID, id)
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
                        try {
                            ArrayList<DiseasesModel> agroModels = new ArrayList<>();
                            for (QueryDocumentSnapshot snapshot : task.getResult()) {
                                DiseasesModel model = snapshot.toObject(DiseasesModel.class);
                                agroModels.add(model);
                            }
                            callback.onSuccess(agroModels);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void fetchLMDiseasesData(long limit, DiseasesDataCallback callback) {
        firestore.collection(Utils.DISEASES_MODEL).limit(limit).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
                        try {
                            ArrayList<DiseasesModel> agroModels = new ArrayList<>();
                            for (QueryDocumentSnapshot snapshot : task.getResult()) {
                                DiseasesModel model = snapshot.toObject(DiseasesModel.class);
                                agroModels.add(model);
                            }
                            callback.onSuccess(agroModels);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void fetchAgroData(AgroDataCallback callback) {
        firestore.collection(Utils.AGRO_MODEL).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
                        try {
                            ArrayList<AgroModel> agroModels = new ArrayList<>();
                            for (QueryDocumentSnapshot snapshot : task.getResult()) {
                                AgroModel model = snapshot.toObject(AgroModel.class);
                                agroModels.add(model);
                            }
                            callback.onSuccess(agroModels);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void fetchLMINQuestionData(String id, long limit, QuestionDataCallback callback) {
        firestore.collection(Utils.QUESTION_MODEL).whereEqualTo(Utils.AGRO_ID, id)
                .limit(limit).get().addOnCompleteListener(task -> {
                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
                        try {
                            ArrayList<QuestionModel> agroModels = new ArrayList<>();
                            for (QueryDocumentSnapshot snapshot : task.getResult()) {
                                QuestionModel model = snapshot.toObject(QuestionModel.class);
                                agroModels.add(model);
                            }
                            callback.onSuccess(agroModels);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void fetchINQuestionData(long limit, QuestionDataCallback callback) {
        firestore.collection(Utils.QUESTION_MODEL).limit(limit).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
                        try {
                            ArrayList<QuestionModel> agroModels = new ArrayList<>();
                            for (QueryDocumentSnapshot snapshot : task.getResult()) {
                                QuestionModel model = snapshot.toObject(QuestionModel.class);
                                agroModels.add(model);
                            }
                            callback.onSuccess(agroModels);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
