package com.unex.proyectoasee_nogymmembership.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "exercise", foreignKeys = @ForeignKey(entity = Routine.class, parentColumns = "id", childColumns = "routineId", onDelete = CASCADE))
public class Exercise implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public long exerciseId;
    public String name;
    public String description;
    public long routineId;


    @Ignore
    private String category;
    @Ignore
    private String muscles;
    // private String imageURI;

    @Ignore
    public final static String ID = "exerciseId";
    @Ignore
    public final static String NAME = "name";
    @Ignore
    public final static String DESCRIPTION = "description";
    @Ignore
    public final static String CATEGORY = "category";
    @Ignore
    public final static String MUSCLES = "muscles";
    @Ignore
    public final static String IMAGEURI = "imageuri";

    @Ignore
    public Exercise(long exerciseId, String name, String description, String imageuri) {
        this.exerciseId = exerciseId;
        this.name = name;
        this.description = description;
        // this.imageURI=imageuri;
    }

    @Ignore
    public Exercise(String name, String description, String muscles, String imageuri) {
        this.name = name;
        this.description = description;
        this.muscles = muscles;
    }

    public Exercise(long exerciseId, String name, String description, long routineId) {
        this.exerciseId = exerciseId;
        this.name = name;
        this.description = description;
        this.routineId = routineId;
    }

    public long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMuscles() {
        return muscles;
    }

    public long getRoutineId() {
        return routineId;
    }

    public void setRoutineId(long routineId) {
        this.routineId = routineId;
    }


    public String getText1() {
        return this.name;
    }
}
