package com.unex.proyectoasee_nogymmembership;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.unex.proyectoasee_nogymmembership.Adapters.ExerciseAdapter;
import com.unex.proyectoasee_nogymmembership.Adapters.RoutineAdapter;
import com.unex.proyectoasee_nogymmembership.Models.Exercise;
import com.unex.proyectoasee_nogymmembership.Models.ExerciseList;
import com.unex.proyectoasee_nogymmembership.Networking.NetworkUtils;
import com.unex.proyectoasee_nogymmembership.Networking.NetworkingAndroidHttpClientJSONActivity;

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
    //Definición de claces JSON
    private static final String EXERCISE_ID_TAG = "results";
    private static final String DESCRIPTION_TAG = "description";
    private static final String NAME_TAG = "name";
    private static final String CATEGORY_TAG = "category";
    private static final String MUSCLES_TAG = "muscles";
    private static RecyclerView mExercisesRecycler=null;

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
        new HttpGetTask(getActivity()).execute();


        return rootView;
    }


    private class HttpGetTask extends AsyncTask<Void, Void, List<Exercise>> {
        private static final String BASE_URL ="wger.de";
        private static final String JSON_SEG = "api";
        private static final String JSON_SEG2 ="v2";
        private static final String JSON_SEG3 ="exercise";
        private static final String LANGUAGE_P = "language";
        private static final String EQUIPMENT_P = "equipment";

        private Context mContext;

        private ExerciseAdapter mAdapter;
        private View rootView;

        private ExerciseList exerciseList;

        public HttpGetTask(Context context){
            mContext=context;
        }

        @Override
        protected List<Exercise> doInBackground(Void... params) {
            URL queryURL;
            JSONObject result;

            //Construir URI con los campos definidos arriba
            queryURL = NetworkUtils.buildURL(BASE_URL,
                    new String[]{JSON_SEG,JSON_SEG2,JSON_SEG3},
                    new Pair(LANGUAGE_P, "2"),
                    new Pair(EQUIPMENT_P, "7"));
            result = NetworkUtils.getJSONResponse(queryURL);
            if(result != null)
                return jsonToList(result);

            return null;
        }

        @Override
        protected void onPostExecute(List<Exercise> result) {
            LinearLayoutManager lay_Manager=new LinearLayoutManager(mContext);
            mExercisesRecycler.setLayoutManager(lay_Manager);
            exerciseList = new ExerciseList(result);
            mAdapter= new ExerciseAdapter(mContext,exerciseList);
            mExercisesRecycler.setAdapter(mAdapter);

        }

    }

    public List<Exercise> jsonToList(JSONObject responseObject) {
        List<Exercise> result = new ArrayList<>();

        try {


            JSONArray exercises = responseObject
                    .getJSONArray(EXERCISE_ID_TAG);
            //TODO obtener todos los ejercicios y construir los objetos
            for (int idx = 0; idx < exercises.length(); idx++) {

                // Get single exercise data - a Map
                JSONObject exercise = (JSONObject) exercises.get(idx);

                Exercise exerciseObj= new Exercise(exercise.get(NAME_TAG).toString(),exercise.get(DESCRIPTION_TAG).toString(),"","");

                result.add(exerciseObj);

                // Summarize exercise data as a string and add it to
                // result
                /*result.add(NAME_TAG + ":"
                        + exercise.get(NAME_TAG) + ","
                        + DESCRIPTION_TAG + ":"
                        + exercise.getString(DESCRIPTION_TAG));*/
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }



}
