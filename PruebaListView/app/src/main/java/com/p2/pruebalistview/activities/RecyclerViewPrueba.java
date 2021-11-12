package com.p2.pruebalistview.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.p2.pruebalistview.R;
import com.p2.pruebalistview.adapters.AdaptadorDisco;
import com.p2.pruebalistview.adapters.EventsInterface;
import com.p2.pruebalistview.model.Disco;

import java.util.ArrayList;
import java.util.Random;

public class RecyclerViewPrueba extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Disco> listaDiscos = new ArrayList<>();
    Button crear,borrar,modificar;
    AdaptadorDisco adaptadorDisco;

    /*
     * Creamos nuestra funcion que captura el evento de cuando se pulse
     * un elemento de la lista, que será mostrar el TOAST.
     *
     */
    private EventsInterface funcion = new EventsInterface() {
        @Override
        public void clickEnElemento(int pos) {
            Toast.makeText(getApplicationContext(), listaDiscos.get(pos).getNombre(), Toast.LENGTH_SHORT).show();
        }
    };

    /*
     * Creo el evento que asignaré al botón de crear.
     * Utilizo la función add(indice, objeto) para ponerlo AL PRINCIPIO de la lista
     * Si sólo uso add(objeto) me lo pone y muestra al final de la lista
     *
     */
    private View.OnClickListener funcionCrear = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            listaDiscos.add(0,new Disco("Disco Creado","autor inventado", R.drawable.tgg));
            adaptadorDisco.notifyItemInserted(0);
        }
    };

    /* Para modificar un elemento de la lista, modifico su valor en el array, y llamo al método de
        notifyItemChanged
     */
    private View.OnClickListener funcionMod = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            int posicionAzar = new Random().nextInt(listaDiscos.size());
            listaDiscos.set(posicionAzar,new Disco("Disco CAMBIADO!!","autor CAMBIADO!!",R.drawable.ncth));
            adaptadorDisco.notifyItemChanged(posicionAzar);
        }

    };

    /*
     * Borro el elemento que está al PRINCIPIO de la lista, es decir, el elemento 0.
     * Y notifico al adaptador
     *
     */
    private View.OnClickListener funcionBorrar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(listaDiscos.size() > 1) {
                listaDiscos.remove(0);
                adaptadorDisco.notifyItemRemoved(0);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Nuestra actividad principal está formada por un recyclerView, así que lo enlazamos desde
        // la interfaz
        recyclerView = findViewById(R.id.rw);
        crear= findViewById(R.id.crear);
        modificar = findViewById(R.id.modificar);
        borrar = findViewById(R.id.borrar);

        crear.setOnClickListener(funcionCrear);
        borrar.setOnClickListener(funcionBorrar);
        modificar.setOnClickListener(funcionMod);
        // Creamos una lista de valores inventadas
        for (int i = 0; i < 5; i++) {
            listaDiscos.add(new Disco("The Greatest Generation", "The Wonder Years", R.drawable.tgg));
            listaDiscos.add(new Disco("Suburbia", "The Wonder Years", R.drawable.suburbia));
            listaDiscos.add(new Disco("Sister cities", "The Wonder Years", R.drawable.sc));
            listaDiscos.add(new Disco("No closer to heaven", "The Wonder Years", R.drawable.ncth));
        }

        // Creamos nuestro adaptador personalizado, y le pasamos la lista de Disco
        adaptadorDisco = new AdaptadorDisco(listaDiscos,funcion);

        // Este método especifica si un RW tiene un número de datos estático,
        // es decir, que no variarán.
        //recyclerView.setHasFixedSize(true);

        // Con esto especificamos que nuestra lista se va a ver en vertical.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Con esto especificaríamos que se vería como si fuese una tabla
        // recyclerView.setLayoutManager(new GridLayoutManager(this,3));


        // DividerItemDecoration nos permite añadir lineas de separación entre elementos,
        // Tanto verticales como horizontales
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        // Establecemos nuestr adaptador al recyclerView
        recyclerView.setAdapter(adaptadorDisco);



    }


}