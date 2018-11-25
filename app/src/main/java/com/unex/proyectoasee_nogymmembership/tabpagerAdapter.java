package com.unex.proyectoasee_nogymmembership;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class tabpagerAdapter extends FragmentStatePagerAdapter {

    //TODO obtener los títulos de cada tabItem desde strings.xml
    String[] tabarray = new String[]{"                 ROUTINES                 ","                 EXERCISES                "};
    Integer tabnumber = 2;

    public tabpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    //Obtiene el título de cada página(tabItem) del array previamente definido
    public CharSequence getPageTitle(int position) {
        return tabarray[position];
    }

    @Override
    //Dada una posición 0-izquierda, 1-derecha, crea el Fragment correspondiente
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                FragmentOne one= new FragmentOne();
                return one;
            case 1:
                FragmentTwo two= new FragmentTwo();
                return two;
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabnumber;
    }
}
