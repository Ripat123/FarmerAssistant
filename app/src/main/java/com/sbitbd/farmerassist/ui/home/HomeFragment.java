package com.sbitbd.farmerassist.ui.home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.carousel.CarouselLayoutManager;
import com.google.android.material.carousel.CarouselStrategy;
import com.google.android.material.carousel.FullScreenCarouselStrategy;
import com.google.android.material.carousel.HeroCarouselStrategy;
import com.google.android.material.carousel.MultiBrowseCarouselStrategy;
import com.google.android.material.carousel.UncontainedCarouselStrategy;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.sbitbd.farmerassist.Adapter.AgroAdapter;
import com.sbitbd.farmerassist.Adapter.DiseasesAdapter;
import com.sbitbd.farmerassist.Adapter.QuestionAdapter;
import com.sbitbd.farmerassist.DataModel.WeatherModel;
import com.sbitbd.farmerassist.R;
import com.sbitbd.farmerassist.databinding.FragmentHomeBinding;
import com.sbitbd.farmerassist.utils.Utils;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;
    private DiseasesAdapter adapter;
    private AgroAdapter agroAdapter;
    private QuestionAdapter questionAdapter;
    private ActivityResultLauncher<String> requestPermissionLauncher;
    private double latitude;
    private double longitude;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        initView();
    }

    private void initView(){
        viewModel.getData().observe(getViewLifecycleOwner(),weatherModel -> {
            if (weatherModel != null)
                setView(weatherModel);
            else
                Toast.makeText(getContext(), "Not Found", Toast.LENGTH_SHORT).show();
        });
        CarouselLayoutManager carouselLayoutManager = new CarouselLayoutManager();
        MultiBrowseCarouselStrategy multiBrowseCarouselStrategy = new MultiBrowseCarouselStrategy();
        multiBrowseCarouselStrategy.setSmallItemSizeMax(180);
        carouselLayoutManager.setCarouselStrategy(multiBrowseCarouselStrategy);
        binding.carouselRec.setLayoutManager(carouselLayoutManager);
        viewModel.getDiseases().observe(getViewLifecycleOwner(),diseasesModels -> {
            adapter = new DiseasesAdapter(getContext(),diseasesModels);
            binding.carouselRec.setAdapter(adapter);
        });
        GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
        binding.agroRec.setLayoutManager(manager);
        viewModel.getAgro().observe(getViewLifecycleOwner(),diseasesModels -> {
            agroAdapter = new AgroAdapter(getContext(),diseasesModels);
            binding.agroRec.setAdapter(agroAdapter);
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.quesRec.setLayoutManager(linearLayoutManager);
        viewModel.getQuestion().observe(getViewLifecycleOwner(),questionModels -> {
            questionAdapter= new QuestionAdapter(getContext(),questionModels);
            binding.quesRec.setAdapter(questionAdapter);
        });
        permissionLauncher();
        getLocation();
    }

    private void setView(WeatherModel model){
        binding.tempT.setText(Utils.getTempString(model.getMain().getTemp()));
        Glide.with(getContext())
                .load(Utils.ICON_URL+model.getWeather().get(0).getIcon()+".png")
                .into(binding.weatherIcon);
    }

    private void permissionLauncher(){
        requestPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted){
                        getLocation();
                    }
                    else
                        Snackbar.make(binding.getRoot(),"Permission required.",Snackbar.LENGTH_SHORT).show();
                }
        );
    }

    private void getLocation(){
        try {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
            } else {
                FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());
                fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
                    if (location != null){
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        viewModel.weatherData(latitude,longitude);
                    }else{
                        Snackbar.make(binding.getRoot(),"Location not found.",Snackbar.LENGTH_SHORT).show();
                    }

                }).addOnFailureListener(e -> {
                    Snackbar.make(binding.getRoot(),"Location not found.",Snackbar.LENGTH_SHORT).show();
                });
            }
        }catch (Exception e){e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}