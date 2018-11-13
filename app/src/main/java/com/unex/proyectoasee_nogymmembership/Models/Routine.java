package com.unex.proyectoasee_nogymmembership.Models;

import java.util.List;

public class Routine {

    public List<Exercise> exerciseList;

    public Routine(List<Exercise> exercises){
        this.exerciseList=exercises;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }
}
