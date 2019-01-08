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

public class ExerciseInRoutineAdapter extends RecyclerView.Adapter<ExerciseInRoutineAdapter.ViewHolder> {
    private Context context;

    /**
     * List of exercises we use in order to show the elements of the recycler view
     */
    private ExerciseList exerciseList;

    /**
     * Listener that allow us to define the behaviour of an item when you perform a long click.
     */
    private final OnItemLongClickListener onLongClickListener;

    /**
     * Defines the method it is going to be executed when on long click on an item.
     */
    public interface OnItemLongClickListener {
        void onLongItemClickListener(Exercise item);
    }

    public ExerciseInRoutineAdapter(Context context, OnItemLongClickListener onLongClickListener) {
        this.context = context;
        this.exerciseList = new ExerciseList();
        this.onLongClickListener = onLongClickListener;
    }

    @NonNull
    @Override
    public ExerciseInRoutineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_exercise, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {

        viewHolder.name.setText(exerciseList.getElements().get(i).getName());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ExerciseDescActivity.class);
                intent.putExtra("name_exercise", exerciseList.getElements().get(i).getName());
                intent.putExtra("description_exercise", exerciseList.getElements().get(i).getDescription());
                context.startActivity(intent);

            }
        });

        viewHolder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onLongClickListener.onLongItemClickListener(exerciseList.getElements().get(i));
                return true;
            }
        });
    }

    /**
     * Loads all the exercises in the data set of the adapter
     *
     * @param items List of exercises we are managing inside a routine
     */
    public void load(ExerciseList items) {
        exerciseList.clear();
        exerciseList = items;
        notifyDataSetChanged();
    }

    /**
     * Deletes an item from the data set. It find the item according to the id.
     *
     * @param item Exercise we are going to delete
     */
    public void deleteItem(Exercise item) {
        for (Exercise e : exerciseList.getElements()) {
            if (e.getExerciseId() == item.getExerciseId()) {
                exerciseList.deleteItem(item);
                notifyDataSetChanged();
            }
        }
    }

    @Override
    public int getItemCount() {
        List<Exercise> exercisesAux = new ArrayList<>();
        exercisesAux = exerciseList.getElements();
        if (exercisesAux != null)
            return exercisesAux.size();
        else
            Log.i("ExerciseList:", "is NULL");
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView name, description;

        public ViewHolder(View item) {
            super(item);
            cardView = (CardView) item.findViewById(R.id.cardView);
            name = (TextView) item.findViewById(R.id.exercise_name);


        }
    }


}
