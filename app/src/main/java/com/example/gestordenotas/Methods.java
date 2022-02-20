package com.example.gestordenotas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Methods {

    private static final Methods methods = new Methods();
    public static ArrayList<Tarea> listaTareas;
    private final int[] iconos = {R.mipmap.hourglass, R.mipmap.calendar, R.mipmap.warning};
    private ArrayList<String> listaStrings;
    private Tarea tarea;

    public static Methods getInstance() {
        return methods;
    }

    public static ArrayList<Tarea> getListaTareas() {
        return listaTareas;
    }

    public ArrayList<String> getListaStrings() {
        return listaStrings;
    }

    public int[] getIconos() {
        return iconos;
    }

    public void consultarListaNotas(Context context) {
        AdminSQLiteOpenHelper bbddAdministrador = new AdminSQLiteOpenHelper(context, "administracion", null, 1);
        SQLiteDatabase db = bbddAdministrador.getReadableDatabase();
        listaTareas = new ArrayList<Tarea>();

        Cursor cursor = db.rawQuery("SELECT * FROM notas", null);

        while (cursor.moveToNext()) {

            //Seteamos con un constructor todos los parámetros
            tarea = new Tarea(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4));
            listaTareas.add(tarea);
        }
        listaStrings = new ArrayList<String>();
        for (int i = 0; i < listaTareas.size(); i++) {
            listaStrings.add(listaTareas.get(i).getCategoria() + "," + listaTareas.get(i).getTitulo() + "," + listaTareas.get(i).getDescripcion() + "," + listaTareas.get(i).getImagen());
        }
    }

}
