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
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class PersonalListView extends AppCompatActivity {


    private android.widget.ListView listView;
    private final int[] iconos = {R.mipmap.hourglass, R.mipmap.calendar, R.mipmap.warning};
    public static ArrayList<Tarea> listaTareas;
    private ArrayList<String> listaStrings;
    private AdminSQLiteOpenHelper bbddAdministrador;
    private ListViewAdapter adaptadorDeLista;
    private Tarea tarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        //Referencias
        listView = findViewById(R.id.listview);

        //Generamos la instancia de la base de datos
        bbddAdministrador = new AdminSQLiteOpenHelper(getApplicationContext(), "administracion", null, 1);

        //Metodo para consultar la lista de notas
        consultarListaNotas();

        //Adaptador para meter el array en el listview
        adaptadorDeLista = new ListViewAdapter(this, listaStrings, iconos);
        listView.setAdapter(adaptadorDeLista);

        //Selecciono lo que va a pasar con un click prolongado en el objeto
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> list, View view, int position, long id) {
                removeItem(position);
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int position, long id) {
                showDescription(position);
            }
        });

    }

    public void consultarListaNotas() {

        SQLiteDatabase db = bbddAdministrador.getReadableDatabase();
        listaTareas = new ArrayList<Tarea>();

        Cursor cursor = db.rawQuery("SELECT * FROM notas", null);

        while (cursor.moveToNext()) {

            //Seteamos con un constructor todos los parámetros
            tarea = new Tarea(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4));
            listaTareas.add(tarea);
        }
        obtenerLista();
    }

    //Metodo para pasar los paramentros a una lista de String
    public void obtenerLista() {
        listaStrings = new ArrayList<String>();
        for (int i = 0; i < listaTareas.size(); i++) {
            listaStrings.add(listaTareas.get(i).getCategoria() + "," + listaTareas.get(i).getTitulo() + "," + listaTareas.get(i).getDescripcion() + "," + listaTareas.get(i).getImagen());
        }
    }

    //Método para ir a la pantalla del registro
    public void registro(View view) {
        //Intent para pasar de pantalla
        startActivity(new Intent(this, Registro.class));
    }

    //Método para borrar un elemento de la bbdd y de la lista. Usa "fragment"
    public void removeItem(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.confirm);
        builder.setMessage("¿Quieres borrar esta nota?");
        builder.setPositiveButton(R.string.remove, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                bbddAdministrador.bbddDelete(getApplicationContext(), ((Tarea) listaTareas.get(position)).getId());
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

    public void showDescription(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("Descripción")
                .setMessage(listaStrings.get(position).split(",")[2])
                .setPositiveButton(R.string.update, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Creamos un FragmentManager y un FragmentTransaction para usar nuestro fragment personalizado
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();

                        //Creamos una instancia del Fragment personalizado con un Bundle donde le pasamos argumentos
                        Bundle argumentos = new Bundle();
                        argumentos.putInt("position", position);
                        DialogFragment newFragment = MyDialogFragment.newInstance();
                        newFragment.setArguments(argumentos);
                        newFragment.show(ft, "tag");
                    }
                });

        builder.setNegativeButton(android.R.string.cancel, null);
        builder.create().show();
    }
}