package com.sbitbd.farmerassist.ui.diseases;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.sbitbd.farmerassist.DataModel.DiseasesModel;
import com.sbitbd.farmerassist.R;
import com.sbitbd.farmerassist.databinding.ActivityDiseaseBinding;
import com.sbitbd.farmerassist.utils.Utils;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class disease extends AppCompatActivity {
    private ActivityDiseaseBinding binding;
    private diseaseViewModel viewModel;

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
            DiseasesModel model = (DiseasesModel) getIntent().getSerializableExtra("data");
            String qust = getIntent().getStringExtra(Utils.QUESTION);
            if (qust != null)
                binding.questId.setText(qust);
            binding.quesBtn.setOnClickListener(v -> {
                if (!binding.questId.getText().toString().isEmpty()) {
                    binding.quesBtn.setEnabled(false);
                    binding.ansT.setText("");
                    binding.loader.setVisibility(View.VISIBLE);
                    viewModel.GenerateText(binding.questId.getText().toString()).observe(this, s -> {
                        binding.ansT.setText(s);
                        binding.loader.setVisibility(View.GONE);
                        binding.questId.setText("");
                        binding.quesBtn.setEnabled(true);
                    });
                }
                else binding.questId.setError("Write something.");
            });

        } catch (Exception e) {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}