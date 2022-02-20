package com.example.gestordenotas;

import android.app.ActivityOptions;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

//Este fragment se usa para actualizar un registro.
public class MyDialogFragment extends DialogFragment {

    private Spinner spinner;
    private EditText et_titulo, et_descripcion;
    private int imagen;
    private String categoria;
    private Button button;
    private AdminSQLiteOpenHelper bbddAdministrador;

    static MyDialogFragment newInstance() {

        return new MyDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_layout, container, false);

        //Referencias.
        et_titulo = view.findViewById(R.id.titulo);
        et_descripcion = view.findViewById(R.id.descripcion);

        //Bundle que trae los objetos enviados desde los Activity.
        Bundle bundle = this.getArguments();
        int position = bundle.getInt("position", 0);

        //Spinner.
        spinner = view.findViewById(R.id.spinner);
        String[] opciones = {"Aviso", "Reunion", "Varios"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (getContext(), R.layout.spinner_layout, opciones);
        spinner.setAdapter(adapter);

        //Lo que pasa cuando seleccionas algo del spinner.
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> spn,
                                               android.view.View v,
                                               int posicion,
                                               long id) {

                        String select = spinner.getSelectedItem().toString();

                        if (select.equals("Aviso")) {

                            categoria = "Aviso";
                            imagen = 0;

                        } else if (select.equals("Reunion")) {

                            categoria = "Reunion";
                            imagen = 1;

                        } else if (select.equals("Varios")) {

                            categoria = "Varios";
                            imagen = 2;

                        }
                    }

                    public void onNothingSelected(AdapterView<?> spn) {
                    }
                });

        button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bbddUpdate((Methods.getListaTareas().get(position)).getId());
            }
        });

        return view;

    }

    //Método para actualizar un registro.
    public void bbddUpdate(int id) {

        bbddAdministrador = new AdminSQLiteOpenHelper(getContext(), "administracion", null, 1);
        SQLiteDatabase BaseDatabase = bbddAdministrador.getWritableDatabase();

        String titulo = et_titulo.getText().toString();
        String descripcion = et_descripcion.getText().toString();

        if (!titulo.isEmpty() && !descripcion.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put("categoria", categoria);
            registro.put("titulo", titulo);
            registro.put("descripcion", descripcion);
            registro.put("imagen", imagen);

            BaseDatabase.update("notas", registro, "id= " + id, null);
            BaseDatabase.close();

            Toast.makeText(getContext(), "Artículo modificado", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getContext(), PersonalListView.class), ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());

        } else {
            Toast.makeText(getContext(), "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();

        }
    }
}