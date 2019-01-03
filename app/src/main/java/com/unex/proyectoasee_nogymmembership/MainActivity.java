package com.unex.proyectoasee_nogymmembership;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.unex.proyectoasee_nogymmembership.AppBarUtils.SettingsFragment;
import com.unex.proyectoasee_nogymmembership.AppBarUtils.UserPreferences;

public class MainActivity extends AppCompatActivity
        {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Asignar el layout que contiene ambas pantallas
        TabLayout tabLayout=(TabLayout)findViewById(R.id.tabs);

        //Asignar el layout que contiene el ViewPager
        ViewPager pager=(ViewPager)findViewById(R.id.viewpager);

        //Creación del adaptador
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
        String username;
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        username=sharedPref.getString(SettingsFragment.KEY_PREF_USERNAME,"");


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_preferences) {
            Intent intent = new Intent(getApplicationContext(), UserPreferences.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}
