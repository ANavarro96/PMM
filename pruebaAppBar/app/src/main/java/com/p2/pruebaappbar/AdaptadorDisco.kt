package com.p2.pruebaappbar

import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.ImageView
import com.p2.pruebaappbar.AdaptadorDisco.DiscoViewHolder
import android.widget.TextView
import java.util.ArrayList

class AdaptadorDisco(var listaDiscos: ArrayList<Disco>) : RecyclerView.Adapter<DiscoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoViewHolder {
        /**
         * La clase View es un método abstracto de Android que representa cualquier elemento gráfico,
         * cualquier cosa que salga por pantalla.
         *
         * La clase LayoutInflater nos permite asignar un layout (un xml) en tiempo de ejecución, de forma dinámica.
         * Usamos una instancia para asignar nuestro layout (layout_disco) al elemento, cada vez que se
         * cree uno nuevo.
         */
        val item = LayoutInflater.from(parent.context).inflate(R.layout.layout_disco, parent, false)
        return DiscoViewHolder(item)
    }

    /**
     * Este evento ocurrirá cuando se tenga que actualizar la información de un elemento
     *
     */
    override fun onBindViewHolder(holder: DiscoViewHolder, position: Int) {
        val discoActual = listaDiscos[position]
        holder.autorCuadroTexto.text = discoActual.autor
        holder.nombreCuadroTexto.text = discoActual.nombre
        holder.portadaIW.setImageResource(discoActual.portada)
    }

    override fun getItemCount(): Int {
        return listaDiscos.size
    }

    /*
        La clase DiscoViewHolder es la que representa el layout de cada elemento de la lista.
        En los ViewHolder declararemos nuestras variables Java que se enlazarán con los
        controles de la interfaz que hayamos creado. En este ejemplo, layout_disco
     */
    inner class DiscoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var nombreCuadroTexto: TextView
        var autorCuadroTexto: TextView
        var portadaIW: ImageView


        override fun onClick(view: View) {
            val position = adapterPosition
            Toast.makeText(view.context, listaDiscos[position].nombre, Toast.LENGTH_SHORT).show()
        }

        // Constructor por defecto, se le pasa una instancia de la clase View
        init {
            // Se utiliza este View para enlazar el XML con Java
            nombreCuadroTexto = itemView.findViewById(R.id.nombre)
            autorCuadroTexto = itemView.findViewById(R.id.autor)
            portadaIW = itemView.findViewById(R.id.portada)
            itemView.setOnClickListener(this)
        }
    }
}