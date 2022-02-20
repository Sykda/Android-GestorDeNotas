package com.example.gestordenotas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridViewAdapter extends BaseAdapter {

    // Variables
    Context context;
    ArrayList<String> tareas;
    int[] imagenes;
    LayoutInflater inflater;

    public GridViewAdapter(Context context, ArrayList<String> tareas, int[] imagenes) {
        this.context = context;
        this.tareas = tareas;
        this.imagenes = imagenes;
    }

    @Override
    public int getCount() {
        return tareas.size();
    }

    @Override
    public Object getItem(int position) {
        return tareas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Variables de texto e imagen
        TextView categoria;
        TextView titulo;
        ImageView imgImg;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.vista_gridview, parent, false);

        // Localizamos los TextViews en vista_gridview.xml
        categoria = itemView.findViewById(R.id.list_row_categoria);
        titulo = itemView.findViewById(R.id.list_row_titulo);
        imgImg = itemView.findViewById(R.id.list_row_image);

        // Captura la posicion y seteamos los textView
        titulo.setText(tareas.get(position).split(",")[0]);
        categoria.setText(tareas.get(position).split(",")[1]);


        imgImg.setImageResource(imagenes[Integer.parseInt(tareas.get(position).split(",")[3])]);

        return itemView;
    }

}
