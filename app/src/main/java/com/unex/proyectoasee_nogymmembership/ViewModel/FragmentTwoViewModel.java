package com.unex.proyectoasee_nogymmembership.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.unex.proyectoasee_nogymmembership.Models.ExerciseList;

/**
 * {@link ViewModel} for {@link com.unex.proyectoasee_nogymmembership.FragmentTwo}
 */
public class FragmentTwoViewModel extends ViewModel {

    private MutableLiveData<ExerciseList> exerciseList;

    public FragmentTwoViewModel() {
        this.exerciseList=new MutableLiveData<>();
    }

    public MutableLiveData<ExerciseList> getExerciseList() {
        return this.exerciseList;
    }

    public void setExerciseList(ExerciseList exerciseList) {
        this.exerciseList.postValue(exerciseList);
    }
}

