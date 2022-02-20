package com.example.gestordenotas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    // Variables
    Context context;
    ArrayList<Tarea> tareas;
    int[] imagenes;
    LayoutInflater inflater;

    public ListViewAdapter(Context context, ArrayList<Tarea> tareas, int[] imagenes) {
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

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.vista_listview, parent, false);

        // Localizamos los TextViews en vista_gridview.xml
        TextView titulo = itemView.findViewById(R.id.list_row_titulo);
        TextView categoria = itemView.findViewById(R.id.list_row_categoria);
        ImageView imgImg = itemView.findViewById(R.id.list_row_image);

        // Captura la posicion y seteamos los textView
        categoria.setText(tareas.get(position).getTitulo());
        titulo.setText(tareas.get(position).getCategoria());
        imgImg.setImageResource(imagenes[tareas.get(position).getImagen()]);

        return itemView;
    }
}