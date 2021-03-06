package com.unex.proyectoasee_nogymmembership;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.unex.proyectoasee_nogymmembership.Adapters.ExerciseInRoutineAdapter;
import com.unex.proyectoasee_nogymmembership.Models.Exercise;
import com.unex.proyectoasee_nogymmembership.Models.ExerciseList;
import com.unex.proyectoasee_nogymmembership.Models.Routine;
import com.unex.proyectoasee_nogymmembership.Repository.AppRepository;

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
                intent.putExtra("Routine", routineItem);
                startActivityForResult(intent, MOD_ROUTINE_ITEM_REQUEST);
            }
        });
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

    class AsyncLoadRoutine extends AsyncTask<Long, Void, Routine> {
        @Override
        protected Routine doInBackground(Long... ints) {
            AppRepository r = AppRepository.getInstance(ShowRoutine.this);
            Routine item = r.getRoutine((int) ints[0].longValue());
            return item;
        }

        @Override
        protected void onPostExecute(Routine item) {
            super.onPostExecute(item);
            routineNameTextView.setText(item.getName());
            routineTypeTextView.setText(item.getType());
        }

    }

    /**
     * Create an AlertDialog to ask if we want to delete a routine or not
     *
     * @param item Exercise we are going to delete
     * @return Dialog
     */
    private AlertDialog AskOption(final Exercise item) {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(ShowRoutine.this)
                //set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Delete")
                .setIcon(R.drawable.ic_delete_name)

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        delete(item);
                        dialog.dismiss();
                    }

                })

                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        return myQuittingDialogBox;

    }

    /**
     * Removes an exercise from the list of exercises of the routine
     *
     * @param item
     */
    public void delete(Exercise item) {
        mAdapter.deleteItem(item);
        new AsyncDelete().execute(item);
    }

    /**
     * Load all the elements that we are going to show from the routine
     */
    public void setRoutineElements() {

        routineNameTextView = findViewById(R.id.routineNameTV);
        routineTypeTextView = findViewById(R.id.routineTypeTV);

        routineItem = (Routine) getIntent().getSerializableExtra(INTENT_OBJECT_EXTRA);

        routineNameTextView.setText(routineItem.getName());
        routineTypeTextView.setText(routineItem.getType());

        mRecyclerView = (RecyclerView) findViewById(R.id.exercises_recycler);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ExerciseInRoutineAdapter(ShowRoutine.this, item -> {
            AlertDialog diaBox = AskOption(item);
            diaBox.show();
        });

        new AsyncLoad().execute();
        mRecyclerView.setAdapter(mAdapter);


    }

    /**
     * AsyncTask to load al the exercises belonging to a routine from the database
     */
    class AsyncLoad extends AsyncTask<Void, Void, List<Exercise>> {
        @Override
        protected List<Exercise> doInBackground(Void... voids) {
            AppRepository r = AppRepository.getInstance(ShowRoutine.this);
            List<Exercise> items = r.getAllExercisesById(routineItem.getId());
            return items;
        }

        @Override
        protected void onPostExecute(List<Exercise> items) {
            super.onPostExecute(items);
            ExerciseList r = new ExerciseList(items);
            mAdapter.load(r);
        }

    }

    /**
     * AsyncTask to update the routine in the database
     */
    class AsyncUpdate extends AsyncTask<Routine, Void, Routine> {

        @Override
        protected Routine doInBackground(Routine... routines) {
            AppRepository r = AppRepository.getInstance(ShowRoutine.this);
            r.updateRoutine(routines[0]);
            return routines[0];
        }

        @Override
        protected void onPostExecute(Routine routine) {
            super.onPostExecute(routine);
            routineItem = routine;
        }
    }

    /**
     * AsyncTask to delete an exercise belonging to a routine from the database
     */
    class AsyncDelete extends AsyncTask<Exercise, Void, Void> {

        @Override
        protected Void doInBackground(Exercise... exercises) {
            AppRepository r = AppRepository.getInstance(ShowRoutine.this);
            r.deleteExercise(exercises[0]);
            return null;
        }
    }

}
