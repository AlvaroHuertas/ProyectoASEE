package com.unex.proyectoasee_nogymmembership.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.unex.proyectoasee_nogymmembership.DBUtils.RoutineCRUD;
import com.unex.proyectoasee_nogymmembership.Models.Routine;
import com.unex.proyectoasee_nogymmembership.Models.RoutineList;
import com.unex.proyectoasee_nogymmembership.R;
import com.unex.proyectoasee_nogymmembership.RoomDB.AppDataBase;

import java.util.List;

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.ViewHolder> {

    private Context mContext;

    private RoutineList routineList = new RoutineList();

    public interface OnItemClickListener {
        void onItemClick(Routine item);     //Type of the element to be returned
    }

    private final OnItemClickListener listener;

    public RoutineAdapter(Context context, OnItemClickListener listener) {
        mContext = context;
        this.listener = listener;
    }

    public RoutineAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }



    private AlertDialog AskOption(final Routine item, final int position)
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(mContext)
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

    public void delete(Routine item, int position){
        routineList.deleteItem(position);

         new AsyncDelete().execute(item);
        notifyDataSetChanged();
    }

    public void add(Routine item) {

        routineList.addItem(item);
        notifyDataSetChanged();

    }



    class ViewHolder extends RecyclerView.ViewHolder {

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

                    new AsyncStatus().execute(routine);
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
            appDB.routineDAO().deleteRoutines(routines[0]);
            return null;
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
