package com.unex.proyectoasee_nogymmembership.Models;

import android.content.Intent;

public class Category {
    public String Name;
    public String Difficulty;

    public final static String NAME = "name";
    public final static String DIFFICULTY = "difficulty";


    public Category(String Name, String Difficulty){
        this.Name = Name;
        this.Difficulty = Difficulty;
    }

    //Constructor a partir de intent
    public Category(Intent intent){

        this.Name = intent.getStringExtra(Category.NAME);
        this.Difficulty = intent.getStringExtra(Category.DIFFICULTY);
    }

    public String getDifficulty() {
        return Difficulty;
    }

    public String getName() {
        return Name;
    }

    public static void packageIntent(Intent intent, String name, String difficulty) {

        intent.putExtra(Category.NAME, name);
        intent.putExtra(Category.DIFFICULTY, difficulty);
    }
}
