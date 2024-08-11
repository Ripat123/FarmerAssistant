package com.sbitbd.farmerassist.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sbitbd.farmerassist.DataModel.AgroModel;
import com.sbitbd.farmerassist.DataModel.DiseasesModel;
import com.sbitbd.farmerassist.DataModel.QuestionModel;
import com.sbitbd.farmerassist.DataModel.WeatherModel;
import com.sbitbd.farmerassist.Repository.WeatherRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    private final WeatherRepository weatherRepository;
    private MutableLiveData<WeatherModel> live_data = new MutableLiveData<>();
    private MutableLiveData<ArrayList<DiseasesModel>> diseases_data = new MutableLiveData<>();
    private MutableLiveData<ArrayList<AgroModel>> agro_data = new MutableLiveData<>();
    private MutableLiveData<ArrayList<QuestionModel>> ques_data = new MutableLiveData<>();
    @Inject
    public HomeViewModel(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    protected LiveData<WeatherModel> getData(){
        return live_data;
    }

    protected void weatherData(){
        weatherRepository.fetchData(new WeatherRepository.DataCallback() {
            @Override
            public void onSuccess(WeatherModel data) {
                Log.d("dddd",data.toString());
                live_data.setValue(data);
            }

            @Override
            public void onError(String error) {
                Log.d("dddd",error);
                live_data.setValue(null);
            }
        });
    }

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
    protected LiveData<ArrayList<AgroModel>> getAgro(){
        ArrayList<AgroModel> models = new ArrayList<>();
        models.add(new AgroModel("1","https://freepngimg.com/download/rice/35823-3-rice-picture.png","Rice"));
        models.add(new AgroModel("2","https://png.pngtree.com/png-vector/20220611/ourmid/pngtree-wheat-rice-icon-barley-vector-png-image_4991947.png",""));
        models.add(new AgroModel("3","https://png.pngtree.com/png-vector/20220611/ourmid/pngtree-wheat-rice-icon-barley-vector-png-image_4991947.png",""));
        models.add(new AgroModel("4","https://png.pngtree.com/png-vector/20220611/ourmid/pngtree-wheat-rice-icon-barley-vector-png-image_4991947.png",""));
        models.add(new AgroModel("5","https://png.pngtree.com/png-vector/20220611/ourmid/pngtree-wheat-rice-icon-barley-vector-png-image_4991947.png",""));
        agro_data.setValue(models);
        return agro_data;
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