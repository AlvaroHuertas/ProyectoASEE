package com.unex.proyectoasee_nogymmembership.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.unex.proyectoasee_nogymmembership.Models.Routine;
import com.unex.proyectoasee_nogymmembership.Models.RoutineList;
import com.unex.proyectoasee_nogymmembership.R;

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.ViewHolder> {

    private final RoutineList routineList = new RoutineList();

    public interface OnItemClickListener {
        void onItemClick(Routine item);     //Type of the element to be returned
    }


    private final OnItemClickListener listener;

    // Provide a suitable constructor (depends on the kind of dataset)
    public RoutineAdapter(OnItemClickListener listener) {
//        mItems = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_routine, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(routineList.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void add(Routine item) {

        routineList.addItem(item);
        notifyDataSetChanged();

    }



    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView type;
        private CheckBox status;

        public ViewHolder(View itemView) {
            super(itemView);

            //Cogemos las referencias a cada uno de los objetos de la vista
            name = (TextView) itemView.findViewById(R.id.nameView);
            type = (TextView) itemView.findViewById(R.id.typeView);
            status = (CheckBox) itemView.findViewById(R.id.statusCheckBox);
        }

        public void bind(final Routine routine, final OnItemClickListener listener){

            name.setText(routine.getName());

            type.setText(routine.getType());

            status.setChecked(routine.getStatus() == Routine.Status.DONE);
        }

    }
}
