package com.unex.proyectoasee_nogymmembership;


import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.unex.proyectoasee_nogymmembership.Adapters.ExerciseAdapter;
import com.unex.proyectoasee_nogymmembership.AppBarUtils.UserPreferences;
import com.unex.proyectoasee_nogymmembership.Models.ExerciseList;
import com.unex.proyectoasee_nogymmembership.Repository.AppRepository;
import com.unex.proyectoasee_nogymmembership.ViewModel.FragmentTwoViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends Fragment {

    ListView listCategories;

    private static final int ADD_CATEGORY_REQUEST = 0;
    public static final int RESULT_OK = -1;
    //Definición de clases JSON
    private static final String TAG = "FragmentTwo";
    private static final String BUNDLE_KEY = "ExercisesList";


    private static RecyclerView mExercisesRecycler = null;

    public ExerciseAdapter mAdapter;
    public ExerciseList exerciseList;
    public FragmentTwoViewModel mViewModel;


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

        mViewModel = ViewModelProviders.of(getActivity()).get(FragmentTwoViewModel.class);

        this.mExercisesRecycler = (RecyclerView) view.findViewById(R.id.exercises_recycler);

        LinearLayoutManager lay_Manager = new LinearLayoutManager(getActivity());
        mExercisesRecycler.setLayoutManager(lay_Manager);

        mAdapter = new ExerciseAdapter(getActivity(), exerciseList);

        mViewModel.getExerciseList().observe((LifecycleOwner) this, exList -> {
            // If the exerciseList forecast details change, update the UI
            if (exList != null) {
                if (exList.getElements().size() != 0) {
                    updateIU(exList);
                }
            }
        });

        if (haveNetwork()) {
            Log.v(TAG, "Loading exercises");
            loadItems();
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

    /**
     * Checks if the device currently has network connection
     *
     * @return True if the device has internet connection
     */
    private boolean haveNetwork() {
        boolean have_Wifi = false;
        boolean have_MobileData = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();

        for (NetworkInfo info : networkInfos) {
            if (info.getTypeName().equalsIgnoreCase("WIFI"))
                if (info.isConnected())
                    have_Wifi = true;
            if (info.getTypeName().equalsIgnoreCase("MOBILE"))
                if (info.isConnected())
                    have_MobileData = true;
        }
        return have_MobileData || have_Wifi;
    }

    /**
     * Load all the exercises from the api
     */
    private void loadItems() {
        new AsyncLoadExercises().execute();
    }

    /**
     * Updates the adapter with a new list of exercises
     * @param items Elements to be inserted in the data set of the adapter
     */
    public void updateIU(ExerciseList items) {
        mAdapter.load(items);
        mExercisesRecycler.setAdapter(mAdapter);
    }

    /**
     * AsyncTask to load all the exercises from the api
     */
    class AsyncLoadExercises extends AsyncTask<Void, Void, ExerciseList> {

        @Override
        protected ExerciseList doInBackground(Void... voids) {
            AppRepository r = AppRepository.getInstance(getContext());
            return new ExerciseList(r.getExercisesFromApi());
        }

        @Override
        protected void onPostExecute(ExerciseList items) {
            super.onPostExecute(items);
            mViewModel.setExerciseList(items);
        }
    }
}
