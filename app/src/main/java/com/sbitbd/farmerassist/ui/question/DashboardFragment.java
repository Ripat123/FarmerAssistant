package com.sbitbd.farmerassist.ui.question;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.sbitbd.farmerassist.R;
import com.sbitbd.farmerassist.databinding.FragmentDashboardBinding;
import com.sbitbd.farmerassist.utils.Utils;

import java.text.MessageFormat;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private DashboardViewModel dashboardViewModel;
    private ActivityResultLauncher<String> requestPermissionLauncher;
    private double latitude;
    private double longitude;
    private AlertDialog dialog;

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
            if (!binding.questId.getText().toString().isEmpty()) {
                binding.quesBtn.setEnabled(false);
                binding.ansT.setText("");
                binding.loader.setVisibility(View.VISIBLE);
                dashboardViewModel.GenerateText(binding.questId.getText().toString()).observe(getViewLifecycleOwner(), s -> {
                    binding.ansT.setText(s);
                    binding.loader.setVisibility(View.GONE);
                    binding.questId.setText("");
                    binding.quesBtn.setEnabled(true);
                });
            } else binding.questId.setError("Write something.");
        });
        binding.questionCard.setOnClickListener(v -> binding.questId.setText(binding.questionT.getText().toString()));
        binding.questionCard1.setOnClickListener(v -> binding.questId.setText(binding.questionT1.getText().toString()));
        binding.weatherBtn.setOnClickListener(v -> getLocation());
        dashboardViewModel.getData().observe(getViewLifecycleOwner(), weatherModel -> {
            if (dialog != null && dialog.isShowing()) dialog.cancel();
            if (weatherModel != null)
                binding.questId.setText(MessageFormat.format("Country: {0} Weather: {1}, {2}, What " +
                                "should i do in this environment for my agro?", weatherModel.getSys().getCountry(),
                        Utils.getTempString(weatherModel.getMain().getTemp()), weatherModel.getWeather().get(0).getDescription()));
            else
                Toast.makeText(getContext(), "Not Found", Toast.LENGTH_SHORT).show();
        });
        dashboardViewModel.getQuestion().observe(getViewLifecycleOwner(),questionModels -> {
            if (questionModels != null){
                binding.questionT.setText(questionModels.get(0).getTitle());
                binding.questionT1.setText(questionModels.get(1).getTitle());
            }

        });

        permissionLauncher();
    }

    private void permissionLauncher() {
        requestPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) {
                        getLocation();
                    } else
                        Snackbar.make(binding.getRoot(), "Permission required.", Snackbar.LENGTH_SHORT).show();
                }
        );
    }

    private void getLocation() {
        try {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
            } else {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
                builder.setView(R.layout.progress_dialog);
                builder.setCancelable(false);
                dialog = builder.create();
                dialog.show();
                FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());
                fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        dashboardViewModel.weatherData(latitude, longitude);
                    } else {
                        Snackbar.make(binding.getRoot(), "Location not found.", Snackbar.LENGTH_SHORT).show();
                        if (dialog.isShowing())
                            dialog.cancel();
                    }

                }).addOnFailureListener(e -> {
                    Snackbar.make(binding.getRoot(), "Location not found.", Snackbar.LENGTH_SHORT).show();
                    if (dialog.isShowing())
                        dialog.cancel();
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (dialog.isShowing())
                dialog.cancel();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}