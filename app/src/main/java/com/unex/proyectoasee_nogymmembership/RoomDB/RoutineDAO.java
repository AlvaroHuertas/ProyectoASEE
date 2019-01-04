package com.unex.proyectoasee_nogymmembership.RoomDB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.unex.proyectoasee_nogymmembership.Models.Routine;

import java.util.List;

@Dao
public interface RoutineDAO {

    @Query("SELECT * FROM routine")
    public List<Routine> getAll();

    @Insert
    public long insert(Routine item);

    @Update
    public void updateStatus(Routine item);

    @Delete
    public void deleteRoutines(Routine... routines);

    @Query("SELECT * FROM routine WHERE id = :id")
    public Routine getRoutine(int id);

    @Query("SELECT * FROM routine WHERE id = :id")
    Routine getRoutine(int id);

    @Query("DELETE FROM routine")
    public void deleteAll();
}
