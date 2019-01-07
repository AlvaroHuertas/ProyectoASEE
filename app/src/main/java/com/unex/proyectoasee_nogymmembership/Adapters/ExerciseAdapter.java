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

    private ExerciseList exerciseList;
    private ExerciseList exerciseListFull;

    private final OnItemClickListener listener;

    @Override
    public Filter getFilter() {
        return exerciseFilter;
    }

    private Filter exerciseFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ExerciseList filteredList = new ExerciseList();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exerciseListFull.getElements());
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Exercise e : exerciseListFull.getElements()) {
                    if (e.getText1().toLowerCase().contains(filterPattern)) {
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


    public interface OnItemClickListener {
        void onItemClick(Exercise item);     //Type of the element to be returned
    }

    public ExerciseAdapter(Context context, ExerciseList exerciseList) {
        this.context = context;
        this.exerciseList = exerciseList;
        this.exerciseListFull = new ExerciseList();
        this.exerciseListFull.addAll(exerciseList.getElements());
        this.listener = null;
    }

    public ExerciseAdapter(Context context){
        this.context = context;
        this.listener = null;
    }

    public void load(ExerciseList items) {
        exerciseList.clear();
        exerciseList = items;
        exerciseListFull = new ExerciseList();
        exerciseListFull.addAll(items.getElements());
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
    public void onBindViewHolder(@NonNull ExerciseAdapter.ViewHolder viewHolder, final int i) {
        List<Exercise> exercisesAux = new ArrayList<>();
        exercisesAux=exerciseList.getElements();
        viewHolder.name.setText(exercisesAux.get(i).getName());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ExerciseDescActivity.class);
                intent.putExtra("id_exercise",i+1);
                intent.putExtra("name_exercise",exerciseList.getElements().get(i).getName());
                intent.putExtra("description_exercise",exerciseList.getElements().get(i).getDescription());
                context.startActivity(intent);

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
    public ExerciseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_exercise,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(itemView);
        return viewHolder;
    }

}

