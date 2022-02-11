package com.example.gestordenotas;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PersonalListView extends AppCompatActivity {

    private android.widget.ListView lista;
    private int[] iconos={R.drawable.hourglass};
    private ArrayList<Tarea> listaTareas;
    private ArrayList<String> listaStrings;
    private AdminSQLiteOpenHelper bbddAdministrador;
    private ListViewAdapter adaptadorDeLista;
    private Tarea tarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        //Referencias
        lista = findViewById(R.id.listview);

        //Generamos la instancia de la base de datos
        bbddAdministrador = new AdminSQLiteOpenHelper(getApplicationContext(), "administracion", null, 1);

        //Metodo para consultar la lista de notas
        consultarListaNotas();

        //Adaptador para meter el array en el listview
        adaptadorDeLista = new ListViewAdapter(this, listaStrings, iconos);
        lista.setAdapter(adaptadorDeLista);

        //Selecciono lo que va a pasar con un click prolongado en el objeto
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> list, View view, int position, long id) {
                removeItem(position);
                return true;
            }
        });
    }

    public void consultarListaNotas() {

        SQLiteDatabase db = bbddAdministrador.getReadableDatabase();
        listaTareas = new ArrayList<Tarea>();

        Cursor cursor = db.rawQuery("SELECT * FROM notas", null);

        while (cursor.moveToNext()) {

            //Seteamos con un constructor todos los parámetros
            tarea = new Tarea(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            listaTareas.add(tarea);
        }
        obtenerLista();
    }

    //Metodo para pasar los paramentros a una lista de String
    public void obtenerLista() {
        listaStrings = new ArrayList<String>();
        for (int i = 0; i < listaTareas.size(); i++) {
            listaStrings.add(listaTareas.get(i).getCategoria() + "," + listaTareas.get(i).getTitulo() + "," + listaTareas.get(i).getDescripcion());
        }
    }

    //Método para ir a la pantalla del registro
    public void registro(View view) {
        //Intent para pasar de pantalla
        Intent intent = new Intent(this, Registro.class);
        startActivity(intent);
    }

    //Método para borrar un elemento de la bbdd y de la lista. Usa "fragment"
    public void removeItem(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.confirm);
        builder.setMessage("¿Quieres borrar esta nota?");
        builder.setPositiveButton(R.string.remove, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                bbddAdministrador.bbddDelete(getApplicationContext(), listaTareas.get(position).getId());
                listaStrings.remove(position);
                adaptadorDeLista.notifyDataSetChanged();//Se notifica al adaptador los cambios
            }
        });

        builder.setNegativeButton(android.R.string.cancel, null);
        builder.create().show();
    }

    //Método para cambiar de vista
    public void cambiaVista(View view) {
        //Intent para pasar de pantalla
        Intent intent = new Intent(this, PersonalGridView.class);
        startActivity(intent,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }


}