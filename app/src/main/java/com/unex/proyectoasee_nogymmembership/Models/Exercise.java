package com.unex.proyectoasee_nogymmembership.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "exercise")
public class Exercise implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String description;
    @Ignore
    private String category;
    @Ignore
    private String muscles;
    private String imageURI;

    @Ignore
    public final static String ID = "ID";
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

    public Exercise(long id,String name, String description, String imageuri){
        this.id=id;
        this.name=name;
        this.description=description;
        this.imageURI=imageuri;
    }
    public Exercise(String name, String description, String muscles, String imageuri){
        this.name=name;
        this.description=description;
       this.muscles = muscles;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getMuscles() {
        return muscles;
    }

    public String getURI() {
        return imageURI;
    }



}
