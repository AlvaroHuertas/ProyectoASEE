package com.unex.proyectoasee_nogymmembership;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
        listCategories = (ListView) view.findViewById(R.id.listCategories);

        //Utilizamos un array de prueba
        //TODO - Establecer este array a partir de un .xml que contenga las categorias
        ArrayList<String> test = new ArrayList<>();
        test.add("Hola");
        test.add("mundo");

        //Adapter para la lista
        ArrayAdapter adapt;
        adapt = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, test);
        listCategories.setAdapter(adapt);

        return view;
    }

}
