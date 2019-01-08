package com.unex.proyectoasee_nogymmembership.Adapters;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;


import com.unex.proyectoasee_nogymmembership.Models.Routine;
import com.unex.proyectoasee_nogymmembership.Models.RoutineList;
import com.unex.proyectoasee_nogymmembership.R;

import java.util.ArrayList;
import java.util.List;

public class RoutineListAdapter extends RecyclerView.Adapter<RoutineListAdapter.ViewHolder>{
    private Context context;
    private RoutineList routineList;

    /**
     * Callback that defines the click function in one element of the data set
     */
    CallBack callback;

    /**
     * Defines the method it is going to be executed when click on an item.
     */
    public interface CallBack{
        void onItemClicked(int position);
    }

    public RoutineListAdapter(Context context,CallBack callback){
        this.context = context;
        this.callback=callback;
        this.routineList=new RoutineList();
    }

    @NonNull
    @Override
    public RoutineListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_routine_name,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RoutineListAdapter.ViewHolder viewHolder, final int i) {
        List<Routine> routinesAux = new ArrayList<>();
        routinesAux=routineList.getElements();
        viewHolder.name.setText(routinesAux.get(i).getName());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onItemClicked(i);

            }
        });

    }

    /**
     * Loads all the routines in the data set of the adapter
     *
     * @param items List of routines we are managing in the application
     */
    public void load(RoutineList items) {
        routineList.clear();
        routineList = items;
        notifyDataSetChanged();
    }

    /**
     * GET an item in a certain position
     * @param position Position of the item
     * @return The item we are looking for
     */
    public Routine getFromPosition(int position) {
        return routineList.get(position);
    }

    @Override
    public int getItemCount() {
        List<Routine> exercisesAux = new ArrayList<>();
        if(routineList != null) {
            exercisesAux = routineList.getElements();
            return exercisesAux.size();
        } else {
            Log.i("ExerciseList:", "is NULL");
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView name;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.routineCardView);
            name=(TextView) itemView.findViewById(R.id.routine_name);
        }
    }

}
