package com.unex.proyectoasee_nogymmembership;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.unex.proyectoasee_nogymmembership.Adapters.CategoryAdapter;
import com.unex.proyectoasee_nogymmembership.Adds.AddCategoryActivity;
import com.unex.proyectoasee_nogymmembership.Models.Category;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends Fragment {

    ListView listCategories;

    private static final int ADD_CATEGORY_REQUEST = 0;
    public static final int RESULT_OK = -1;

    private static final String TAG = "ListCategories";

    private CategoryAdapter mAdapter;

    public FragmentTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Inflate del layout para el fragment
        View view = inflater.inflate(R.layout.fragment_fragment_two, container, false);

        //Definimos la lista a partir del view inflated
        listCategories = (ListView) view.findViewById(R.id.listCategories2);



        //Array de prueba
        Category c1 = new Category("Category1", "Hard");
        ArrayList<Category> test = new ArrayList<Category>();
        test.add(c1);


        //Definimos nuestro adapter personalizado
        mAdapter = new CategoryAdapter(getContext(), test);


        listCategories.setAdapter(mAdapter);

        //Listener para elementos de la lista
        listCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i1 = new Intent(getContext(), RoutinesManagerActivity.class);
                startActivity(i1);

                Snackbar.make(view, "Seleccionando" + position, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Parte lógica del FAB para añadir categorias
        FloatingActionButton fab = view.findViewById(R.id.addCategory);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO - Definir funcionalidad para borrar categorias
                Intent i = new Intent(getContext(), AddCategoryActivity.class);
                startActivityForResult(i, ADD_CATEGORY_REQUEST);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        log("Entered onActivityResult()");

        // Cuando el usuario crea una nueva categoría, pasa la información a través de un intent
        // desde la activity popup_addcategory y la añade al adapter
        if (requestCode == ADD_CATEGORY_REQUEST){
            if (resultCode == RESULT_OK){
                Category item = new Category(data);
                mAdapter.add(item);
            }
        }

    }

    private void log(String msg) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, msg);
    }

}
