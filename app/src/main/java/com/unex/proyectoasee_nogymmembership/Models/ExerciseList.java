package com.unex.proyectoasee_nogymmembership.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExerciseList implements Serializable {

    protected List<Exercise> elements;

    public ExerciseList(){ elements = new ArrayList<>();}

    public ExerciseList(List<Exercise> e){
        elements = e;
    }

    public List<Exercise> getElements() {
        return elements;
    }

    public void setElements(List<Exercise> elements) {
        this.elements = elements;
    }
}
