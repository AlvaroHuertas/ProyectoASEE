package com.unex.proyectoasee_nogymmembership;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.unex.proyectoasee_nogymmembership.Adapters.RoutineAdapter;
import com.unex.proyectoasee_nogymmembership.Adds.AddRoutineActivity;
import com.unex.proyectoasee_nogymmembership.Models.Routine;

public class RoutinesManagerActivity extends AppCompatActivity {

    private static final int ADD_TODO_ITEM_REQUEST = 0;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RoutineAdapter mAdapter;



    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routines_manager_activity);

        //TODO - ¿Toolbar?

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabroutines);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RoutinesManagerActivity.this, AddRoutineActivity.class);
                startActivityForResult(i, ADD_TODO_ITEM_REQUEST);
            }
        });

        //Obtenemos referencia a la RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.routines_recycler);

        //Este ajuste mejorará el desempeño de la RecyclerVew si sabemos que introduciendo
        //más contenido no cambiará el tamaño del layout
        mRecyclerView.setHasFixedSize(true);

        //Definimos un linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Definir el adapter para la RecyclerView
        mAdapter = new RoutineAdapter(null);

        //Asociaomos el adaptador a la recyclerView
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // TODO - Check result code and request code.
        // If user submitted a new ToDoItem
        // Create a new ToDoItem from the data Intent
        // and then add it to the adapter
        if (requestCode == ADD_TODO_ITEM_REQUEST){
            if (resultCode == RESULT_OK){
                Routine item = new Routine(data);
                mAdapter.add(item);
            }
        }

    }
    @Override
    public void onResume() {
        super.onResume();

        // Load saved ToDoItems, if necessary

        if (mAdapter.getItemCount() == 0)
            loadItems();
    }

    private void loadItems() {

        Toast toast = Toast.makeText(getApplicationContext(), "Loading", Toast.LENGTH_SHORT);
        toast.show();
    }
}
