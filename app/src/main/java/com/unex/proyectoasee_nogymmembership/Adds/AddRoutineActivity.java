package com.unex.proyectoasee_nogymmembership.Adds;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.unex.proyectoasee_nogymmembership.Models.Routine;
import com.unex.proyectoasee_nogymmembership.R;


public class AddRoutineActivity extends AppCompatActivity {

    private EditText mNameText;
    private Spinner mType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_routine);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        mNameText = (EditText) findViewById(R.id.routine_name_add);
        mType = (Spinner) findViewById(R.id.routine_type_add);

        final Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //- Implement onClick().
                Intent data = new Intent();
                setResult(RESULT_CANCELED, data);
                finish();

            }
        });

        final Button resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //- Reset data fields to default values
                mNameText.setText("");
                mType.setSelection(0);
            }
        });

        final Button submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameString = mNameText.getText().toString();

                String typeString = mType.getSelectedItem().toString();

                Intent data = new Intent();
                Routine.packageIntent(data, nameString, typeString);

                setResult(RESULT_OK, data);
                finish();

            }
        });


    }

}
