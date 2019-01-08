package com.unex.proyectoasee_nogymmembership;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity
        {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout=(TabLayout)findViewById(R.id.tabs);

        ViewPager pager=(ViewPager)findViewById(R.id.viewpager);

        tabpagerAdapter tabPagerAdapter = new tabpagerAdapter(getSupportFragmentManager());
        //Pasar el adapter al pager para la creacion de los fragments
        pager.setAdapter(tabPagerAdapter);
        tabLayout.setupWithViewPager(pager);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        //Cargar las preferencias con los valores por defecto
        PreferenceManager.setDefaultValues(this,R.xml.preferences,false);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
