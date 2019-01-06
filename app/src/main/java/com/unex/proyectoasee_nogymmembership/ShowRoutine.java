package com.unex.proyectoasee_nogymmembership;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.unex.proyectoasee_nogymmembership.Adapters.ExerciseAdapter;
import com.unex.proyectoasee_nogymmembership.Adapters.ExerciseInRoutineAdapter;
import com.unex.proyectoasee_nogymmembership.Adds.AddRoutineActivity;
import com.unex.proyectoasee_nogymmembership.DBUtils.DBContract;
import com.unex.proyectoasee_nogymmembership.Models.Exercise;
import com.unex.proyectoasee_nogymmembership.Models.ExerciseList;
import com.unex.proyectoasee_nogymmembership.Models.Routine;
import com.unex.proyectoasee_nogymmembership.Models.RoutineList;
import com.unex.proyectoasee_nogymmembership.Repository.AppRepository;
import com.unex.proyectoasee_nogymmembership.RoomDB.AppDataBase;

import java.util.List;
import com.unex.proyectoasee_nogymmembership.Models.RoutineList;
import com.unex.proyectoasee_nogymmembership.RoomDB.AppDataBase;

import java.util.ArrayList;
import java.util.List;

public class ShowRoutine extends AppCompatActivity {

    private Routine routineItem;

    private TextView routineNameTextView;
    private TextView routineTypeTextView;

    private RecyclerView mRecyclerView;
    private ExerciseInRoutineAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static String INTENT_OBJECT_EXTRA = "Routine";
    private static final int MOD_ROUTINE_ITEM_REQUEST = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showroutine);

        ActionBar ab = getSupportActionBar();

        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_mod_routine);

        setRoutineElements();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowRoutine.this, ModRoutineActivity.class);
                intent.putExtra("Routine",routineItem);
                startActivityForResult(intent, MOD_ROUTINE_ITEM_REQUEST);
            }
        });
    }

    public void setRoutineElements(){

        routineNameTextView = findViewById(R.id.routineNameTV);
        routineTypeTextView = findViewById(R.id.routineTypeTV);

        routineItem = (Routine) getIntent().getSerializableExtra(INTENT_OBJECT_EXTRA);

        routineNameTextView.setText(routineItem.getName());
        routineTypeTextView.setText(routineItem.getType());

        mRecyclerView = (RecyclerView) findViewById(R.id.exercises_recycler);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ExerciseInRoutineAdapter(ShowRoutine.this);

        new AsyncLoad().execute();
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MOD_ROUTINE_ITEM_REQUEST) {
            if (resultCode == RESULT_OK) {
                Routine routine = new Routine(data);
                new AsyncUpdate().execute((Routine) data.getSerializableExtra("Routine"));
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        new AsyncLoadRoutine().execute(routineItem.getId());
    }

    class AsyncLoadRoutine extends AsyncTask<Long,Void,Routine> {
        @Override
        protected Routine doInBackground(Long... ints) {
            AppDataBase appDB = AppDataBase.getDataBase(ShowRoutine.this);
            Routine item = appDB.routineDAO().getRoutine((int)ints[0].longValue());
            return item;
        }

        @Override
        protected void onPostExecute(Routine item){
            super.onPostExecute(item);
            routineNameTextView.setText(item.getName());
            routineTypeTextView.setText(item.getType());
        }

    }

    class AsyncLoad extends AsyncTask<Void, Void, List<Exercise>> {
        @Override
        protected List<Exercise> doInBackground(Void... voids) {

            AppDataBase appDB = AppDataBase.getDataBase(ShowRoutine.this);
            List<Exercise> items = new ArrayList<>();
            List<Exercise> aux = appDB.exerciseDAO().getAll();

            if(aux.size()!=0) {
                for (int i = 0; i < aux.size(); i++) {
                    if (aux.get(i).getRoutineId() == routineItem.getId()) {
                        items.add(aux.get(i));
                    }
                }
            }

            return items;
        }

        @Override
        protected void onPostExecute(List<Exercise> items){
            super.onPostExecute(items);
            ExerciseList r = new ExerciseList(items);
            mAdapter.load(r);
        }

    }

    class AsyncUpdate extends AsyncTask<Routine, Void, Routine> {

        @Override
        protected Routine doInBackground(Routine... routines) {
            AppRepository r = AppRepository.getInstance(ShowRoutine.this);
            r.updateRoutine(routines[0]);
            return routines[0];
        }

        @Override
        protected void onPostExecute(Routine routine){
            super.onPostExecute(routine);
            routineItem = routine;
        }
    }


}
