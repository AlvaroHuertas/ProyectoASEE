package com.unex.proyectoasee_nogymmembership.RoomDB;

import android.arch.persistence.room.Dao;
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
    public int updateStatus(Routine item);

    @Query("DELETE FROM routine")
    public void deleteAll();
}