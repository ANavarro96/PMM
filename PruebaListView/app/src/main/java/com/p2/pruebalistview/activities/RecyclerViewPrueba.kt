package com.p2.pruebalistview.activities

import com.p2.pruebalistview.model.Disco
import com.p2.pruebalistview.adapters.EventsInterface
import androidx.recyclerview.widget.RecyclerView
import com.p2.pruebalistview.R
import androidx.appcompat.app.AppCompatActivity
import com.p2.pruebalistview.adapters.AdaptadorDisco
import android.widget.Toast
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import java.util.*

class RecyclerViewPrueba : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView 
    var listaDiscos = ArrayList<Disco>()
    lateinit var crear: Button 
    lateinit var borrar: Button 
    lateinit var modificar: Button 
    lateinit var adaptadorDisco: AdaptadorDisco 

    /*
     * Creamos nuestra funcion que captura el evento de cuando se pulse
     * un elemento de la lista, que será mostrar el TOAST.
     *
     */
    private val funcion = object : EventsInterface {
        override fun clickEnElemento(pos: Int) {
            Toast.makeText(
                applicationContext, listaDiscos.get(pos).nombre, Toast.LENGTH_SHORT
            ).show()
        }
    }

    /*
     * Creo el evento que asignaré al botón de crear.
     * Utilizo la función add(indice, objeto) para ponerlo AL PRINCIPIO de la lista
     * Si sólo uso add(objeto) me lo pone y muestra al final de la lista
     *
     */
    private val funcionCrear = View.OnClickListener {
        listaDiscos.add(0, Disco("Disco Creado", "autor inventado", R.drawable.tgg))
        adaptadorDisco.notifyItemInserted(0)
    }

    /* Para modificar un elemento de la lista, modifico su valor en el array, y llamo al método de
        notifyItemChanged
     */
    private val funcionMod = View.OnClickListener {
        val posicionAzar = Random().nextInt(listaDiscos.size)
        listaDiscos[posicionAzar] = Disco("Disco CAMBIADO",
            "autor CAMBIADO", R.drawable.ncth)
        adaptadorDisco.notifyItemChanged(posicionAzar)
    }

    /*
     * Borro el elemento que está al PRINCIPIO de la lista, es decir, el elemento 0.
     * Y notifico al adaptador
     *
     */
    private val funcionBorrar = View.OnClickListener {
        if (listaDiscos.size > 1) {
            listaDiscos.removeAt(0)
            adaptadorDisco.notifyItemRemoved(0)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Nuestra actividad principal está formada por un recyclerView, así que lo enlazamos desde
        // la interfaz
        recyclerView = findViewById(R.id.rw)
        crear = findViewById(R.id.crear)
        modificar = findViewById(R.id.modificar)
        borrar = findViewById(R.id.borrar)
        crear.setOnClickListener(funcionCrear)
        borrar.setOnClickListener(funcionBorrar)
        modificar.setOnClickListener(funcionMod)
        // Creamos una lista de valores inventadas
        for (i in 0..4) {
            listaDiscos.add(Disco("The Greatest Generation", "The Wonder Years", R.drawable.tgg))
            listaDiscos.add(Disco("Suburbia", "The Wonder Years", R.drawable.suburbia))
            listaDiscos.add(Disco("Sister cities", "The Wonder Years", R.drawable.sc))
            listaDiscos.add(Disco("No closer to heaven", "The Wonder Years", R.drawable.ncth))
        }

        // Creamos nuestro adaptador personalizado, y le pasamos la lista de Disco
        adaptadorDisco = AdaptadorDisco(listaDiscos, funcion)

        // Este método especifica si un RW tiene un número de datos estático,
        // es decir, que no variarán.
        //recyclerView.setHasFixedSize(true);

        // Con esto especificamos que nuestra lista se va a ver en vertical.
        recyclerView.setLayoutManager(LinearLayoutManager(this))

        // Con esto especificaríamos que se vería como si fuese una tabla
        // recyclerView.setLayoutManager(GridLayoutManager(this,3));


        // Con esto especificaríamos que se vería como si fuese una tabla pero con elementos
        // desiguales
         //recyclerView.setLayoutManager(StaggeredGridLayoutManager(2,LinearLayout.VERTICAL));


        // DividerItemDecoration nos permite añadir lineas de separación entre elementos,
        // Tanto verticales como horizontales
        recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )


        // Establecemos nuestr adaptador al recyclerView
        recyclerView.setAdapter(adaptadorDisco)
    }
}