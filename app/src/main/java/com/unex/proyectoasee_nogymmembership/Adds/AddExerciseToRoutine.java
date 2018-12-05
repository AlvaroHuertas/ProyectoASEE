package com.unex.proyectoasee_nogymmembership.Adds;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.unex.proyectoasee_nogymmembership.Adapters.RoutineAdapter;
import com.unex.proyectoasee_nogymmembership.Adapters.RoutineListAdapter;
import com.unex.proyectoasee_nogymmembership.DBUtils.RoutineCRUD;
import com.unex.proyectoasee_nogymmembership.Models.Routine;
import com.unex.proyectoasee_nogymmembership.Models.RoutineList;
import com.unex.proyectoasee_nogymmembership.R;

import java.util.List;

public class AddExerciseToRoutine extends AppCompatActivity implements RoutineListAdapter.CallBack{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ex_to_routine);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        int id=intent.getIntExtra("id_exercise",-1);

        mRecyclerView = (RecyclerView) findViewById(R.id.routines_list_recycler);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        RoutineList routineList=new RoutineList();
        List<Routine> aux=routineList.getElements();
        RoutineCRUD crud = RoutineCRUD.getInstance(getApplicationContext());
        aux=crud.getAll();
        routineList.setElements(aux);
        mAdapter = new RoutineListAdapter(this,routineList,AddExerciseToRoutine.this);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onItemClicked(int position) {
        RoutineCRUD routineCRUD = RoutineCRUD.getInstance(getApplicationContext());

    }
}
