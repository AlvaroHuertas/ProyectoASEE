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

    public void deleteAllItems(){
        elements.clear();
    }

    public void updateItem(int position, Routine item){
        elements.get(position).setStatus(item.getStatus());
    }

    public Routine get(int position){
        return elements.get(position);
    }

    public int size(){
        return elements.size();
    }

}
