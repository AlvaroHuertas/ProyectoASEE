package com.unex.proyectoasee_nogymmembership.Models;

public class Category {
    public String Name;
    public String Difficulty;

    public Category(String Name, String Difficulty){
        this.Name = Name;
        this.Difficulty = Difficulty;
    }

    public String getDifficulty() {
        return Difficulty;
    }

    public String getName() {
        return Name;
    }
}
