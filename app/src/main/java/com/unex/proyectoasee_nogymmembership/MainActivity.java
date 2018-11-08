package com.unex.proyectoasee_nogymmembership;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText weight, height;
    RadioGroup gender, level;
    RadioButton defaultGenderButton, defaultLevelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        Button enterButton = (Button) findViewById(R.id.buttonEnter);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPreferences();

                Intent start = new Intent(v.getContext(), RoutinesActivity.class);
                startActivity(start);

            }
        });
    }

    public void setPreferences(){
        int radioId;

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
        editor.putFloat("weight", Float.valueOf(weight.getText().toString()));
        editor.putFloat("height", Float.valueOf(height.getText().toString()));
        editor.putString("gender", defaultGenderButton.getText().toString());
        editor.putString("level", defaultLevelButton.getText().toString());


        //Prueba
        Toast toast = Toast.makeText(getApplicationContext(), preferences.getString("gender", "No info"), Toast.LENGTH_LONG);
        toast.show();

        editor.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
