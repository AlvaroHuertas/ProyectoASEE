package com.unex.proyectoasee_nogymmembership;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.unex.proyectoasee_nogymmembership.Adapters.ExerciseAdapter;
import com.unex.proyectoasee_nogymmembership.AppBarUtils.UserPreferences;
import com.unex.proyectoasee_nogymmembership.DBUtils.ExerciseCRUD;
import com.unex.proyectoasee_nogymmembership.Models.Exercise;
import com.unex.proyectoasee_nogymmembership.Models.ExerciseList;
import com.unex.proyectoasee_nogymmembership.Networking.NetworkUtils;
import com.unex.proyectoasee_nogymmembership.Networking.NetworkingAndroidHttpClientJSON;
import com.unex.proyectoasee_nogymmembership.Repository.AppRepository;

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


    private static RecyclerView mExercisesRecycler = null;

    public ExerciseAdapter mAdapter;
    public ExerciseList exerciseList;


    public FragmentTwo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_fragment_two, container, false);
        this.exerciseList = new ExerciseList();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mExercisesRecycler = (RecyclerView) view.findViewById(R.id.exercises_recycler);

        new AsynLoadExercises().execute();

        LinearLayoutManager lay_Manager = new LinearLayoutManager(getActivity());
        mExercisesRecycler.setLayoutManager(lay_Manager);

        mAdapter = new ExerciseAdapter(getActivity(), exerciseList);
        mExercisesRecycler.setAdapter(mAdapter);
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

    class AsynLoadExercises extends AsyncTask<Void, Void, ExerciseList>{

        @Override
        protected ExerciseList doInBackground(Void... voids) {
            AppRepository r = AppRepository.getInstance(getContext());
            return new ExerciseList(r.getExercisesFromApi());
        }

        @Override
        protected void onPostExecute(ExerciseList items){
            super.onPostExecute(items);
            exerciseList = items;
        }
    }
}
