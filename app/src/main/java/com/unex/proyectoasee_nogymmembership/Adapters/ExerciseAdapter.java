package com.unex.proyectoasee_nogymmembership.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unex.proyectoasee_nogymmembership.Adds.ExerciseDescActivity;
import com.unex.proyectoasee_nogymmembership.Models.Exercise;
import com.unex.proyectoasee_nogymmembership.Models.ExerciseList;
import com.unex.proyectoasee_nogymmembership.R;

import java.util.ArrayList;
import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder>{
    Context context;
    private ExerciseList exerciseList;


    public interface OnItemClickListener {
        void onItemClick(Exercise item);     //Type of the element to be returned
    }

    public interface OnLongItemClickListener {
        void onItemLongClick(Exercise item);     //Type of the element to be returned
    }

    public ExerciseAdapter(Context context, ExerciseList exerciseList, OnLongItemClickListener listener) {
            this.context = context;
        this.exerciseList = exerciseList;
        this.longListener = listener;

    }

    public ExerciseAdapter(OnItemClickListener listener) {
        this.exerciseList = new ExerciseList();
        this.listener = listener;
    }

    public ExerciseAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    private OnItemClickListener listener;
    private OnLongItemClickListener longListener;

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView name,description;

        public ViewHolder(View item){
            super(item);
            cardView = (CardView) item.findViewById(R.id.cardView);
            name = (TextView) item.findViewById(R.id.exercise_name);


        }
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseAdapter.ViewHolder viewHolder, final int i) {
        List<Exercise> exercisesAux = new ArrayList<>();
        exercisesAux=exerciseList.getElements();
        viewHolder.name.setText(exercisesAux.get(i).getName());

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // - Attach Listener to FloatingActionButton. Implement onClick()
                Intent intent = new Intent(context, ExerciseDescActivity.class);
                intent.putExtra("id_exercise",i);
                context.startActivity(intent);

            }
        });
        /*viewHolder.description.setText(exerciseList.get(i).getDescription());*/
    }

    @Override
    public int getItemCount() {
        List<Exercise> exercisesAux = new ArrayList<>();
        exercisesAux=exerciseList.getElements();
        if(exercisesAux != null)
            return exercisesAux.size();
        else
            Log.i("ExerciseList:", "is NULL");
        return 0;
    }

    @NonNull
    @Override
    public ExerciseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_exercise,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(itemView);
        return viewHolder;
    }

}

