package com.example.gestordenotas;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class PersonalListView extends AppCompatActivity {

    private final Methods methods = Methods.getInstance();
    private ListView listView;
    private ListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        //Referencias.
        listView = findViewById(R.id.my_listView);

        //Generamos la instancia de la base de datos.
        new AdminSQLiteOpenHelper(getApplicationContext(), "administracion", null, 1);

        //Metodo para consultar la lista de notas.
        methods.consultarListaNotas(this);

        //Adaptador para meter el array en el listview.
        listViewAdapter = new ListViewAdapter(this, Methods.getListaTareas(), methods.getIconos());
        listView.setAdapter(listViewAdapter);

        //Selecciono lo que va a pasar con un click prolongado en el objeto.
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> list, View view, int position, long id) {
                removeItem(position);
                return true;
            }
        });

        //Selecciono lo que va a pasar con un click simple en el objeto.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int position, long id) {
                showDescription(position);
            }
        });

    }

    //Método para ir a la pantalla del registro.
    public void registro(View view) {
        //Intent para pasar de pantalla
        startActivity(new Intent(this, Registro.class));
    }

    //Método para borrar un elemento de la bbdd y de la lista. Usa "fragment".
    public void removeItem(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.confirm);
        builder.setMessage("¿Quieres borrar esta nota?");
        builder.setPositiveButton(R.string.remove, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                methods.bbddDelete(getApplicationContext(), (Methods.getListaTareas().get(position)).getId());
                Methods.getListaTareas().remove(position);
                listViewAdapter.notifyDataSetChanged();//Se notifica al adaptador los cambios
            }
        });

        builder.setNegativeButton(android.R.string.cancel, null);
        builder.create().show();
    }

    //Metodo para mostrar la descripción. Usa "fragment".
    public void showDescription(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("Descripción")
                .setMessage(Methods.getListaTareas().get(position).getDescripcion())
                .setPositiveButton(R.string.update, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Creamos un FragmentManager y un FragmentTransaction para usar nuestro fragment personalizado.
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();

                        //Creamos una instancia del Fragment personalizado con un Bundle donde le pasamos argumentos.
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

    //Método para cambiar de vista.
    public void cambiaVista(View view) {
        //Intent para pasar de pantalla
        startActivity(new Intent(this, PersonalGridView.class),
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}