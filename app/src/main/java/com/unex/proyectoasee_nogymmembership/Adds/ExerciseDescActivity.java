package com.unex.proyectoasee_nogymmembership.Adds;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.unex.proyectoasee_nogymmembership.DBUtils.ExerciseCRUD;
import com.unex.proyectoasee_nogymmembership.Models.Exercise;
import com.unex.proyectoasee_nogymmembership.R;

public class ExerciseDescActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_desc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        int id=intent.getIntExtra("id_exercise",-1);

        ExerciseCRUD crud = ExerciseCRUD.getInstance(getApplicationContext());
        Exercise exercise=crud.getExercise(id);


        TextView name = findViewById(R.id.exercise_name_desc);
        TextView description= findViewById(R.id.exercise_description);

        name.setText(exercise.getName());
        description.setText(exercise.getDescription());

    }

}
