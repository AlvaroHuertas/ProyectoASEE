package com.unex.proyectoasee_nogymmembership;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.unex.proyectoasee_nogymmembership.Models.Routine;

public class ModRoutineActivity extends Activity {

    private Routine routineItem;

    private EditText name;
    private Spinner type;

    public static String INTENT_OBJECT_EXTRA = "Routine";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mod_routine);

        routineItem = (Routine) getIntent().getSerializableExtra(INTENT_OBJECT_EXTRA);

        name = (EditText) findViewById(R.id.routine_name_mod);
        type = (Spinner) findViewById(R.id.routine_type_mod);

        name.setText(routineItem.getName());

        ArrayAdapter myAdap = (ArrayAdapter) type.getAdapter(); //cast to an ArrayAdapter

        int spinnerPosition = myAdap.getPosition(routineItem.getType());

        //set the default according to value
        type.setSelection(spinnerPosition);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*0.8), (int)(height*0.6));
        getWindow().setElevation(10);

        final Button discardButton = (Button) findViewById(R.id.discardButton_mod);
        discardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final Button submitButton = (Button) findViewById(R.id.submitButton_mod);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameRoutine = (String) name.getText().toString();
                String typeRoutine = (String) type.getSelectedItem();

                routineItem.setName(nameRoutine);
                routineItem.setType(typeRoutine);

                Intent data = new Intent();
                data.putExtra("Routine", routineItem);

                setResult(RESULT_OK, data);
                finish();
            }
        });
     }

}
