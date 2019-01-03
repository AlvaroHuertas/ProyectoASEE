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
    };

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String type;
    @TypeConverters(StatusConverter.class)
    private Status status;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Ignore
    public static void packageIntent(Intent intent, String name, String type) {

        intent.putExtra(Routine.NAME, name);
        intent.putExtra(Routine.TYPE, type);
    }
}
