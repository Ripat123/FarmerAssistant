package com.sbitbd.farmerassist.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.sbitbd.farmerassist.DataModel.AgroModel;
import com.sbitbd.farmerassist.DataModel.DiseasesModel;
import com.sbitbd.farmerassist.R;
import com.sbitbd.farmerassist.ui.agro.agro;
import com.sbitbd.farmerassist.ui.diseases.disease;

import java.util.ArrayList;

public class AgroAdapter extends RecyclerView.Adapter<AgroAdapter.ViewHolder>{
    Context context;
    ArrayList<AgroModel> agroList;

    public AgroAdapter(Context context, ArrayList<AgroModel> agroList) {
        this.context = context;
        this.agroList = agroList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.agro_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AgroModel model = agroList.get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return agroList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        MaterialCardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.agro_img);
            name = itemView.findViewById(R.id.agroItem_t);
            cardView = itemView.findViewById(R.id.agro_card);
        }
        public void bind(AgroModel model){
            name.setText(model.getName());
            Glide.with(context).load(model.getUrl()).into(imageView);
            cardView.setOnClickListener(v -> {
                Intent intent = new Intent(context, agro.class);
                intent.putExtra("data",model);
                context.startActivity(intent);
            });
        }
    }
}
