package com.unex.proyectoasee_nogymmembership.Models;

public class Exercise {

    private long id;
    private String name;
    private String description;
    private String category;
    private String imageURI;

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

    public String getURI() {
        return imageURI;
    }



}
