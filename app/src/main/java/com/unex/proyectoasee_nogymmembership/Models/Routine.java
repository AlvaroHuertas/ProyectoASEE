package com.unex.proyectoasee_nogymmembership.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.content.Intent;

import com.unex.proyectoasee_nogymmembership.RoomDB.StatusConverter;

import java.io.Serializable;

@Entity(tableName = "routine")
public class Routine implements Serializable {


    public enum Status {
        NOTDONE, DONE
    }


    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public String type;
    @TypeConverters(StatusConverter.class)
    public Status status;

    @Ignore
    private ExerciseList exercises;

    @Ignore
    public final static String ID = "ID";
    @Ignore
    public final static String NAME = "name";
    @Ignore
    public final static String TYPE = "type";
    @Ignore
    public final static String STATUS = "status";

    public Routine(long id, String name, String type, Status status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
    }

    @Ignore
    public Routine(Intent intent) {
        id = intent.getLongExtra(Routine.ID, 0);
        name = intent.getStringExtra(Routine.NAME);
        type = intent.getStringExtra(Routine.TYPE);
        this.exercises = new ExerciseList();

        //Rutina por defecto no realizada
        status = Status.NOTDONE;
    }

    /**
     * Getter of status attribute
     *
     * @return status of the Exercise
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Setter of status attribute
     *
     * @param status New value of the attribute
     */
    public void setStatus(Status status) {
        this.status = status;
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
     * Getter of type attribute
     *
     * @return type of the Exercise
     */
    public String getType() {
        return type;
    }

    /**
     * Setter of type attribute
     *
     * @param type New value of the attribute
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter of id attribute
     *
     * @return id of the Exercise
     */
    public long getId() {
        return id;
    }

    /**
     * Setter of id attribute
     * @param id New value of the attribute
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Package the routine attributes into an intent
     *
     * @param intent Intent which is going to contain the attributes
     * @param name   String extra which is going to be added to the intent
     * @param type   String extra which is going to be added to the intent
     */
    @Ignore
    public static void packageIntent(Intent intent, String name, String type) {
        intent.putExtra(Routine.NAME, name);
        intent.putExtra(Routine.TYPE, type);
    }
}
