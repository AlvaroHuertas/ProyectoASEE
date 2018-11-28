package com.unex.proyectoasee_nogymmembership.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExerciseList implements Serializable {

    protected List<Exercise> elements;

    public ExerciseList(){
        elements = new ArrayList<>();

        Exercise e1 = new Exercise("Exercise1", "Testing exercise 1", "Back", null);
        Exercise e2 = new Exercise("Exercise2", "Testing exercise 2", "Chest", null);
        Exercise e3 = new Exercise("Exercise3", "Testing exercise 3", "Back", null);

        elements.add(e1);
        elements.add(e2);
        elements.add(e3);
    }

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
