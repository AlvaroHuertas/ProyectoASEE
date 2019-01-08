package com.unex.proyectoasee_nogymmembership.Adds;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.unex.proyectoasee_nogymmembership.Adapters.RoutineListAdapter;
import com.unex.proyectoasee_nogymmembership.Models.Exercise;
import com.unex.proyectoasee_nogymmembership.Models.Routine;
import com.unex.proyectoasee_nogymmembership.Models.RoutineList;
import com.unex.proyectoasee_nogymmembership.R;
import com.unex.proyectoasee_nogymmembership.Repository.AppRepository;

import java.util.List;

public class AddExerciseToRoutine extends AppCompatActivity{
    private RecyclerView mRecyclerView;
    private RoutineListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private long id_ex;
    private String name;
    private String description;
    private long id_rou;

    private static final String TAG = "RoutineAdapter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Exercise e = (Exercise) intent.getSerializableExtra("exercise");
        id_ex= e.getExerciseId();
        
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

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RoutineListAdapter(this, new RoutineListAdapter.CallBack() {
            @Override
            public void onItemClicked(int position) {
                id_rou = mAdapter.getFromPosition(position).getId();
                new AsyncAddRoutine().execute();
            }
        });

        new AsyncLoad().execute();

        mRecyclerView.setAdapter(mAdapter);

    }

    /**
     * AsyncTask to load all the routines in the Recycle View
     */
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

    /**
     * AsyncTask to add a exercise to the selected routine
     */
    class AsyncAddRoutine extends AsyncTask<Void, Void, Exercise> {
        @Override
        protected Exercise doInBackground(Void... voids) {
            AppRepository r = AppRepository.getInstance(AddExerciseToRoutine.this);
            Exercise exercise = r.getExerciseInRoutine((int)id_ex,(int)id_rou);

            // Checks if exercise is not added
            if(exercise==null){
                Exercise exerciseToInsert=new Exercise(id_ex,name,description,id_rou, 0, 0);
                r.addExercise(exerciseToInsert);

                runOnUiThread(() -> {
                    Toast toast1 = Toast.makeText(AddExerciseToRoutine.this, "Añadido a rutina ", Toast.LENGTH_SHORT);
                    toast1.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
                    toast1.show();
                });

            }else{
                runOnUiThread(() -> {
                    Toast toast1 = Toast.makeText(AddExerciseToRoutine.this, "Ya se encuentra añadido a la rutina ", Toast.LENGTH_SHORT);
                    toast1.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
                    toast1.show();
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
