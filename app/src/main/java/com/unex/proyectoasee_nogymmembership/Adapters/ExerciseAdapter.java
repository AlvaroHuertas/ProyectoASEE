package com.unex.proyectoasee_nogymmembership.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unex.proyectoasee_nogymmembership.Models.Exercise;
import com.unex.proyectoasee_nogymmembership.Models.ExerciseList;
import com.unex.proyectoasee_nogymmembership.R;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {
    private Context context;
    private ExerciseList exerciseList;

    public interface OnItemClickListener {
        void onItemClick(Exercise item);     //Type of the element to be returned
    }

    private final OnItemClickListener listener;

    public ExerciseAdapter(Context context, ExerciseList exerciseList) {
        this.context = context;
        this.exerciseList = exerciseList;
        this.listener = null;
    }

    public ExerciseAdapter(OnItemClickListener listener) {
        this.exerciseList = new ExerciseList();
        this.listener = listener;
    }

    public ExerciseAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ExerciseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_exercise, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseAdapter.ViewHolder viewHolder, int i) {
        viewHolder.bind(exerciseList.getElements().get(i), listener);
    }

    @Override
    public int getItemCount() {
        if (exerciseList.getElements() != null)
            return exerciseList.getElements().size();
        else
            Log.i("ExerciseList:", "is NULL");
        return 0;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        private TextView name, description;

        public ViewHolder(View item) {
            super(item);
            cardView = (CardView) item.findViewById(R.id.cardView);
            name = (TextView) item.findViewById(R.id.exercise_name);
            description = (TextView) item.findViewById(R.id.exercise_description);

        }

        public void bind(final Exercise exercise, final OnItemClickListener listener) {
            name.setText(exercise.getName());
            description.setText(exercise.getDescription());
        }
    }




}
