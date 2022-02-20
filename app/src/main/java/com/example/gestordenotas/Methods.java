package com.example.gestordenotas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class Methods {

    private static final int[] iconos = {R.mipmap.hourglass, R.mipmap.calendar, R.mipmap.warning};
    private static final Methods methods = new Methods();
    private static ArrayList<Tarea> listaTareas;
    private Tarea tarea;

    public static Methods getInstance() {
        return methods;
    }

    public static ArrayList<Tarea> getListaTareas() {
        return listaTareas;
    }

    public int[] getIconos() {
        return iconos;
    }

    //Método que consulta la bbdd
    public void consultarListaNotas(Context context) {

        AdminSQLiteOpenHelper bbddAdministrador = new AdminSQLiteOpenHelper(context, "administracion", null, 1);
        SQLiteDatabase db = bbddAdministrador.getReadableDatabase();

        listaTareas = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM notas", null);

        while (cursor.moveToNext()) {

            //Por cada objeto creamos una tarea con los parámetros de la bbdd.
            tarea = new Tarea(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4));
            listaTareas.add(tarea);
        }

    }

    //Método para el borrado de bbdd.
    public void bbddDelete(Context context, int id) {

        AdminSQLiteOpenHelper bbddAdministrador = new AdminSQLiteOpenHelper(context, "administracion", null, 1);
        SQLiteDatabase bbdd = bbddAdministrador.getWritableDatabase();
        int cantidad = bbdd.delete("notas", "id =" + id, null);
        bbdd.close();
        if (cantidad == 1) {
            Toast.makeText(context, "Artículo eliminado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Ha habido un error", Toast.LENGTH_SHORT).show();
        }
    }
}