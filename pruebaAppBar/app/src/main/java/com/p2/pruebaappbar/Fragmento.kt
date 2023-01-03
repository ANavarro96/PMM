package com.p2.pruebaappbar

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.fragment.app.Fragment
import kotlin.collections.ArrayList

class Fragmento(var listaDiscos: ArrayList<Disco>, var filtro: String) : Fragment() {
    lateinit var recyclerView: RecyclerView
    var listaAMostrar = ArrayList<Disco>()
    lateinit var adaptadorDisco: AdaptadorDisco


    fun updateRecyclerView(listaDiscos: ArrayList<Disco>) {
        this.listaDiscos = listaDiscos
        listaAMostrar.clear()
        for (d in listaDiscos) {
            if (d.nombre == filtro) listaAMostrar.add(d)
        }
        adaptadorDisco.notifyDataSetChanged()
    }


    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment, container, false)
        recyclerView = v.findViewById(R.id.rw)
        listaAMostrar.clear()
        for (d in listaDiscos) {
            if (d.nombre == filtro) listaAMostrar.add(d)
        }

        // Creamos nuestro adaptador personalizado, y le pasamos la lista de Disco
        adaptadorDisco = AdaptadorDisco(listaAMostrar)

        // Con esto especificamos que nuestra lista se va a ver en vertical.
        recyclerView.setLayoutManager(LinearLayoutManager(context))

        // Con esto especificaríamos que se vería como si fuese una tabla
        // recyclerView.setLayoutManager(new GridLayoutManager(this,3));


        // DividerItemDecoration nos permite añadir lineas de separación entre elementos,
        // Tanto verticales como horizontales
        recyclerView.addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        // Establecemos nuestr adaptador al recyclerView
        recyclerView.setAdapter(adaptadorDisco)
        return v
    }
}