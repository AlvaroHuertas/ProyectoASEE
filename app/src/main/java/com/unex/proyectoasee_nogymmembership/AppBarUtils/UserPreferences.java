package com.unex.proyectoasee_nogymmembership.AppBarUtils;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.unex.proyectoasee_nogymmembership.R;

public class UserPreferences extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpreferences);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        final Button doneButton = (Button) findViewById(R.id.buttonDone);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPreferences();

                finish();
            }
        });


    }

    public void setPreferences() {
        int radioId;
        EditText weight, height;
        RadioGroup gender, level;
        RadioButton defaultGenderButton, defaultLevelButton;

        //Recuperamos la información introducida sobre peso y altura
        weight = (EditText) findViewById(R.id.weight);
        height = (EditText) findViewById(R.id.height);


        //Recuperamos el género y el nivel seleccionado
        gender = (RadioGroup) findViewById(R.id.selectionGender);
        radioId = gender.getCheckedRadioButtonId();
        defaultGenderButton = (RadioButton) findViewById(radioId);
        level = (RadioGroup) findViewById(R.id.selectionLevel);
        radioId = level.getCheckedRadioButtonId();
        defaultLevelButton = (RadioButton) findViewById(radioId);

        //Fichero donde vamos a guardar las preferencias
        SharedPreferences preferences = getSharedPreferences("atributos", getApplicationContext().MODE_PRIVATE);

        //Creamos el editor para modificar el fichero. Se trata de un fichero clave-valor
        SharedPreferences.Editor editor = preferences.edit();

        if (!weight.getText().toString().isEmpty()) {
            editor.putFloat("weight", Float.valueOf(weight.getText().toString()));
        } else {
            editor.putFloat("weight", Float.valueOf(R.string.defaultWeight));
        }
        if (!height.getText().toString().isEmpty()) {
            editor.putFloat("height", Float.valueOf(height.getText().toString()));
        }else{
            editor.putFloat("weight", Float.valueOf(R.string.defaultHeight));
        }
            editor.putString("gender", defaultGenderButton.getText().toString());
            editor.putString("level", defaultLevelButton.getText().toString());

            editor.commit();
        }
    }
