package com.unex.proyectoasee_nogymmembership.RoomDB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.unex.proyectoasee_nogymmembership.Models.Exercise;
import com.unex.proyectoasee_nogymmembership.Models.Routine;

import java.util.List;

@Dao
public interface ExerciseDAO {

    @Query("SELECT * FROM exercise")
    public List<Routine> getAll();

    @Insert
    public long insert(Exercise item);

    @Query("DELETE FROM exercise")
    public void deleteAll();
}
