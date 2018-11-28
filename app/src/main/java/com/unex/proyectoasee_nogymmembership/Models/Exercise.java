package com.unex.proyectoasee_nogymmembership.Models;

import java.io.Serializable;

public class Exercise implements Serializable {

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

    public Exercise(String name, String description, String muscles, String imageuri){
        this.name=name;
        this.description=description;
        this.muscles=muscles;
        this.imageURI=imageuri;
    }

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
