package com.unex.proyectoasee_nogymmembership;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.unex.proyectoasee_nogymmembership.Adapters.RoutineAdapter;
import com.unex.proyectoasee_nogymmembership.Adds.AddRoutineActivity;
import com.unex.proyectoasee_nogymmembership.AppBarUtils.UserPreferences;
import com.unex.proyectoasee_nogymmembership.Models.Routine;
import com.unex.proyectoasee_nogymmembership.Models.RoutineList;
import com.unex.proyectoasee_nogymmembership.Repository.AppRepository;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {

    private static final int ADD_ROUTINE_ITEM_REQUEST = 0;
    public static final int RESULT_OK = -1;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RoutineAdapter mAdapter;

    public FragmentOne() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_fragment_one, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fabroutines);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // - Attach Listener to FloatingActionButton. Implement onClick()
                Intent intent = new Intent(getContext(), AddRoutineActivity.class);
                startActivityForResult(intent, ADD_ROUTINE_ITEM_REQUEST);
            }
        });

        mRecyclerView = (RecyclerView) view.findViewById(R.id.routines_recycler);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RoutineAdapter(getContext(), item -> {
            Intent intent = new Intent(getContext(), ShowRoutine.class);
            intent.putExtra("Routine", item);
            startActivity(intent);
        }, item -> {
            AlertDialog diaBox = AskOption(item);
            diaBox.show();
        });

        mRecyclerView.setAdapter(mAdapter);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_ROUTINE_ITEM_REQUEST) {
            if (resultCode == RESULT_OK) {
                Routine routine = new Routine(data);
                new AsyncInsert().execute(routine);
            }
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mAdapter.getFilter().filter(s);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_preferences) {
            Intent intent = new Intent(getContext(), UserPreferences.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadItems();
    }

    /**
     * Deletes a routine in the adapter as well as in the database
     *
     * @param routine Routine to be removed
     */
    public void delete(Routine routine) {
        mAdapter.deleteItem(routine);
        new AsyncDeleteRoutine().execute(routine);
    }

    /**
     * Create an AlertDialog to ask if we want to delete a routine or not
     *
     * @param routine Routine we are going to delete
     * @return Dialog
     */
    private AlertDialog AskOption(final Routine routine) {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(getContext())
                //set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Delete")
                .setIcon(R.drawable.ic_delete_name)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        delete(routine);
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
     * Load all the routines from the database
     */
    private void loadItems() {
        new AsyncLoad().execute();
    }

    /**
     * AsyncTask to load all the routines from the database
     */
    class AsyncLoad extends AsyncTask<Void, Void, List<Routine>> {
        @Override
        protected List<Routine> doInBackground(Void... voids) {
            AppRepository r = AppRepository.getInstance(getContext());
            List<Routine> items = r.getAllRoutines();
            return items;
        }

        @Override
        protected void onPostExecute(List<Routine> items) {
            super.onPostExecute(items);
            RoutineList r = new RoutineList(items);
            mAdapter.load(r);
        }

    }

    /**
     * AsyncTask to insert a routine in the database and update the status of the adapter
     */
    class AsyncInsert extends AsyncTask<Routine, Void, Routine> {

        @Override
        protected Routine doInBackground(Routine... routines) {
            AppRepository r = AppRepository.getInstance(getContext());
            long id = r.addRoutine(routines[0]);
            routines[0].setId(id);
            return routines[0];
        }

        @Override
        protected void onPostExecute(Routine routine) {
            super.onPostExecute(routine);
            mAdapter.add(routine);
        }
    }


    /**
     * Asynctask to delete a routine from the database
     */
    class AsyncDeleteRoutine extends AsyncTask<Routine, Void, Void> {

        @Override
        protected Void doInBackground(Routine... routines) {
            AppRepository r = AppRepository.getInstance(getContext());
            r.deleteRoutine(routines[0]);
            return null;
        }
    }


}