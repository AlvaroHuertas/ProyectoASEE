package com.unex.proyectoasee_nogymmembership;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.preference.PreferenceFragment;



public class SettingsFragment extends PreferenceFragment {
    public static final String KEY_PREF_NIGHTMODE = "pref_nightmode";
    public static final String KEY_PREF_USERNAME = "pref_username";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Cargar las preferencias del recurso XML
        addPreferencesFromResource(R.xml.preferences);

    }


}
