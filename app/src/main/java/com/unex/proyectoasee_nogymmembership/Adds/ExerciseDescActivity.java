package com.unex.proyectoasee_nogymmembership.Adds;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.unex.proyectoasee_nogymmembership.Models.Exercise;
import com.unex.proyectoasee_nogymmembership.R;
import com.unex.proyectoasee_nogymmembership.Repository.AppRepository;
import com.unex.proyectoasee_nogymmembership.ViewModel.ExerciseDescActivityViewModel;

public class ExerciseDescActivity extends AppCompatActivity {
    private long id;
    private String name;
    private String description;
    private long idRoutine;

    private Exercise e;
    private int setsNumber;
    private int repsNumber;

    EditText sets;
    EditText reps;

    private boolean fromRoutine;

    public static String TAG = "ExerciseDescActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        e = (Exercise) intent.getSerializableExtra("exercise");
        id = e.getExerciseId();
        idRoutine = e.getRoutineId();
        name = e.getName();
        description = e.getDescription();

        fromRoutine = intent.getBooleanExtra("fromRoutine", false);
        if (fromRoutine == false) {
            setContentView(R.layout.exercise_desc);
        } else {
            setContentView(R.layout.exercise_desc_in_rout);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        TextView name = findViewById(R.id.exercise_name_desc);
        TextView description = findViewById(R.id.exercise_description);

        sets = (EditText) findViewById(R.id.setsNumber);
        reps = (EditText) findViewById(R.id.repsNumber);

        setsNumber = e.getSets();
        if (setsNumber != 0)
            sets.setText(String.valueOf(setsNumber));
        repsNumber = e.getReps();
        if (repsNumber != 0)
            reps.setText(String.valueOf(repsNumber));

        name.setText(this.name);
        description.setText(this.description);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //don't run click button when coming from a routine
        if (fromRoutine == false) {
            Button button = findViewById(R.id.exercise_button);
            button.setOnClickListener(addToRoutine);
        } else {
            Button button = findViewById(R.id.submitSets);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (sets.getText().toString().isEmpty()) {
                        e.setSets(0);
                    } else {
                        e.setSets(Integer.valueOf(sets.getText().toString()));
                    }
                    if (reps.getText().toString().isEmpty()) {
                        e.setReps(0);
                    } else {
                        e.setReps(Integer.valueOf(reps.getText().toString()));
                    }

                    new AsyncUpdate().execute(e);
                    finish();
                }
            });
        }
    }

    /**
     * Listener to allow us adding the exercise to a routine
     */
    View.OnClickListener addToRoutine = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //don't run click button when coming from a routine
            if (fromRoutine == false) {
                Intent intent = new Intent(getApplicationContext(), AddExerciseToRoutine.class);
                intent.putExtra("exercise", e);
                intent.putExtra("id_exercise", id);
                intent.putExtra("name_exercise", name);
                intent.putExtra("description_exercise", description);
                startActivity(intent);
            }
        }
    };

    /**
     * AsyncTask to update the routine in the database
     */
    class AsyncUpdate extends AsyncTask<Exercise, Void, Exercise> {

        @Override
        protected Exercise doInBackground(Exercise... exercises) {
            AppRepository r = AppRepository.getInstance(ExerciseDescActivity.this);
            r.updateExercise(exercises[0]);
            return exercises[0];
        }

        @Override
        protected void onPostExecute(Exercise routine) {
            super.onPostExecute(routine);
        }
    }
}
