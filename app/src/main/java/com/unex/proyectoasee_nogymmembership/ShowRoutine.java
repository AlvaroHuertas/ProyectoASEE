package com.unex.proyectoasee_nogymmembership;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.unex.proyectoasee_nogymmembership.Adapters.ExerciseAdapter;
import com.unex.proyectoasee_nogymmembership.DBUtils.DBContract;
import com.unex.proyectoasee_nogymmembership.Models.Exercise;
import com.unex.proyectoasee_nogymmembership.Models.ExerciseList;
import com.unex.proyectoasee_nogymmembership.Models.Routine;
import com.unex.proyectoasee_nogymmembership.Models.RoutineList;
import com.unex.proyectoasee_nogymmembership.RoomDB.AppDataBase;

import java.util.ArrayList;
import java.util.List;

public class ShowRoutine extends AppCompatActivity {

    private Routine routineItem;

    private TextView routineNameTextView;
    private TextView routineTypeTextView;

    private RecyclerView mRecyclerView;
    private ExerciseAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static String INTENT_OBJECT_EXTRA = "Routine";




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showroutine);
        setRoutineElements();
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

        mAdapter = new ExerciseAdapter(null);

        new AsyncLoad().execute();
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    public void onResume() {
        super.onResume();

        // Load saved ToDoItems, if necessary

        if (mAdapter.getItemCount() == 0){
            //loadItems();
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
}
