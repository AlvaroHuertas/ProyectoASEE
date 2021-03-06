package com.unex.proyectoasee_nogymmembership.RoomDB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.unex.proyectoasee_nogymmembership.Models.Exercise;

import java.util.List;

@Dao
public interface ExerciseDAO {

    @Query("SELECT * FROM exercise")
    public List<Exercise> getAll();

    @Insert
    public long insert(Exercise item);

    @Query("DELETE FROM exercise")
    public void deleteAll();

    @Delete
    public void deleteExercises(Exercise... exercises);

    @Query("SELECT * FROM exercise WHERE exerciseId = :id and routineId = :rid")
    Exercise getExercise(long id, long rid);

    @Update
    public int updateExercise(Exercise item);

    @Query("SELECT * FROM exercise WHERE routineId = :id")
    public List<Exercise> getExercisesByRoutineId(long id);
}
