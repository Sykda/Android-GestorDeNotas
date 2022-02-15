package com.example.gestordenotas;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Registro extends AppCompatActivity {

    private Spinner spinner;
    private EditText et_titulo, et_descripcion;
    private int imagen;
    private String categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//Boton "<-"

        //Referencias
        et_titulo = findViewById(R.id.titulo);
        et_descripcion = findViewById(R.id.descripcion);


        //Spinner
        spinner = findViewById(R.id.spinner);
        String[] opciones = {"Aviso", "Reunion", "Varios"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, R.layout.spinner_layout, opciones);
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

                            categoria="Aviso";
                            imagen=0;

                        } else if (select.equals("Reunion")) {

                            categoria="Reunion";
                            imagen=1;

                        } else if (select.equals("Varios")) {

                            categoria="Varios";
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

        //String categoria;
        String titulo = et_titulo.getText().toString();
        String descripcion = et_descripcion.getText().toString();


        if (!titulo.isEmpty() && !descripcion.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put("categoria", categoria);
            registro.put("titulo", titulo);
            registro.put("descripcion", descripcion);
            registro.put("imagen", imagen);

            bbdd.insert("notas", null, registro);

            bbdd.close();
            et_titulo.setText("");
            et_descripcion.setText("");

            Toast.makeText(this, "Se ha registrado correctamente", Toast.LENGTH_SHORT).show();

            //Intent para volver
            startActivity(new Intent(this, PersonalListView.class));

        } else {
            Toast.makeText(this, "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    //Boton "<-"
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //hago un case por si en un futuro agrego mas opciones
                Log.i("ActionBar", "Atrás!");
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}