package com.sbitbd.farmerassist.ui.question;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sbitbd.farmerassist.databinding.FragmentDashboardBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        initView();
    }

    private void initView() {
        binding.quesBtn.setOnClickListener(v -> {
            if (!binding.questId.getText().toString().isEmpty()){
                binding.quesBtn.setEnabled(false);
                binding.ansT.setText("");
                binding.loader.setVisibility(View.VISIBLE);
                dashboardViewModel.GenerateText(binding.questId.getText().toString()).observe(getViewLifecycleOwner(),s -> {
                    binding.ansT.setText(s);
                    binding.loader.setVisibility(View.GONE);
                    binding.questId.setText("");
                    binding.quesBtn.setEnabled(true);
                });
            }

            else binding.questId.setError("Write something.");
        });
        binding.questionCard.setOnClickListener(v -> binding.questId.setText(binding.questionT.getText().toString()));
        binding.questionCard1.setOnClickListener(v -> binding.questId.setText(binding.questionT1.getText().toString()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}