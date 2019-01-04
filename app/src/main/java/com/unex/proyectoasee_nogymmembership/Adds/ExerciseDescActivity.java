package com.unex.proyectoasee_nogymmembership.Adds;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.unex.proyectoasee_nogymmembership.Adapters.RoutineListAdapter;
import com.unex.proyectoasee_nogymmembership.DBUtils.ExerciseCRUD;
import com.unex.proyectoasee_nogymmembership.DBUtils.RoutineCRUD;
import com.unex.proyectoasee_nogymmembership.Models.Exercise;
import com.unex.proyectoasee_nogymmembership.R;

public class ExerciseDescActivity extends AppCompatActivity{
    private int id;
    private String name;
    private String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_desc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        id=intent.getIntExtra("id_exercise",-1);
        name=intent.getStringExtra("name_exercise");
        description=intent.getStringExtra("description_exercise");

        TextView name = findViewById(R.id.exercise_name_desc);
        TextView description= findViewById(R.id.exercise_description);

        name.setText(this.name);
        description.setText(this.description);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Button button = findViewById(R.id.exercise_button);
        button.setOnClickListener(addToRoutine);
    }

    View.OnClickListener addToRoutine =new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), AddExerciseToRoutine.class);
            intent.putExtra("id_exercise",id);
            intent.putExtra("name_exercise",name);
            intent.putExtra("description_exercise",description);
            startActivity(intent);
        }
    };


}
