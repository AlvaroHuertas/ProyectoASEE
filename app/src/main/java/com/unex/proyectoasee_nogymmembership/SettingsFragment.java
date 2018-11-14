package com.unex.proyectoasee_nogymmembership;

import android.content.Context;
import android.net.Uri;
import android.util.*;
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


    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = super.onCreateView(inflater, container, savedInstanceState);

        //Establece el color de fondo de la pantalla de preferencias
        view.setBackgroundColor(getResources().getColor(android.R.color.white));

        /*int horizontalMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
        int verticalMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
        int topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (int) getResources().getDimension(R.dimen.activity_vertical_margin) + 30, getResources().getDisplayMetrics());

        view.setPadding(horizontalMargin, topMargin, horizontalMargin, verticalMargin);*/



        //Muestra la pantalla de preferencias
        //TODO evitar que la pantalla de preferencias se coloque encima de la actividad principal
        addPreferencesFromResource(R.xml.preferences);
        return view; }


}
