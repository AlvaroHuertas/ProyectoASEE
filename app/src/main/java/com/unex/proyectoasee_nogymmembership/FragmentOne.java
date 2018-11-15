package com.unex.proyectoasee_nogymmembership;


import android.content.Intent;
import android.os.Bundle;
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

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fabroutines);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // - Attach Listener to FloatingActionButton. Implement onClick()
                Intent intent = new Intent(getContext(), AddRoutineActivity.class);
                startActivityForResult(intent,ADD_ROUTINE_ITEM_REQUEST);
            }
        });

        //Obtenemos referencia a la RecyclerView
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.routines_recycler);

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
               Toast toast = Toast.makeText(getContext(), "Item " + item.getName() + " de tipo " + item.getType(), Toast.LENGTH_SHORT);
               toast.show();
            }
        });

        mRecyclerView.setAdapter(mAdapter);


        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        //  - Check result code and request code.
        // If user submitted a new Routine
        // Create a new Routine from the data Intent
        // and then add it to the adapter
        if (requestCode == ADD_ROUTINE_ITEM_REQUEST){
            if (resultCode == RESULT_OK){
                Routine item = new Routine(data);

                //insert into DB
                RoutineCRUD crud = RoutineCRUD.getInstance(getContext());
                long id = crud.insert(item);

                //update item ID
                item.setId(id);

                //insert into adapter list
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

    @Override
    public void onPause() {
        super.onPause();

        // ALTERNATIVE: Save all ToDoItems

    }

    @Override
    public void onDestroy() {
        RoutineCRUD crud = RoutineCRUD.getInstance(getContext());
        crud.close();
        super.onDestroy();
    }



    // Load stored Routines
    private void loadItems() {
        RoutineCRUD crud = RoutineCRUD.getInstance(getContext());
        List<Routine> items = crud.getAll();
        RoutineList routineItems = new RoutineList(items);
        mAdapter.load(routineItems);
    }

}
