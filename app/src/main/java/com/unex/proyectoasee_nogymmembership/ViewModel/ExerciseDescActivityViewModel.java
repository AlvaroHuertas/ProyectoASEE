package com.unex.proyectoasee_nogymmembership.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.unex.proyectoasee_nogymmembership.Models.Exercise;

public class ExerciseDescActivityViewModel extends ViewModel {

    private MutableLiveData<Exercise> exercise;

    public ExerciseDescActivityViewModel() {
        exercise=new MutableLiveData<>();
    }

    public MutableLiveData<Exercise> getExercise() {
        return this.exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise.postValue(exercise);
    }
}
