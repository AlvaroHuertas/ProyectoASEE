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

    public void clear(){
        elements.clear();
    }

    public void deleteItem(Exercise e){
        elements.remove(e);
    }

    public List<Exercise> getElements() {
        return elements;
    }

    public void setElements(List<Exercise> elements) {
        this.elements = elements;
    }

    public void addAll(List<Exercise> e) {
        elements.addAll(e);
    }

    public void addItem(Exercise e) {
        elements.add(e);
    }
}
