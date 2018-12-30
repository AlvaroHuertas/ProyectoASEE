package com.unex.proyectoasee_nogymmembership.RoomDB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.unex.proyectoasee_nogymmembership.Models.Exercise;
import com.unex.proyectoasee_nogymmembership.Models.Routine;

@Database(entities = {Routine.class,Exercise.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase{
    private static AppDataBase instance;

    public static AppDataBase getDataBase(Context context){
        if(instance==null)
            instance= Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,"app.db").build();
        return instance;
    }

    public abstract RoutineDAO routineDAO();

    public abstract ExerciseDAO exerciseDAO();
}
