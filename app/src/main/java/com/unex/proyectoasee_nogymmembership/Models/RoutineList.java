package com.unex.proyectoasee_nogymmembership.Models;

import java.util.ArrayList;
import java.util.List;

public class RoutineList {

    protected List<Routine> elements;

    public RoutineList(){
        elements = new ArrayList<>();
    }

    public RoutineList(List<Routine> e){
        elements = e;
    }


    public void clear(){
        elements.clear();
    }

    public void addItem(Routine item){
        elements.add(item);
    }

    public void deleteItem(Routine routine){
        elements.remove(routine);
    }

    public void deleteAllItems(){
        elements.clear();
    }

    public void updateItem(int position, Routine item){
        elements.get(position).setStatus(item.getStatus());
    }

    public List<Routine> getElements() {
        return elements;
    }

    public void setElements(List<Routine> elements) {
        this.elements = elements;
    }

    public Routine get(int position){
        return elements.get(position);
    }

    public int size(){
        return elements.size();
    }

    public void addAll(List<Routine> r) {
        elements.addAll(r);
    }
}
