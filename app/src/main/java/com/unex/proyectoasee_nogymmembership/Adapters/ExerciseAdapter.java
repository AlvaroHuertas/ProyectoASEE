package com.unex.proyectoasee_nogymmembership.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unex.proyectoasee_nogymmembership.Models.Exercise;
import com.unex.proyectoasee_nogymmembership.R;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder>{
    Context context;
    List<Exercise> exerciseList;

    public ExerciseAdapter(Context context, List<Exercise> exerciseList){
        this.context = context;
        this.exerciseList=exerciseList;

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView name,description;

        public ViewHolder(View item){
            super(item);
            cardView = (CardView) item.findViewById(R.id.cardView);
            name = (TextView) item.findViewById(R.id.exercise_name);
            description = (TextView) item.findViewById(R.id.exercise_description);

        }
    }

    @NonNull
        @Override
        public ExerciseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_exercise,viewGroup,false);
            ViewHolder viewHolder=new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseAdapter.ViewHolder viewHolder, int i) {
        viewHolder.name.setText(exerciseList.get(i).getName());
        viewHolder.description.setText(exerciseList.get(i).getDescription());
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
}
