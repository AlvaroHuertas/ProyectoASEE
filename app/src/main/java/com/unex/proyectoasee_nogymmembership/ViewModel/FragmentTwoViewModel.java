package com.unex.proyectoasee_nogymmembership.ViewModel;

import android.arch.lifecycle.ViewModel;

import com.unex.proyectoasee_nogymmembership.Models.ExerciseList;

public class FragmentTwoViewModel extends ViewModel {

    private ExerciseList exerciseList;

    public FragmentTwoViewModel() {
    }

    public ExerciseList getExerciseList() {
        return this.exerciseList;
    }

    public void setExerciseList(ExerciseList exerciseList) {
        this.exerciseList = exerciseList;
    }
}

