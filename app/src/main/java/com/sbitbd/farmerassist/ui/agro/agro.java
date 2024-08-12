package com.sbitbd.farmerassist.ui.agro;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.carousel.CarouselLayoutManager;
import com.google.android.material.carousel.MultiBrowseCarouselStrategy;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.sbitbd.farmerassist.Adapter.DiseasesAdapter;
import com.sbitbd.farmerassist.Adapter.QuestionAdapter;
import com.sbitbd.farmerassist.DataModel.AgroModel;
import com.sbitbd.farmerassist.R;
import com.sbitbd.farmerassist.databinding.ActivityAgroBinding;
import com.sbitbd.farmerassist.utils.Utils;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class agro extends AppCompatActivity {

    private ActivityAgroBinding binding;
    private agroViewModel viewModel;
    private DiseasesAdapter adapter;
    private QuestionAdapter questionAdapter;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAgroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initiView();
    }

    private void initiView(){
        try {
            viewModel = new ViewModelProvider(this).get(agroViewModel.class);
            AgroModel model = (AgroModel) getIntent().getSerializableExtra(Utils.DATA);
            if (model != null)
                binding.textHome.setText(model.getName());

            CarouselLayoutManager carouselLayoutManager = new CarouselLayoutManager();
            MultiBrowseCarouselStrategy multiBrowseCarouselStrategy = new MultiBrowseCarouselStrategy();
            multiBrowseCarouselStrategy.setSmallItemSizeMax(180);
            carouselLayoutManager.setCarouselStrategy(multiBrowseCarouselStrategy);
            binding.carouselRec.setLayoutManager(carouselLayoutManager);
            viewModel.getDiseases(model.getId()).observe(this,diseasesModels -> {
                adapter = new DiseasesAdapter(this,diseasesModels);
                binding.carouselRec.setAdapter(adapter);
            });
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
            builder.setView(R.layout.progress_dialog);
            builder.setCancelable(false);
            dialog = builder.create();
            dialog.show();

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            binding.quesRec.setLayoutManager(linearLayoutManager);
            viewModel.getQuestion(model.getId()).observe(this,questionModels -> {
                questionAdapter= new QuestionAdapter(1,this,questionModels);
                binding.quesRec.setAdapter(questionAdapter);
                if (dialog.isShowing())dialog.cancel();
            });

            binding.backBtn.setOnClickListener(v -> {
                getOnBackPressedDispatcher().onBackPressed();
                finish();
            });
        }catch (Exception e){
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}