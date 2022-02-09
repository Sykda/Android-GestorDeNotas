package com.example.gestordenotas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton button;
    private ListView lista;
    private int iconos[];
    private ArrayList<String> listaInformacion;
    private AdminSQLiteOpenHelper conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button= findViewById(R.id.floatingActionButton);
        lista=findViewById(R.id.listview);

        //Generamos la instancia de la base de datos
        conn=new AdminSQLiteOpenHelper(getApplicationContext(), "administracion", null, 1);

        //Metodo para consultar la lista de notas
        consultarListaNotas();

        //Adaptador para meter el array en el listview
        //ListViewAdapter adaptadorDeLista = new ListViewAdapter(this, listaInformacion, iconos);
        //lista.setAdapter(adaptadorDeLista);

        //Adaptador de prueba
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInformacion);
        lista.setAdapter(adapter);
    }

    private void consultarListaNotas() {
        SQLiteDatabase db=conn.getReadableDatabase();
        listaInformacion= new ArrayList<String>();

        Cursor cursor=db.rawQuery("SELECT * FROM notas", null);

        while(cursor.moveToNext()){

            listaInformacion.add(cursor.getString(0));
            listaInformacion.add(cursor.getString(1));
            listaInformacion.add(cursor.getString(2));
        }
    }

    public void registro(View view){

            //Intent para pasar de pantalla
            Intent intent = new Intent(this, Registro.class);
            startActivity(intent);


    }


}