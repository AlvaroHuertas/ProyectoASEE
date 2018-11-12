package com.unex.proyectoasee_nogymmembership;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.unex.proyectoasee_nogymmembership.Adapters.CategoryAdapter;
import com.unex.proyectoasee_nogymmembership.Models.Category;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends Fragment {

    ListView listCategories;

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
        CategoryAdapter adapter = new CategoryAdapter(getContext(), test);

        listCategories.setAdapter(adapter);

        //Parte lógica del FAB para añadir categorias
        FloatingActionButton fab = view.findViewById(R.id.addCategory);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Añadiendo movidas", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent i = new Intent(getContext(), AddCategoryActivity.class);
                startActivity(i);
            }
        });

        return view;
    }

}
