package com.unex.proyectoasee_nogymmembership.Models;

public class Exercise {
    public String name;
    public String description;
    /*public String category;*/
    public String muscles;
    public String imageURI;

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
