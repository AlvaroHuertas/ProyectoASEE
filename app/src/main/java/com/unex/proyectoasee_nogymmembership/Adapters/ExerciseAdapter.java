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
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // - Attach Listener to FloatingActionButton. Implement onClick()
                    Intent intent = new Intent(context, ExerciseDescActivity.class);
                    context.startActivity(intent);
                }
            });
            name = (TextView) item.findViewById(R.id.exercise_name);


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
        /*viewHolder.description.setText(exerciseList.get(i).getDescription());*/
    }

    @Override
    public int getItemCount() {
        if(exerciseList != null)
            return exerciseList.size();
        else
            Log.i("ExerciseList:", "is NULL");
        return 0;
    }
}
