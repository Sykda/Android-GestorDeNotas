package com.example.gestordenotas;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    private AdminSQLiteOpenHelper bbddAdministrador;

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL("CREATE TABLE notas(id INTEGER PRIMARY KEY AUTOINCREMENT, categoria TEXT ,titulo TEXT , descripcion TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table notas");
    }

   public void bbddDelete(Context context, int id){
       bbddAdministrador = new AdminSQLiteOpenHelper(context, "administracion", null, 1);
       SQLiteDatabase bbdd = bbddAdministrador.getWritableDatabase();
       int cantidad = bbdd.delete("notas", "id=" + id, null);
       bbdd.close();
       if (cantidad == 1) {
           Toast.makeText(context, "Artículo eliminado", Toast.LENGTH_SHORT).show();
       } else {
           Toast.makeText(context, "Ha habido un error", Toast.LENGTH_SHORT).show();
       }
   }

   public void bbddInsert(Context context, EditText et_categoria, EditText et_titulo, EditText et_descripcion){
       bbddAdministrador = new AdminSQLiteOpenHelper(context, "administracion", null, 1);
       SQLiteDatabase BaseDeDatos = bbddAdministrador.getWritableDatabase();

       et_categoria = et_categoria.findViewById(R.id.categoria);
       et_titulo = et_titulo.findViewById(R.id.titulo);
       et_descripcion = et_descripcion.findViewById(R.id.descripcion);

       String categoria = et_categoria.getText().toString();
       String titulo = et_titulo.getText().toString();
       String descripcion = et_descripcion.getText().toString();

       if (!categoria.isEmpty() && !titulo.isEmpty() && !descripcion.isEmpty()) {
           ContentValues registro = new ContentValues();
           registro.put("categoria", categoria);
           registro.put("titulo", titulo);
           registro.put("descripcion", descripcion);

           BaseDeDatos.insert("notas", null, registro);

           BaseDeDatos.close();
           et_categoria.setText("");
           et_titulo.setText("");
           et_descripcion.setText("");

           Toast.makeText(context, "Se ha registrado correctamente", Toast.LENGTH_SHORT).show();

       } else {
           Toast.makeText(context, "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();
       }
   }
}
