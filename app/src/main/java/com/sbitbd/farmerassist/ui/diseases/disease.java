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
import com.sbitbd.farmerassist.R;
import com.sbitbd.farmerassist.databinding.ActivityDiseaseBinding;
import com.sbitbd.farmerassist.utils.Utils;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class disease extends AppCompatActivity {
    private ActivityDiseaseBinding binding;
    private diseaseViewModel viewModel;
    private QuestionAdapter questionAdapter;
    private Bitmap bitmap;

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
            String qust = getIntent().getStringExtra(Utils.QUESTION);

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

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            binding.questRec.setLayoutManager(linearLayoutManager);
            viewModel.getQuestion().observe(this, questionModels -> {
                questionAdapter = new QuestionAdapter(this, questionModels);
                binding.questRec.setAdapter(questionAdapter);
            });

            binding.backBtn.setOnClickListener(v -> {
                getOnBackPressedDispatcher().onBackPressed();
                finish();
            });
            if (qust != null)
                binding.questId.setText(qust);
            else if (model != null) {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}