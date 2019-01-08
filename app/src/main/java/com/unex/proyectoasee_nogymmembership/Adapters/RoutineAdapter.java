package com.unex.proyectoasee_nogymmembership.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.unex.proyectoasee_nogymmembership.Models.Routine;
import com.unex.proyectoasee_nogymmembership.Models.RoutineList;
import com.unex.proyectoasee_nogymmembership.R;
import com.unex.proyectoasee_nogymmembership.RoomDB.AppDataBase;

import java.util.List;

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.ViewHolder> implements Filterable {

    private Context mContext;

    /**
     * List of routine we use in order to show the elements of the recycler view
     */
    private RoutineList routineList = new RoutineList();

    /**
     * List of routines that always contains the exercises in the database. This one its never shown,
     * we just use it as a support list.
     */
    private RoutineList routineListFull = new RoutineList();

    /**
     * Listener that allow us to define the behaviour of an item when you perform a click.
     */
    private final OnItemClickListener listener;
    /**
     * Listener that allow us to define the behaviour of an item when you perform a long click.
     */
    private final OnItemLongClickListener onLongClickListener;

    private static final String TAG = "RoutineAdapter";

    /**
     * Deletes an item from the data set. It find the item according to the id.
     *
     * @param routine Routine we are going to delete
     */
    public void deleteItem(Routine routine) {
        for(Routine r : routineList.getElements()){
            if(r.getId() == routine.getId()){
                routineList.deleteItem(routine);
                routineListFull.deleteItem(routine);
                notifyDataSetChanged();
            }
        }
    }

    /**
     * Defines the method it is going to be executed when click on an item.
     */
    public interface OnItemClickListener {
        void onItemClick(Routine item);     //Type of the element to be returned
    }

    /**
     * Defines the method it is going to be executed when long click on an item.
     */
    public interface OnItemLongClickListener {
        void onLongItemClickListener(Routine item);
    }


    public RoutineAdapter(Context context, OnItemClickListener listener, OnItemLongClickListener onLongClickListener) {
        mContext = context;
        this.listener = listener;
        this.onLongClickListener = onLongClickListener;
    }

    @Override
    public RoutineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_routine, parent, false);

        return new ViewHolder(mContext, v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.bind(routineList.get(position), listener, onLongClickListener);
    }

    /**
     * Loads all the routines in the data set of the adapter
     *
     * @param items List of routines we are managing in the application
     */
    public void load(RoutineList items) {
        routineList.clear();
        routineList = items;
        routineListFull = new RoutineList();
        routineListFull.addAll(items.getElements());
        notifyDataSetChanged();
    }

    /**
     * Adds a routine to the data set
     * @param item Routine we are going to add
     */
    public void add(Routine item) {
        routineList.addItem(item);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return routineList.size();
    }

    @Override
    public Filter getFilter() {
        return routineFilter;
    }

    /**
     * Filter that allow us to search routines. It checks if the routine NAME CONTAINS a certain char sequence
     */
    private Filter routineFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            RoutineList filteredList = new RoutineList();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(routineListFull.getElements());
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Routine r : routineListFull.getElements()) {
                    if (r.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.addItem(r);
                    }
                }

            }
            FilterResults results = new FilterResults();
            results.values = filteredList.getElements();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            routineList.clear();
            routineList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView type;
        private CheckBox status;

        public ViewHolder(Context context, View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.nameView);
            type = (TextView) itemView.findViewById(R.id.typeView);
            status = (CheckBox) itemView.findViewById(R.id.statusCheckBox);
        }

        /**
         * Binds all the content into the ViewHolder
         * @param routine Element to bind
         * @param listener OnClickListener for the item
         * @param longListener OnLongClickListener for the item
         */
        public void bind(final Routine routine, final OnItemClickListener listener, final OnItemLongClickListener longListener) {

            name.setText(routine.getName());

            type.setText(routine.getType());

            status.setChecked(routine.getStatus() == Routine.Status.DONE);

            status.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    routine.setStatus(Routine.Status.DONE);
                } else {
                    routine.setStatus(Routine.Status.NOTDONE);
                }

                new AsyncStatus().execute(routine);
            });


            itemView.setOnClickListener(v -> listener.onItemClick(routine));

            itemView.setOnLongClickListener(v -> {
                longListener.onLongItemClickListener(routine);
                return true;
            });

        }

    }

    class AsyncStatus extends AsyncTask<Routine, Void, Void> {

        @Override
        protected Void doInBackground(Routine... routines) {
            AppDataBase appDB = AppDataBase.getDataBase(mContext);
            appDB.routineDAO().updateStatus(routines[0]);
            return null;
        }
    }
}
