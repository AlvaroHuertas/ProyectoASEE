package com.unex.proyectoasee_nogymmembership;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.unex.proyectoasee_nogymmembership.DBUtils.DBContract;
import com.unex.proyectoasee_nogymmembership.Models.Routine;

public class ShowRoutine extends AppCompatActivity {

    Routine routineItem;

    TextView routineNameTextView;
    TextView routineTypeTextView;

    String INTENT_OBJECT_EXTRA = "Routine";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showroutine);

        routineNameTextView = findViewById(R.id.routineNameTV);
        routineTypeTextView = findViewById(R.id.routineTypeTV);

        routineItem = (Routine) getIntent().getSerializableExtra(INTENT_OBJECT_EXTRA);

        routineNameTextView.setText(routineItem.getName());
        routineTypeTextView.setText(routineItem.getType());


    }
}
