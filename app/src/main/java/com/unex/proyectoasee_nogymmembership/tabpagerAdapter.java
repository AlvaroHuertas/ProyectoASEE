package com.unex.proyectoasee_nogymmembership;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class tabpagerAdapter extends FragmentStatePagerAdapter {

    String[] tabarray = new String[]{"LIST OF ROUTINES","CREATE ROUTINE"};
    Integer tabnumber = 2;

    public tabpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabarray[position];
    }

    @Override
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
