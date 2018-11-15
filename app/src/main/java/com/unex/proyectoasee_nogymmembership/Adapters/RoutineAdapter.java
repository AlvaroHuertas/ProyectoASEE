package com.unex.proyectoasee_nogymmembership.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.unex.proyectoasee_nogymmembership.DBUtils.RoutineCRUD;
import com.unex.proyectoasee_nogymmembership.Models.Routine;
import com.unex.proyectoasee_nogymmembership.Models.RoutineList;
import com.unex.proyectoasee_nogymmembership.R;

import java.util.List;

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.ViewHolder> {

    Context mContext;

    private RoutineList routineList = new RoutineList();

    public void load(RoutineList items) {
        routineList.clear();
        routineList = items;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Routine item);     //Type of the element to be returned
    }

    private final OnItemClickListener listener;

    // Provide a suitable constructor (depends on the kind of dataset)
    public RoutineAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public RoutineAdapter(Context context, OnItemClickListener listener) {
        mContext = context;
        this.listener = listener;
    }

    @Override
    public RoutineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_routine, parent, false);

        return new ViewHolder(mContext, v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(routineList.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return routineList.size();
    }

    public void add(Routine item) {

        routineList.addItem(item);
        notifyDataSetChanged();

    }



    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView type;
        private CheckBox status;

        private Context mContext;

        public ViewHolder(Context context, View itemView) {
            super(itemView);

            mContext = context;

            //Cogemos las referencias a cada uno de los objetos de la vista
            name = (TextView) itemView.findViewById(R.id.nameView);
            type = (TextView) itemView.findViewById(R.id.typeView);
            status = (CheckBox) itemView.findViewById(R.id.statusCheckBox);
        }

        public void bind(final Routine routine, final OnItemClickListener listener){

            name.setText(routine.getName());

            type.setText(routine.getType());

            status.setChecked(routine.getStatus() == Routine.Status.DONE);

            status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        routine.setStatus(Routine.Status.DONE);

                        name.setBackgroundColor(Color.GREEN);
                    } else{
                        routine.setStatus(Routine.Status.NOTDONE);

                        name.setBackgroundColor(Color.WHITE);
                    }

                    RoutineCRUD crud = RoutineCRUD.getInstance(mContext);
                    crud.updateStatus(routine.getId(),routine.getStatus());
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    listener.onItemClick(routine);
                }
            });
        }

    }
}
