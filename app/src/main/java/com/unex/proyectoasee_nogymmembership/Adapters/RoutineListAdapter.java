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
    CallBack callback;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Routine item);     //Type of the element to be returned
    }

    public RoutineListAdapter(Context context, RoutineList routineList) {
        this.context = context;
        this.routineList = routineList;
        this.listener = null;
    }

    public RoutineListAdapter(OnItemClickListener listener) {
        this.routineList = new RoutineList();
        this.listener = listener;
    }

    public RoutineListAdapter(Context context,CallBack callback){
        this.context = context;
        this.listener = null;
        this.callback=callback;
        this.routineList=new RoutineList();
    }
    public interface CallBack{
        void onItemClicked(int position);
    }

    public void load(RoutineList items) {
        routineList.clear();
        routineList = items;
        notifyDataSetChanged();
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

    @Override
    public int getItemCount() {
        List<Routine> exercisesAux = new ArrayList<>();
        exercisesAux=routineList.getElements();
        if(exercisesAux != null)
            return exercisesAux.size();
        else
            Log.i("ExerciseList:", "is NULL");
        return 0;
    }

    @NonNull
    @Override
    public RoutineListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_routine_name,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(itemView);
        return viewHolder;
    }

}
