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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.unex.proyectoasee_nogymmembership.Adds.ExerciseDescActivity;
import com.unex.proyectoasee_nogymmembership.Models.Exercise;
import com.unex.proyectoasee_nogymmembership.Models.ExerciseList;
import com.unex.proyectoasee_nogymmembership.R;

import java.util.ArrayList;
import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> implements Filterable {
    private Context context;

    private static final String LOAD_LIST = "ExercisesList";

    /**
     * List of exercises we use in order to show the elements of the recycler view
     */
    private ExerciseList exerciseList;
    /**
     * List of exercises that always contains the exercises in the database. This one its never shown,
     * we just use it as a support list.
     */
    private ExerciseList exerciseListFull;

    public ExerciseAdapter(Context context, ExerciseList exerciseList) {
        this.context = context;
        this.exerciseList = exerciseList;
        this.exerciseListFull = new ExerciseList();
        this.exerciseListFull.addAll(exerciseList.getElements());
    }

    @NonNull
    @Override
    public ExerciseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_exercise_single,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseAdapter.ViewHolder viewHolder, final int i) {
       viewHolder.bind(exerciseList.getExercise(i), i);
    }

    /**
     * Loads all the exercises in the data set of the adapter
     * @param items LIst of exercises we are managing in the application
     */
    public void load(ExerciseList items) {
        Log.v(LOAD_LIST, "Loading data into adapter");
        exerciseList.clear();
        exerciseList = items;
        exerciseListFull = new ExerciseList();
        exerciseListFull.addAll(items.getElements());
        notifyDataSetChanged();
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

    @Override
    public Filter getFilter() {
        return exerciseFilter;
    }

    /**
     * Filter that allow us to search exercises. It checks if the exercise NAME CONTAINS a certain char sequence
     */
    private Filter exerciseFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ExerciseList filteredList = new ExerciseList();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exerciseListFull.getElements());
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Exercise e : exerciseListFull.getElements()) {
                    if (e.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.addItem(e);
                    }
                }

            }
            FilterResults results = new FilterResults();
            results.values = filteredList.getElements();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            exerciseList.clear();
            exerciseList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;

        public ViewHolder(View item){
            super(item);
            name = (TextView) item.findViewById(R.id.exercise_name);
        }

        /**
         * Binds all the content into the ViewHolder
         * @param exercise Exercise to bind
         * @param position Position of the exercise
         */
        public void bind(Exercise exercise, int position) {
            name.setText(exercise.getName());
            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, ExerciseDescActivity.class);
                intent.putExtra("exercise", exercise);
                intent.putExtra("name_exercise",exercise.getName());
                intent.putExtra("description_exercise",exercise.getDescription());
                context.startActivity(intent);
            });

        }
    }

}

