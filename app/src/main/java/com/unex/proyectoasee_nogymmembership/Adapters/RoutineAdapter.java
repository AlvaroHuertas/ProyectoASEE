package com.unex.proyectoasee_nogymmembership.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
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
import android.widget.Toast;

import com.unex.proyectoasee_nogymmembership.DBUtils.RoutineCRUD;
import com.unex.proyectoasee_nogymmembership.Models.Routine;
import com.unex.proyectoasee_nogymmembership.Models.RoutineList;
import com.unex.proyectoasee_nogymmembership.R;
import com.unex.proyectoasee_nogymmembership.RoomDB.AppDataBase;

import java.util.List;

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.ViewHolder> implements Filterable {

    private Context mContext;

    private RoutineList routineList = new RoutineList();
    private RoutineList routineListFull = new RoutineList();

    private final OnItemClickListener listener;

    private static final String TAG = "RoutineAdapter";


    public interface OnItemClickListener {
        void onItemClick(Routine item);     //Type of the element to be returned
    }

    RoutineAdapter(RoutineList routineList) {
        this.routineList = routineList;
        listener = null;
    }


    public RoutineAdapter(Context context, OnItemClickListener listener) {
        mContext = context;
        this.listener = listener;
    }


    private AlertDialog AskOption(final Routine item, final int position) {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(mContext)
                //set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Delete")
                .setIcon(R.drawable.ic_delete_name)

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        delete(item, position);
                        dialog.dismiss();
                    }

                })


                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();
        return myQuittingDialogBox;

    }

    public void load(RoutineList items) {
        routineList.clear();
        routineList = items;
        routineListFull = new RoutineList();
        routineListFull.addAll(items.getElements());
        Log.v(TAG, "Loading data");
        notifyDataSetChanged();
    }


    @Override
    public RoutineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_routine, parent, false);

        return new ViewHolder(mContext, v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.bind(routineList.get(position), listener);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog diaBox = AskOption(routineList.get(position), position);
                diaBox.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return routineList.size();
    }

    @Override
    public Filter getFilter() {
        return routineFilter;
    }

    private Filter routineFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            RoutineList filteredList = new RoutineList();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(routineListFull.getElements());
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Routine r : routineListFull.getElements()) {
                    if (r.getText1().toLowerCase().contains(filterPattern)) {
                        filteredList.addItem(r);
                    }
                }

            }
            FilterResults results = new FilterResults();
            results.values = filteredList.getElements();
            Log.v(TAG, "Returning values Filtered list");
            for (Routine r : filteredList.getElements()) {
                Log.v(TAG, "Routine name: " + r.getName());
            }

            Log.v(TAG, "Returning values Full list");
            for (Routine r : routineListFull.getElements()) {
                Log.v(TAG, "Routine name: " + r.getName());
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            routineList.clear();
            routineList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public void delete(Routine item, int position) {
        routineList.deleteItem(position);
        routineListFull.deleteItem(position);
        new AsyncDelete().execute(item);
        notifyDataSetChanged();
    }

    public void add(Routine item) {
        routineList.addItem(item);
        routineListFull.addItem(item);
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

        public void bind(final Routine routine, final OnItemClickListener listener) {

            name.setText(routine.getName());

            type.setText(routine.getType());

            status.setChecked(routine.getStatus() == Routine.Status.DONE);

            status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        routine.setStatus(Routine.Status.DONE);

                        name.setBackgroundColor(Color.GREEN);
                    } else {
                        routine.setStatus(Routine.Status.NOTDONE);

                        name.setBackgroundColor(Color.WHITE);
                    }

                    RoutineCRUD crud = RoutineCRUD.getInstance(mContext);
                    crud.updateStatus(routine.getId(), routine.getStatus());
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

    class AsyncDelete extends AsyncTask<Routine, Void, Void> {

        @Override
        protected Void doInBackground(Routine... routines) {
            AppDataBase appDB = AppDataBase.getDataBase(mContext);

/*            Log.v(TAG, "Routine");
            Log.v(TAG, String.valueOf(routines[0].getId()));
            Log.v(TAG, routines[0].getName());
            Log.v(TAG, routines[0].getType());
            Log.v(TAG, routines[0].getStatus().toString());*/

            appDB.routineDAO().deleteRoutines(routines[0]);
            return null;
        }

    }
}
