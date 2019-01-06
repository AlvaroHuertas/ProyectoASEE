package com.unex.proyectoasee_nogymmembership.Repository;

import android.content.Context;

import com.unex.proyectoasee_nogymmembership.Models.Routine;
import com.unex.proyectoasee_nogymmembership.RoomDB.AppDataBase;

import java.util.List;

public class AppRepository {

    private static AppRepository instance;

    private AppDataBase appDB;

    private AppRepository(Context c){
        appDB = AppDataBase.getDataBase(c);
    }

    public static AppRepository getInstance(Context c){
        if(instance == null){
            instance = new AppRepository(c.getApplicationContext());
        }
        return instance;
    }

    /**
     * GET all the routines stored in the database
     * @return List of routines currently stored in database
     */
    public List<Routine> getAllRoutines() {
        List<Routine> items = appDB.routineDAO().getAll();
        return items;
    }

    /**
     * Add a new routine to the database
     * @param routine Item we are going to add
     * @return Return the id autogenerated for the new routine
     */
    public long addRoutine(Routine routine) {
        long id = appDB.routineDAO().insert(routine);
        return id;
    }
}
