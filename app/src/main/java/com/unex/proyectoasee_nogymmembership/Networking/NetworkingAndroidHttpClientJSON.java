package com.unex.proyectoasee_nogymmembership.Networking;

import android.os.AsyncTask;
import android.util.Pair;
import android.view.View;

import com.unex.proyectoasee_nogymmembership.Models.Exercise;
import com.unex.proyectoasee_nogymmembership.Models.ExerciseList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NetworkingAndroidHttpClientJSON {

    //Definición de clases JSON
    private static final String EXERCISE_TAG = "results";
    private static final String DESCRIPTION_TAG = "description";
    private static final String NAME_TAG = "name";
    private static final String CATEGORY_TAG = "category";
    private static final String MUSCLES_TAG = "muscles";
    private static final String IMAGE_TAG = "image";
    private ExerciseList exerciseList;

    public NetworkingAndroidHttpClientJSON() {
        HttpGetTask httpGetTask = new HttpGetTask();
        httpGetTask.execute();
        this.exerciseList = httpGetTask.getExerciseList();
    }

    public ExerciseList getExerciseList() {
        return exerciseList;
    }

    private class HttpGetTask extends AsyncTask<Void, Void, ExerciseList> {
        private static final String BASE_URL = "wger.de";
        private static final String JSON_SEG = "api";
        private static final String JSON_SEG2 = "v2";
        private static final String JSON_SEG_EX = "exercise";
        private static final String JSON_SEG_IMG = "exerciseimage";
        private static final String LANGUAGE_P = "language";
        private static final String EQUIPMENT_P = "equipment";
        private static final String IMAGE = "image";
        private ExerciseList exerciseList;

        private View rootView;

        public HttpGetTask() {
            this.exerciseList = new ExerciseList();
        }

        public ExerciseList getExerciseList() {
            //Wait until exercises are load
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return exerciseList;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            exerciseList = new ExerciseList();
        }

        @Override
        protected ExerciseList doInBackground(Void... params) {
            URL queryURL;
            JSONObject[] result = new JSONObject[2];

            //Construir URI de EJERCICIOS con los campos definidos arriba
            queryURL = NetworkUtils.buildURL(BASE_URL,
                    new String[]{JSON_SEG, JSON_SEG2, JSON_SEG_EX},
                    new Pair(LANGUAGE_P, "2"),
                    new Pair(EQUIPMENT_P, "7"));
            result[0] = NetworkUtils.getJSONResponse(queryURL);


            //Construir URI de IMAGENES
            queryURL = NetworkUtils.buildURL(BASE_URL,
                    new String[]{JSON_SEG, JSON_SEG2, JSON_SEG_IMG});
            result[1] = NetworkUtils.getJSONResponse(queryURL);

            if (result != null)
                return new ExerciseList(jsonToList(result));

            return null;
        }

        @Override
        protected void onPostExecute(ExerciseList exerciseList) {
            this.exerciseList = exerciseList;
        }

        public List<Exercise> jsonToList(JSONObject[] responseObject) {
            List<Exercise> exercisesList = new ArrayList<>();
            try {

                if (responseObject[0] != null) {
                    JSONArray exercises = responseObject[0]
                            .getJSONArray(EXERCISE_TAG);
                    for (int idx = 0; idx < exercises.length(); idx++) {
                        // Get single exercise data - a Map
                        JSONObject exercise = (JSONObject) exercises.get(idx);
                        Exercise exerciseObj = new Exercise(idx, exercise.get(NAME_TAG).toString(), exercise.get(DESCRIPTION_TAG).toString(), "");
                        exercisesList.add(exerciseObj);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return exercisesList;
        }

    }

}
