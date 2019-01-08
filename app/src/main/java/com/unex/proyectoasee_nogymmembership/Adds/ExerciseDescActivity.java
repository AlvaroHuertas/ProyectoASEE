package com.unex.proyectoasee_nogymmembership.Adds;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.unex.proyectoasee_nogymmembership.R;
import com.unex.proyectoasee_nogymmembership.ViewModel.ExerciseDescActivityViewModel;

public class ExerciseDescActivity extends AppCompatActivity {
    private int id;
    private String name;
    private String description;
    private AppCompatDelegate mDelegate;
    private ExerciseDescActivityViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        id=intent.getIntExtra("id_exercise",-1);

        mViewModel = ViewModelProviders.of(this).get(ExerciseDescActivityViewModel.class);

        if(id!=-1) {
            setContentView(R.layout.exercise_desc);
        }else{
            setContentView(R.layout.exercise_desc_in_rout);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);


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
        //don't run click button when coming from a routine
        if(id!=-1) {
            Button button = findViewById(R.id.exercise_button);
            button.setOnClickListener(addToRoutine);
        }
    }

    View.OnClickListener addToRoutine =new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            //don't run click button when coming from a routine
            if (id != -1) {
                Intent intent = new Intent(getApplicationContext(), AddExerciseToRoutine.class);
                intent.putExtra("id_exercise", id);
                intent.putExtra("name_exercise", name);
                intent.putExtra("description_exercise", description);
                startActivity(intent);
            }
        }
    };

}
