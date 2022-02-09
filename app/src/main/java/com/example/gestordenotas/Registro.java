package com.example.gestordenotas;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Registro extends AppCompatActivity {

    private Spinner spinner;
    private EditText et_categoria, et_titulo, et_descripcion;
    private AdminSQLiteOpenHelper bbddAdministrador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        et_categoria = findViewById(R.id.categoria);
        et_titulo = findViewById(R.id.titulo);
        et_descripcion = findViewById(R.id.descripcion);


        //Spinner
        spinner = findViewById(R.id.spinner);
        String[] opciones = {"Aviso", "Reunion", "Varios"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_item, opciones);
        spinner.setAdapter(adapter);

        //Lo que pasa cuando seleccionas algo del spinner
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> spn,
                                               android.view.View v,
                                               int posicion,
                                               long id) {

                        String select = spinner.getSelectedItem().toString();

                        if (select.equals("Aviso")) {

                        } else if (select.equals("Reunion")) {

                        } else if (select.equals("Varios")) {

                        }
                    }

                    public void onNothingSelected(AdapterView<?> spn) {
                    }
                });


    }

    //Metodo para registrar una nota
    public void registrar(View view) {
       /* AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

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

            Toast.makeText(this, "Se ha registrado correctamente", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();
        }*/

        bbddAdministrador.bbddInsert(this, et_categoria.getText().toString(),et_titulo.getText().toString(),et_descripcion.getText().toString());
        et_categoria.setText("");
        et_titulo.setText("");
        et_descripcion.setText("");

        //Intent para volver
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

/*
    //Metodo para modificar un producto o artículo
    public void modificar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos=admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();

        if(!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);

            int cantidad=BaseDeDatos.update("articulos",registro,"codigo="+codigo,null);
            BaseDeDatos.close();

            if(cantidad==1){
                Toast.makeText(this, "Artículo modificado", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "El artículo no existe", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();

        }
    }
*/
}