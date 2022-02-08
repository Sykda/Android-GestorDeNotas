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
    ArrayList<String> titulos;
    int[] imagenes;
    LayoutInflater inflater;

    public ListViewAdapter(Context context, ArrayList<String> titulos, int[] imagenes) {
        this.context = context;
        this.titulos = titulos;
        this.imagenes = imagenes;
    }

    @Override
    public int getCount() {
        return titulos.size();
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
        TextView txtTitle;
        TextView descripcion;
        TextView descripcion1;
        TextView descripcion2;
        ImageView imgImg;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.vista_listview, parent, false);

        // Localizamos los TextViews en el listview_item.xml
        txtTitle = (TextView) itemView.findViewById(R.id.list_row_title);
        descripcion = (TextView) itemView.findViewById(R.id.list_row_description);
        descripcion1 = (TextView) itemView.findViewById(R.id.list_row_description1);
        descripcion2 = (TextView) itemView.findViewById(R.id.list_row_description2);

        imgImg = (ImageView) itemView.findViewById(R.id.list_row_image);

        // Captura la posicion y seteamos los textView
        txtTitle.setText(titulos.get(position).split(",")[1]);
        descripcion.setText(titulos.get(position).split(",")[2]);
        descripcion1.setText(titulos.get(position).split(",")[3] + " cm");
        descripcion2.setText(titulos.get(position).split(",")[4]);

        imgImg.setImageResource(imagenes[position]);

        return itemView;
    }


}
