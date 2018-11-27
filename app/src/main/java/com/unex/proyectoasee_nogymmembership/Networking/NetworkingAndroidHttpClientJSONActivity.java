package com.unex.proyectoasee_nogymmembership.Networking;

import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Pair;
import android.view.View;
import android.widget.ListAdapter;

import com.unex.proyectoasee_nogymmembership.Adapters.ExerciseAdapter;
import com.unex.proyectoasee_nogymmembership.Adapters.RoutineAdapter;
import com.unex.proyectoasee_nogymmembership.Models.Exercise;
import com.unex.proyectoasee_nogymmembership.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NetworkingAndroidHttpClientJSONActivity extends ListActivity {



    //Definición de claces JSON
    private static final String EXERCISE_ID_TAG = "results";
    private static final String DESCRIPTION_TAG = "description";
    private static final String NAME_TAG = "name";
    private static final String CATEGORY_TAG = "category";
    private static final String MUSCLES_TAG = "muscles";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView=findViewById(R.layout.fragment_fragment_two);
        RecyclerView mExercisesRecycler=(RecyclerView) findViewById(R.id.exercises_recycler);
        //Ejecutar petición GET
        new HttpGetTask(this,mExercisesRecycler).execute();
    }


    private class HttpGetTask extends AsyncTask<Void, Void, List<Exercise>>{
        private static final String BASE_URL ="wger.de";
        private static final String JSON_SEG = "api";
        private static final String JSON_SEG2 ="v2";
        private static final String JSON_SEG3 ="exercise";
        private static final String LANGUAGE_P = "language";
        private static final String EQUIPMENT_P = "equipment";

        private Context mContext;
        private RecyclerView mExercisesRecycler;
        private ExerciseAdapter mAdapter;
        private View rootView;

        public HttpGetTask(Context context, RecyclerView mExercisesRecycler){
            mContext=context;
            Layout inflater;
            this.mExercisesRecycler=mExercisesRecycler;


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


        protected void onPostExecute(List<Exercise> result) {
            LinearLayoutManager lay_Manager=new LinearLayoutManager(mContext);
            mExercisesRecycler.setLayoutManager(lay_Manager);
            mAdapter= new ExerciseAdapter(mContext,result);
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
