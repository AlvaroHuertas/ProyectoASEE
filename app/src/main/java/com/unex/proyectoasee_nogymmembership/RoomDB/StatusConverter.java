package com.unex.proyectoasee_nogymmembership.RoomDB;

import android.arch.persistence.room.TypeConverter;

import com.unex.proyectoasee_nogymmembership.Models.Routine;

public class StatusConverter {

    @TypeConverter
    public static Routine.Status toStatus(String status){
        return Routine.Status.valueOf(status);
    }

    @TypeConverter
    public static String toString(Routine.Status status){
        return status.name();
    }
}
