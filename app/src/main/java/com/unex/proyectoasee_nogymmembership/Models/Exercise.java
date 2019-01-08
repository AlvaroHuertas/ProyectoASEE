package com.unex.proyectoasee_nogymmembership.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;

import java.io.Serializable;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "exercise", primaryKeys = {"exerciseId", "routineId"}, foreignKeys = @ForeignKey(entity = Routine.class, parentColumns = "id", childColumns = "routineId", onDelete = CASCADE))
public class Exercise implements Serializable {

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

    /**
     * Getter of exerciseID attribute
     *
     * @return exerciseId of the Exercise
     */
    public long getExerciseId() {
        return exerciseId;
    }

    /**
     * Setter of exerciseId attribute
     *
     * @param exerciseId New value of the attribute
     */
    public void setExerciseId(long exerciseId) {
        this.exerciseId = exerciseId;
    }

    /**
     * Getter of name attribute
     *
     * @return name of the Exercise
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of name attribute
     *
     * @param name New value of the attribute
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of description attribute
     *
     * @return description of the Exercise
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter of description attribute
     *
     * @param description New value of the attribute
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter of muscles attribute
     *
     * @return muscles of the Exercise
     */
    public String getMuscles() {
        return muscles;
    }

    /**
     * Setter of muscles attribute
     *
     * @param muscles New value of the attribute
     */
    public void setMuscles(String muscles) {
        this.muscles = muscles;
    }

    /**
     * Getter of routineId attribute
     *
     * @return routineId of the Exercise
     */
    public long getRoutineId() {
        return routineId;
    }

    /**
     * Setter of routineId attribute
     *
     * @param routineId New value of the attribute
     */
    public void setRoutineId(long routineId) {
        this.routineId = routineId;
    }

}
