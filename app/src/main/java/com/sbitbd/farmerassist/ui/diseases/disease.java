package com.sbitbd.farmerassist.ui.diseases;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.sbitbd.farmerassist.Adapter.QuestionAdapter;
import com.sbitbd.farmerassist.DataModel.DiseasesModel;
import com.sbitbd.farmerassist.DataModel.QuestionModel;
import com.sbitbd.farmerassist.R;
import com.sbitbd.farmerassist.Repository.OnItemClickListener;
import com.sbitbd.farmerassist.databinding.ActivityDiseaseBinding;
import com.sbitbd.farmerassist.utils.Utils;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class disease extends AppCompatActivity implements OnItemClickListener {
    private ActivityDiseaseBinding binding;
    private diseaseViewModel viewModel;
    private QuestionAdapter questionAdapter;
    private Bitmap bitmap;
    private ArrayList<QuestionModel> questionModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityDiseaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
    }

    private void initView() {
        try {
            viewModel = new ViewModelProvider(this).get(diseaseViewModel.class);
            DiseasesModel model = (DiseasesModel) getIntent().getSerializableExtra(Utils.DATA);
            QuestionModel qust = (QuestionModel) getIntent().getSerializableExtra(Utils.QUESTION);

            binding.quesBtn.setOnClickListener(v -> {
                if (!binding.questId.getText().toString().isEmpty()) {
                    binding.quesBtn.setEnabled(false);
                    binding.ansT.setText("");
                    binding.loader.setVisibility(View.VISIBLE);
                    if (model != null && bitmap != null)
                        viewModel.GenerateTextbyImage(binding.questId.getText().toString(), bitmap).observe(this, this::setView);
                    else
                        viewModel.GenerateText(binding.questId.getText().toString()).observe(this, this::setView);
                } else binding.questId.setError("Write something.");
            });

            binding.backBtn.setOnClickListener(v -> {
                getOnBackPressedDispatcher().onBackPressed();
                finish();
            });
            if (qust != null) {
                binding.questId.setText(qust.getTitle());
                RenderQuestion(qust.getAgro_id());
            }
            else if (model != null) {
                RenderQuestion(model.getAgro_id());
                Glide.with(this)
                        .asBitmap()
                        .load(model.getUrl())
                        .centerCrop()
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                bitmap = resource;
                                binding.srcImg.setImageBitmap(resource);
                            }

                        });
            }
        } catch (Exception e) {
        }
    }

    private void setView(String s) {
        binding.ansT.setText(s);
        binding.loader.setVisibility(View.GONE);
        binding.questId.setText("");
        binding.quesBtn.setEnabled(true);
    }

    private void RenderQuestion(String id){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.questRec.setLayoutManager(linearLayoutManager);
        viewModel.getQuestion(id).observe(this, questionModels -> {
            this.questionModels = questionModels;
            questionAdapter = new QuestionAdapter(2,this, questionModels);
            questionAdapter.setOnItemClickListener(this);
            binding.questRec.setAdapter(questionAdapter);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void onItemClick(int position) {
        binding.questId.setText(questionModels.get(position).getTitle());
    }
}