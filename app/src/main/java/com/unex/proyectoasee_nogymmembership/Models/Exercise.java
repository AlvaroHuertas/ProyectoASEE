package com.unex.proyectoasee_nogymmembership.Models;

import java.io.Serializable;

public class Exercise implements Serializable {

    private long id;
    private String name;
    private String description;
    private String category;
    private String muscles;
    private String imageURI;

    public final static String ID = "ID";
    public final static String NAME = "name";
    public final static String DESCRIPTION = "description";
    public final static String CATEGORY = "category";
    public final static String MUSCLES = "muscles";
    public final static String IMAGEURI = "imageuri";



    public Exercise(long id,String name, String description, String imageuri){
        this.id=id;
        this.name=name;
        this.description=description;
        this.imageURI=imageuri;
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
