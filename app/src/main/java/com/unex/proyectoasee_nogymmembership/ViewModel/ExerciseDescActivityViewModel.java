package com.unex.proyectoasee_nogymmembership.ViewModel;

import android.arch.lifecycle.ViewModel;

import com.unex.proyectoasee_nogymmembership.Models.Exercise;

public class ExerciseDescActivityViewModel extends ViewModel {

    private Exercise exercise;

    public ExerciseDescActivityViewModel() {
    }

    public Exercise getExercise() {
        return this.exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}
