package com.unex.proyectoasee_nogymmembership.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
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
import com.unex.proyectoasee_nogymmembership.Models.Routine;
import com.unex.proyectoasee_nogymmembership.R;
import com.unex.proyectoasee_nogymmembership.RoomDB.AppDataBase;
import com.unex.proyectoasee_nogymmembership.ShowRoutine;

import java.util.ArrayList;
import java.util.List;

public class ExerciseInRoutineAdapter extends RecyclerView.Adapter<ExerciseInRoutineAdapter.ViewHolder>{
    private Context context;
    private ExerciseList exerciseList;

    private final OnItemLongClickListener onLongClickListener;

    public void deleteItem(Exercise item) {
        for(Exercise e : exerciseList.getElements()){
            if(e.getExerciseId() == item.getExerciseId()){
                exerciseList.deleteItem(item);
                notifyDataSetChanged();
            }
        }
    }

    public interface OnItemLongClickListener {
        void onLongItemClickListener(Exercise item);
    }

    public ExerciseInRoutineAdapter(Context context, OnItemLongClickListener onLongClickListener) {
        this.context=context;
        this.exerciseList=new ExerciseList();
        this.onLongClickListener = onLongClickListener;
    }


    public void load(ExerciseList items) {
        exerciseList.clear();
        exerciseList = items;
        notifyDataSetChanged();
    }

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
    public void onBindViewHolder(ViewHolder viewHolder,final int i) {

        viewHolder.name.setText(exerciseList.getElements().get(i).getName());
        viewHolder.cardView.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View v) {
                onLongClickListener.onLongItemClickListener(exerciseList.getElements().get(i));
                return true;
            }
        });
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
    public ExerciseInRoutineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_exercise,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(itemView);
        return viewHolder;
    }

}
