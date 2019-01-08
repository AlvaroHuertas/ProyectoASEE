package com.unex.proyectoasee_nogymmembership.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExerciseList implements Serializable {

    protected List<Exercise> elements;

    public ExerciseList() {
        elements = new ArrayList<>();
    }

    public ExerciseList(List<Exercise> e) {
        elements = e;
    }

    /**
     * Removes all the elements from the list
     */
    public void clear() {
        elements.clear();
    }

    /**
     * Getter of the elements attribute
     *
     * @return List with all the elements
     */
    public List<Exercise> getElements() {
        return elements;
    }

    /**
     * Setter of the elements attribute
     *
     * @param elements List of elements
     */
    public void setElements(List<Exercise> elements) {
        this.elements = elements;
    }

    /**
     * Add all the elements to the list
     *
     * @param e List of elements to be added
     */
    public void addAll(List<Exercise> e) {
        elements.addAll(e);
    }

    /**
     * Adds one element to the list
     *
     * @param e Element to be added
     */
    public void addItem(Exercise e) {
        elements.add(e);
    }

    /**
     * Removes a element from the list
     *
     * @param e Item to be removed
     */
    public void deleteItem(Exercise e) {
        elements.remove(e);
    }

    /**
     * GET an exercise from a certain position
     * @param i Position
     * @return The exercise
     */
    public Exercise getExercise(int i) {
        return elements.get(i);
    }
}
