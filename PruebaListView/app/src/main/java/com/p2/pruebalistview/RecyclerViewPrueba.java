package com.p2.pruebalistview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerViewPrueba extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Disco> listaDiscos = new ArrayList<>();

    private AdaptadorDisco.listenersInterfaz funcion = new AdaptadorDisco.listenersInterfaz() {
        @Override
        public void clickEnElemento(int pos) {
            Toast.makeText(getApplicationContext(), listaDiscos.get(pos).getNombre(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Nuestra actividad principal está formada por un recyclerView, así que lo enlazamos desde
        // la interfaz
        recyclerView = findViewById(R.id.rw);

        // Creamos una lista de valores inventadas
        for (int i = 0; i < 10; i++) {
            listaDiscos.add(new Disco("The Greatest Generation", "The Wonder Years", R.drawable.tgg));
            listaDiscos.add(new Disco("Suburbia", "The Wonder Years", R.drawable.suburbia));
            listaDiscos.add(new Disco("Sister cities", "The Wonder Years", R.drawable.sc));
            listaDiscos.add(new Disco("No closer to heaven", "The Wonder Years", R.drawable.ncth));
        }

        // Creamos nuestro adaptador personalizado, y le pasamos la lista de Disco
        AdaptadorDisco adaptadorDisco = new AdaptadorDisco(listaDiscos,funcion);

        recyclerView.setHasFixedSize(true);

        // Con esto especificamos que nuestra lista se va a ver en vertical.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Con esto especificaríamos que se vería como si fuese una tabla
        // recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        // Establecemos nuestr adaptador al recyclerView
        recyclerView.setAdapter(adaptadorDisco);



    }


}