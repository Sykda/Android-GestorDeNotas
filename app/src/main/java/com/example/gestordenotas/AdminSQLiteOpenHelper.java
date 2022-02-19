package com.example.gestordenotas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {


    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL("CREATE TABLE notas(id INTEGER PRIMARY KEY AUTOINCREMENT, categoria TEXT ,titulo TEXT , descripcion TEXT, imagen int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table notas");
    }

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
