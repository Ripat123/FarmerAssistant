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
import com.sbitbd.farmerassist.ui.diseases.disease;
import com.sbitbd.farmerassist.utils.Utils;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder>{
    Context context;
    ArrayList<QuestionModel> quesList;

    public QuestionAdapter(Context context, ArrayList<QuestionModel> quesList) {
        this.context = context;
        this.quesList = quesList;
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
        holder.bind(questionModel);
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

        public void bind(QuestionModel questionModel){
            name.setText(questionModel.getTitle());
            cardView.setOnClickListener(v -> {
                Intent intent = new Intent(context, disease.class);
                intent.putExtra(Utils.QUESTION,questionModel.getTitle());
                context.startActivity(intent);
            });
        }
    }
}
