package com.unex.proyectoasee_nogymmembership.Models;

import android.content.Intent;


public class Routine {

    public enum Status {
        NOTDONE, DONE
    };

    private long id;
    private String name;
    private String type;
    private Status status;

    public final static String ID = "ID";
    public final static String NAME = "name";
    public final static String TYPE = "type";
    public final static String STATUS = "status";


    public Routine(long id, String name, String type, String status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = Status.valueOf(status);
    }

    public Routine(Intent intent) {
        id = intent.getLongExtra(Routine.ID, 0);
        name = intent.getStringExtra(Routine.NAME);
        type = intent.getStringExtra(Routine.TYPE);

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




    public static void packageIntent(Intent intent, String name, String type) {

        intent.putExtra(Routine.NAME, name);
        intent.putExtra(Routine.TYPE, type);
    }
}
