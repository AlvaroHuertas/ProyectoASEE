package com.unex.proyectoasee_nogymmembership;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.unex.proyectoasee_nogymmembership.Adapters.CategoryAdapter;
import com.unex.proyectoasee_nogymmembership.Models.Category;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {

    ListView listCategories;

    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Inflate del layout para el fragment
        View view = inflater.inflate(R.layout.fragment_fragment_one, container, false);

        //Definimos la lista a partir del view inflated
        listCategories = (ListView) view.findViewById(R.id.listCategories1);

        //TODO - Categorias basadas en nivel dependen del nivel establecido en preferencias
        Category c1 = new Category("Rutinas basadas en nivel", "Medium");
        Category c2 = new Category("Rutinas enfocadas a movimientos", "Medium");
        //Utilizamos un array de prueba
        //TODO - Establecer este array a partir de un .xml que contenga las categorias
        ArrayList<Category> test = new ArrayList<Category>();
        test.add(c1);
        test.add(c2);

        //Adapter para la lista

        CategoryAdapter adapter = new CategoryAdapter(getContext(), test);
        listCategories.setAdapter(adapter);

        return view;
    }

}
