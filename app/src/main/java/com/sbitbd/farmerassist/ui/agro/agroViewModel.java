package com.sbitbd.farmerassist.ui.agro;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sbitbd.farmerassist.DataModel.DiseasesModel;
import com.sbitbd.farmerassist.DataModel.QuestionModel;

import java.util.ArrayList;

import dagger.hilt.android.lifecycle.HiltViewModel;


public class agroViewModel extends ViewModel {
    private MutableLiveData<ArrayList<QuestionModel>> ques_data = new MutableLiveData<>();
    private MutableLiveData<ArrayList<DiseasesModel>> diseases_data = new MutableLiveData<>();

    protected LiveData<ArrayList<DiseasesModel>> getDiseases(){
        ArrayList<DiseasesModel> models = new ArrayList<>();
        models.add(new DiseasesModel("1","https://www.irri.org/sites/default/files/files/diseases%20and%20pests/disease-and-pest-bacterial-blight2.jpg","",""));
        models.add(new DiseasesModel("2","https://www.irri.org/sites/default/files/files/diseases%20and%20pests/disease-and-pest-rice-blast.jpg","",""));
        models.add(new DiseasesModel("3","https://us-central1-plantix-8e0ce.cloudfunctions.net/v1/image/w400/6f2d41b6-f518-4e63-b863-87209602a207","",""));
        models.add(new DiseasesModel("4","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ0npoPQLiWW61lvN0cVYadBypWVbj_geJ6IdNVSj_U4O_PF814CrlQWSWEgp5l9MvZxqE&usqp=CAU","",""));
        models.add(new DiseasesModel("5","https://plantlet.org/wp-content/uploads/2023/02/fungal-smut-crop-disese.jpg","",""));
        diseases_data.setValue(models);
        return diseases_data;
    }

    protected LiveData<ArrayList<QuestionModel>> getQuestion(){
        ArrayList<QuestionModel> models = new ArrayList<>();
        models.add(new QuestionModel("1","How to crop rice?","1"));
        models.add(new QuestionModel("2","How to recover diseases to crop rice?","1"));
        models.add(new QuestionModel("3","How to crop Ginger?","2"));

        ques_data.setValue(models);
        return ques_data;
    }
}
