package com.sbitbd.farmerassist.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.carousel.MaskableFrameLayout;
import com.sbitbd.farmerassist.DataModel.DiseasesModel;
import com.sbitbd.farmerassist.R;
import com.sbitbd.farmerassist.ui.diseases.disease;

import java.util.ArrayList;

public class DiseasesAdapter extends RecyclerView.Adapter<DiseasesAdapter.ViewHolder> {
    Context context;
    ArrayList<DiseasesModel> diseasesList;

    public DiseasesAdapter(Context context, ArrayList<DiseasesModel> arrayList) {
        this.context = context;
        this.diseasesList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.diseases_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DiseasesModel diseasesModel = diseasesList.get(position);
        holder.bind(diseasesModel);
    }

    @Override
    public int getItemCount() {
        return diseasesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        MaskableFrameLayout mask;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.diseases_image);
            mask = itemView.findViewById(R.id.mask);
        }
        public void bind(DiseasesModel model){
            Glide.with(context).load(model.getUrl()).into(imageView);
            mask.setOnClickListener(v -> {
                Intent intent = new Intent(context, disease.class);
                intent.putExtra("data",model);
                context.startActivity(intent);
            });
        }
    }
}
