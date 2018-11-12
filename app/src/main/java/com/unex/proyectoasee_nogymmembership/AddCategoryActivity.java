package com.unex.proyectoasee_nogymmembership;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class AddCategoryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_addcategory);

        //Utilizamos la clase metrics para establecer el tamaño de la ventana pop-up
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        getWindow().setLayout((int)(width*0.9), (int)(height*0.6));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

        //Establecemos la funcionalidad del button
        Button submit = (Button) findViewById(R.id.buttonSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO - Añadir funcionalidad
            }
        });

    }
}
