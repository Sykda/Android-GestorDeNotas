package com.example.gestordenotas;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Registro extends AppCompatActivity {

    private Spinner spinner;
    private EditText et_categoria, et_titulo, et_descripcion;
    private int imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //Referencias
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
                            et_categoria.setText("Aviso");
                            imagen=0;

                        } else if (select.equals("Reunion")) {
                            et_categoria.setText("Reunion");
                            imagen=1;

                        } else if (select.equals("Varios")) {
                            et_categoria.setText("Varios");
                            imagen=2;

                        }
                    }

                    public void onNothingSelected(AdapterView<?> spn) {
                    }
                });


    }

    //Metodo para registrar una nota
    public void registrar(View view) {
        AdminSQLiteOpenHelper bbddAdministrador = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bbdd = bbddAdministrador.getWritableDatabase();

        String categoria = et_categoria.getText().toString();
        String titulo = et_titulo.getText().toString();
        String descripcion = et_descripcion.getText().toString();


        if (!categoria.isEmpty() && !titulo.isEmpty() && !descripcion.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put("categoria", categoria);
            registro.put("titulo", titulo);
            registro.put("descripcion", descripcion);
            registro.put("imagen", imagen);

            bbdd.insert("notas", null, registro);

            bbdd.close();
            et_categoria.setText("");
            et_titulo.setText("");
            et_descripcion.setText("");

            Toast.makeText(this, "Se ha registrado correctamente", Toast.LENGTH_SHORT).show();

            //Intent para volver
            Intent intent = new Intent(this, PersonalListView.class);
            startActivity(intent);

        } else {
            Toast.makeText(this, "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}