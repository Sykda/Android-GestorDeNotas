package com.example.gestordenotas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton button;
    private ListView lista;
    int iconos[];
    ArrayList<String> titulos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button= findViewById(R.id.floatingButton);
        lista=findViewById(R.id.listview);


        //Adaptador para meter el array en el listview
        ListViewAdapter adaptadorDeLista = new ListViewAdapter(this, titulos, iconos);
        lista.setAdapter(adaptadorDeLista);
    }
}