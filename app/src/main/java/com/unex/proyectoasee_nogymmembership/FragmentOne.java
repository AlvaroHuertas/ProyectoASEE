package com.unex.proyectoasee_nogymmembership;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.unex.proyectoasee_nogymmembership.Adapters.RoutineAdapter;
import com.unex.proyectoasee_nogymmembership.Adds.AddRoutineActivity;
import com.unex.proyectoasee_nogymmembership.DBUtils.RoutineCRUD;
import com.unex.proyectoasee_nogymmembership.Models.Routine;
import com.unex.proyectoasee_nogymmembership.Models.RoutineList;
import com.unex.proyectoasee_nogymmembership.RoomDB.AppDataBase;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {


    // Add a Routine Request Code
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
                startActivityForResult(intent,ADD_ROUTINE_ITEM_REQUEST);
            }
        });

        //Obtenemos referencia a la RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.routines_recycler);

        //Este ajuste mejorará el desempeño de la RecyclerVew si sabemos que introduciendo
        //más contenido no cambiará el tamaño del layout
        mRecyclerView.setHasFixedSize(true);

        //Definimos un linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);


        //Definir el adapter para la RecyclerView
        mAdapter = new RoutineAdapter(getContext(), new RoutineAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Routine item) {
                Intent intent = new Intent(getContext(), ShowRoutine.class);
                intent.putExtra("Routine", item);
                startActivity(intent);
            }
        });

        mRecyclerView.setAdapter(mAdapter);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        //  - Check result code and request code.
        // If user submitted a new Routine
        // Create a new Routine from the data Intent
        // and then add it to the adapter
        if (requestCode == ADD_ROUTINE_ITEM_REQUEST){
            if (resultCode == RESULT_OK){
                Routine routine = new Routine(data);

                new AsyncInsert().execute(routine);
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

    @Override
    public void onPause() {
        super.onPause();

        // ALTERNATIVE: Save all ToDoItems

        }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }



    // Load stored Routines
    private void loadItems() {
     new AsyncLoad().execute();
    }

    class AsyncLoad extends AsyncTask<Void, Void, List<Routine>>{
        @Override
        protected List<Routine> doInBackground(Void... voids) {
            AppDataBase appDB = AppDataBase.getDataBase(getContext());
            List<Routine> items = appDB.routineDAO().getAll();
            return items;
        }

        @Override
        protected void onPostExecute(List<Routine> items){
            super.onPostExecute(items);
            RoutineList r = new RoutineList(items);
            mAdapter.load(r);
        }

    }

    class AsyncInsert extends AsyncTask<Routine, Void, Routine>{

        @Override
        protected Routine doInBackground(Routine... routines) {
            AppDataBase appDB = AppDataBase.getDataBase(getContext());
            long id = appDB.routineDAO().insert(routines[0]);

            routines[0].setId(id);

            return routines[0];
        }

        @Override
        protected void onPostExecute(Routine routine){
            super.onPostExecute(routine);
            mAdapter.add(routine);
        }
    }

}