package com.unex.proyectoasee_nogymmembership.Models;

import java.util.ArrayList;
import java.util.List;

public class RoutineList {

    protected List<Routine> elements;

    public RoutineList() {
        elements = new ArrayList<>();
    }

    public RoutineList(List<Routine> e) {
        elements = e;
    }

    /**
     * Removes all the elements from the list
     */
    public void clear() {
        elements.clear();
    }

    /**
     * Adds one element to the list
     *
     * @param item Element to be added
     */
    public void addItem(Routine item) {
        elements.add(item);
    }

    /**
     * Removes a element from the list
     *
     * @param routine Item to be removed
     */
    public void deleteItem(Routine routine) {
        elements.remove(routine);
    }

    /**
     * Getter of the elements attribute
     *
     * @return List with all the elements
     */
    public List<Routine> getElements() {
        return elements;
    }

    /**
     * Setter of the elements attribute
     *
     * @param elements List of elements
     */
    public void setElements(List<Routine> elements) {
        this.elements = elements;
    }

    /**
     * Gets a element from the list
     *
     * @param position Position of the element
     * @return Element
     */
    public Routine get(int position) {
        return elements.get(position);
    }

    /**
     * Calculates the size of the list
     *
     * @return Size of the list
     */
    public int size() {
        return elements.size();
    }

    /**
     * Add all the elements to the list
     *
     * @param r List of elements to be added
     */
    public void addAll(List<Routine> r) {
        elements.addAll(r);
    }
}
