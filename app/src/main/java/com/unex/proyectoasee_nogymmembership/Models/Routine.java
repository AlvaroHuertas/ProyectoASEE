package com.unex.proyectoasee_nogymmembership.Models;

import android.content.Intent;

import java.text.ParseException;
import java.util.Date;

public class Routine {

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

    public enum Status {
        NOTDONE, DONE
    };

    private String name;
    private String type;
    private Status status;

    public final static String NAME = "name";
    public final static String TYPE = "type";

    public Routine(Intent intent) {

        name = intent.getStringExtra(Routine.NAME);
        type = intent.getStringExtra(Routine.TYPE);
    }

    public static void packageIntent(Intent intent, String name, String type) {

        intent.putExtra(Routine.NAME, name);
        intent.putExtra(Routine.TYPE, type);
    }
}
