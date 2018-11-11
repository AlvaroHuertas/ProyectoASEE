package com.unex.proyectoasee_nogymmembership.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.unex.proyectoasee_nogymmembership.Models.Category;
import com.unex.proyectoasee_nogymmembership.R;

import java.util.ArrayList;

public class CategoryAdapter extends ArrayAdapter {

    public CategoryAdapter(Context context, ArrayList<Category> categories) {
        super(context, 0, categories);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //Obtenemos el item para esta posición
        Category category = (Category) getItem(position);
        //Comprueba si la vista está siendo reusada, de no ser así hace el inflate
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_category, parent, false);
        }
        //Obtenemos las referencias al layout
        TextView ctgName = (TextView) convertView.findViewById(R.id.ctgName);
        TextView ctgDifficulty = (TextView) convertView.findViewById(R.id.ctgDifficulty);

        //Insertamos los datos en la vista
        ctgName.setText(category.getName());
        ctgDifficulty.setText(category.getDifficulty());

        //Devolvemos la vista para mostrarla en pantalla
        return convertView;
    }
}
