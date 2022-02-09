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
    ArrayList<String> tareas;
    int[] imagenes;
    LayoutInflater inflater;

    public ListViewAdapter(Context context, ArrayList<String> tareas, int[] imagenes) {
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
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Variables de texto e imagen
        TextView categoria;
        TextView titulo;
        TextView descripcion;
        ImageView imgImg;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.vista_listview, parent, false);

        // Localizamos los TextViews en el listview_item.xml
        categoria = (TextView) itemView.findViewById(R.id.list_row_categoria);
        titulo = (TextView) itemView.findViewById(R.id.list_row_titulo);
        descripcion = (TextView) itemView.findViewById(R.id.list_row_descripcion);


        imgImg = (ImageView) itemView.findViewById(R.id.list_row_image);

        // Captura la posicion y seteamos los textView
        categoria.setText(tareas.get(position).split(",")[0]);
        titulo.setText(tareas.get(position).split(",")[1]);
        descripcion.setText(tareas.get(position).split(",")[2]);


//        imgImg.setImageResource(imagenes[position]);

        return itemView;
    }


}
