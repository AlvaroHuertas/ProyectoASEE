package com.unex.proyectoasee_nogymmembership.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
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
import com.unex.proyectoasee_nogymmembership.Models.ExerciseList;
import com.unex.proyectoasee_nogymmembership.Models.Routine;
import com.unex.proyectoasee_nogymmembership.R;
import com.unex.proyectoasee_nogymmembership.RoomDB.AppDataBase;
import com.unex.proyectoasee_nogymmembership.ShowRoutine;

import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;

public class ExerciseInRoutineAdapter extends RecyclerView.Adapter<ExerciseInRoutineAdapter.ViewHolder>{
    private Context context;
    private ExerciseList exerciseList;

    public ExerciseInRoutineAdapter(Context context) {
        this.context=context;
        this.exerciseList=new ExerciseList();
    }

    private AlertDialog AskOption(final Exercise item, final int position)
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(context)
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

    public void load(ExerciseList items) {
        exerciseList.clear();
        exerciseList = items;
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
    public void onBindViewHolder(ViewHolder viewHolder,final int i) {

        viewHolder.name.setText(exerciseList.getElements().get(i).getName());

        // Long click functions
        viewHolder.cardView.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View v) {
                AlertDialog diaBox = AskOption(exerciseList.getElements().get(i),i);
                diaBox.show();
                return false;
            }
        });

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Starting exercise description
                Intent intent = new Intent(context, ExerciseDescActivity.class);
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

    public void delete(Exercise item, int position){
        exerciseList.deleteItem(position);

        new AsyncDelete().execute(item);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExerciseInRoutineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_exercise,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(itemView);
        return viewHolder;
    }


    class AsyncDelete extends AsyncTask<Exercise, Void, Void> {

        @Override
        protected Void doInBackground(Exercise... exercises) {
            // Delete exercise selected from DB
            AppDataBase appDB = AppDataBase.getDataBase(context);
            appDB.exerciseDAO().deleteExercises(exercises[0]);
            return null;
        }
    }
}
