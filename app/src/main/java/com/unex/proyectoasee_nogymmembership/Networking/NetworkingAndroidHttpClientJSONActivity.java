package com.unex.proyectoasee_nogymmembership.Networking;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Pair;
import android.widget.ArrayAdapter;

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
        //Ejecutar petición GET
        new HttpGetTask().execute();
    }


    private class HttpGetTask extends AsyncTask<Void, Void, List<String>> {
        private static final String BASE_URL ="wger.de";
        private static final String JSON_SEG = "api";
        private static final String JSON_SEG2 ="v2";
        private static final String JSON_SEG3 ="exercise";
        private static final String LANGUAGE_P = "language";
        private static final String EQUIPMENT_P = "equipment";

        @Override
        protected List<String> doInBackground(Void... params) {
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

        protected void onPostExecute(List<String> result) {
           /*setListAdapter(new ArrayAdapter<String>(
                    NetworkingAndroidHttpClientJSONActivity.this,
                    R.layout.list_item, result));*/
        }

    }

    public List<String> jsonToList(JSONObject responseObject) {
        List<String> result = new ArrayList<String>();

        try {


            JSONArray exercises = responseObject
                    .getJSONArray(EXERCISE_ID_TAG);
            //TODO obtener todos los ejercicios y construir los objetos
            for (int idx = 0; idx < exercises.length(); idx++) {

                // Get single earthquake data - a Map
                JSONObject exercise = (JSONObject) exercises.get(idx);

                // Summarize earthquake data as a string and add it to
                // result
                result.add(NAME_TAG + ":"
                        + exercise.get(NAME_TAG) + ","
                        + DESCRIPTION_TAG + ":"
                        + exercise.getString(DESCRIPTION_TAG));
            }

        } catch (JSONException e) {
        e.printStackTrace();
        }
        return result;
    }
}
