package com.sbitbd.farmerassist.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sbitbd.farmerassist.DataModel.DiseasesModel;
import com.sbitbd.farmerassist.R;

import java.util.ArrayList;

public class AgroAdapter extends RecyclerView.Adapter<AgroAdapter.ViewHolder>{
    Context context;
    ArrayList<DiseasesModel> agroList;

    public AgroAdapter(Context context, ArrayList<DiseasesModel> agroList) {
        this.context = context;
        this.agroList = agroList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.agro_item, parent, false);
        return new AgroAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DiseasesModel diseasesModel = agroList.get(position);
        holder.bind(diseasesModel);
    }

    @Override
    public int getItemCount() {
        return agroList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.agro_img);
            name = itemView.findViewById(R.id.agroItem_t);
        }
        public void bind(DiseasesModel model){
            name.setText(model.getName());
            Glide.with(context).load(model.getUrl()).into(imageView);
        }
    }
}
