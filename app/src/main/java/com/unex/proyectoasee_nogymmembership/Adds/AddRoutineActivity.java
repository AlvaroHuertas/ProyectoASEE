package com.unex.proyectoasee_nogymmembership.Adds;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.unex.proyectoasee_nogymmembership.Models.Routine;
import com.unex.proyectoasee_nogymmembership.R;


public class AddRoutineActivity extends AppCompatActivity {

    private EditText mNameText;
    private EditText mType;
    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_routine);

        mNameText = (EditText) findViewById(R.id.name);
        mType = (EditText) findViewById(R.id.type);


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
                mType.setText("");
            }
        });

        final Button submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO -  Title
                String nameString = mNameText.getText().toString();

                // Date
                String typeString = mType.getText().toString();

                Intent data = new Intent();
                Routine.packageIntent(data, nameString, typeString);

                //TODO - return data Intent and finish
                setResult(RESULT_OK, data);
                finish();

            }
        });


    }

}
