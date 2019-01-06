package com.unex.proyectoasee_nogymmembership;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.unex.proyectoasee_nogymmembership.Adapters.ExerciseAdapter;
import com.unex.proyectoasee_nogymmembership.DBUtils.ExerciseCRUD;
import com.unex.proyectoasee_nogymmembership.Models.Exercise;
import com.unex.proyectoasee_nogymmembership.Models.ExerciseList;
import com.unex.proyectoasee_nogymmembership.Networking.NetworkUtils;
import com.unex.proyectoasee_nogymmembership.Networking.NetworkingAndroidHttpClientJSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends Fragment {

    ListView listCategories;

    private static final int ADD_CATEGORY_REQUEST = 0;
    public static final int RESULT_OK = -1;
    //Definición de clases JSON
    private static final String EXERCISE_TAG = "results";
    private static final String DESCRIPTION_TAG = "description";
    private static final String NAME_TAG = "name";
    private static final String CATEGORY_TAG = "category";
    private static final String MUSCLES_TAG = "muscles";
    private static final String IMAGE_TAG = "image";



    private static RecyclerView mExercisesRecycler=null;

    ExerciseAdapter mAdapter;
    ExerciseList exerciseList;


    public FragmentTwo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Inflate del layout para el fragment
        View rootView = inflater.inflate(R.layout.fragment_fragment_two, container, false);
       this.mExercisesRecycler=(RecyclerView) rootView.findViewById(R.id.exercises_recycler);

        //Ejecutar petición GET
        NetworkingAndroidHttpClientJSON petition=new NetworkingAndroidHttpClientJSON();

        this.exerciseList=petition.getExerciseList();

        LinearLayoutManager lay_Manager = new LinearLayoutManager(getActivity());
        mExercisesRecycler.setLayoutManager(lay_Manager);

        mAdapter = new ExerciseAdapter(getActivity(), exerciseList);
        mExercisesRecycler.setAdapter(mAdapter);

        return rootView;
    }

}
