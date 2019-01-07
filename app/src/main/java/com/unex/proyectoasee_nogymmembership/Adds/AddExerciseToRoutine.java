package com.unex.proyectoasee_nogymmembership.Adds;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.unex.proyectoasee_nogymmembership.Adapters.RoutineAdapter;
import com.unex.proyectoasee_nogymmembership.Adapters.RoutineListAdapter;
import com.unex.proyectoasee_nogymmembership.DBUtils.RoutineCRUD;
import com.unex.proyectoasee_nogymmembership.FragmentOne;
import com.unex.proyectoasee_nogymmembership.Models.Exercise;
import com.unex.proyectoasee_nogymmembership.Models.Routine;
import com.unex.proyectoasee_nogymmembership.Models.RoutineList;
import com.unex.proyectoasee_nogymmembership.R;
import com.unex.proyectoasee_nogymmembership.Repository.AppRepository;
import com.unex.proyectoasee_nogymmembership.RoomDB.AppDataBase;

import java.util.List;

public class AddExerciseToRoutine extends AppCompatActivity implements RoutineListAdapter.CallBack{
    private RecyclerView mRecyclerView;
    private RoutineListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private long id_ex=-1;
    private String name;
    private String description;
    private long id_rou;

    private static final String TAG = "RoutineAdapter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        id_ex=intent.getIntExtra("id_exercise",-1);

        setContentView(R.layout.activity_add_ex_to_routine);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);


        name=intent.getStringExtra("name_exercise");
        description=intent.getStringExtra("description_exercise");

        mRecyclerView = (RecyclerView) findViewById(R.id.routines_list_recycler);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RoutineListAdapter(this,AddExerciseToRoutine.this);

        new AsyncLoad().execute();

        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onItemClicked(int position) {
        this.id_rou = mAdapter.getFromPosition(position).getId();

        new AsyncAddRoutine().execute();

    }

    class AsyncLoad extends AsyncTask<Void, Void, List<Routine>> {
        @Override
        protected List<Routine> doInBackground(Void... voids) {
            AppRepository r = AppRepository.getInstance(AddExerciseToRoutine.this);
            List<Routine> items = r.getAllRoutines();
            return items;
        }

        @Override
        protected void onPostExecute(List<Routine> items){
            super.onPostExecute(items);
            RoutineList r = new RoutineList(items);
            mAdapter.load(r);
        }

    }

    class AsyncAddRoutine extends AsyncTask<Void, Void, Exercise> {
        @Override
        protected Exercise doInBackground(Void... voids) {
            AppRepository r = AppRepository.getInstance(AddExerciseToRoutine.this);
            Exercise exercise = r.getExerciseInRoutine((int)id_ex,(int)id_rou);

            // Checks if exercise is not added
            if(exercise==null){
                Exercise exerciseToInsert=new Exercise(id_ex,name,description,id_rou);
                r.addExercise(exerciseToInsert);

                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast toast1 = Toast.makeText(AddExerciseToRoutine.this, "Añadido a rutina ", Toast.LENGTH_SHORT);
                        toast1.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
                        toast1.show();
                    }
                });

            }else{
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast toast1 = Toast.makeText(AddExerciseToRoutine.this, "Ya se encuentra añadido a la rutina ", Toast.LENGTH_SHORT);
                        toast1.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
                        toast1.show();
                    }
                });

            }
            return exercise;
        }

        @Override
        protected void onPostExecute(Exercise items){
            super.onPostExecute(items);

        }

    }
}
