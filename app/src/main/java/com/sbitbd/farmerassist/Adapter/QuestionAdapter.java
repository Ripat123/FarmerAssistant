package com.sbitbd.farmerassist.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.sbitbd.farmerassist.DataModel.QuestionModel;
import com.sbitbd.farmerassist.R;
import com.sbitbd.farmerassist.Repository.OnItemClickListener;
import com.sbitbd.farmerassist.ui.diseases.disease;
import com.sbitbd.farmerassist.utils.Utils;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder>{
    Context context;
    ArrayList<QuestionModel> quesList;
    private OnItemClickListener listener;
    int status;

    public QuestionAdapter(int status,Context context, ArrayList<QuestionModel> quesList) {
        this.context = context;
        this.quesList = quesList;
        this.status = status;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.question_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuestionModel questionModel = quesList.get(position);
        holder.bind(questionModel,position);
    }

    @Override
    public int getItemCount() {
        return quesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        MaterialCardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.question_t);
            cardView = itemView.findViewById(R.id.question_card);
        }

        public void bind(QuestionModel questionModel, int position){
            name.setText(questionModel.getTitle());
            cardView.setOnClickListener(v -> {
                if (status == 1){
                    Intent intent = new Intent(context, disease.class);
                    intent.putExtra(Utils.QUESTION,questionModel);
                    context.startActivity(intent);
                }else if (status == 2) {
                    if (listener != null)
                        listener.onItemClick(position);
                }

            });
        }
    }
}
