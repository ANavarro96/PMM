package com.p2.pruebalistview.adapters

import com.p2.pruebalistview.model.Disco
import com.p2.pruebalistview.adapters.EventsInterface
import androidx.recyclerview.widget.RecyclerView
import com.p2.pruebalistview.adapters.AdaptadorDisco.DiscoViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import com.p2.pruebalistview.R
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.p2.pruebalistview.adapters.AdaptadorDisco
import android.widget.Toast
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DividerItemDecoration
import java.util.ArrayList

class AdaptadorDisco(var listaDiscos: ArrayList<Disco>,private val mOnClickListener: EventsInterface) : RecyclerView.Adapter<DiscoViewHolder>() {


    /*
       La clase DiscoViewHolder es la que gestiona el layout de cada elemento de la lista.
       En los ViewHolder declararemos nuestras variables Java que se enlazarán con los
       controles de la interfaz que hayamos creado. En este ejemplo, layout_disco
    */
    inner class DiscoViewHolder(itemView: View) : ViewHolder(itemView), View.OnClickListener {
        var nombreCuadroTexto: TextView
        var autorCuadroTexto: TextView
        var portadaIW: ImageView

        override fun onClick(view: View) {
            val position = absoluteAdapterPosition
            mOnClickListener.clickEnElemento(position)
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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoViewHolder {
        /**
         * La clase ViewGroup es un método abstracto de Android que representa cualquier elemento gráfico,
         * cualquier cosa que salga por pantalla.
         *
         * La clase LayoutInflater nos permite asignar un layout (un xml) en tiempo de ejecución, de forma dinámica.
         * Usamos una instancia para asignar nuestro layout (layout_disco) al elemento, cada vez que se
         * cree uno nuevo. NO ES EL LAYOUT DE LA ACTIVIDAD, SI NO EL QUE REPRESENTE CADA ELEMENTO.
         *
         * Este método se llamará cada vez que añadamos un elemnto a nuestro array o notifiquemos
         * de algún cambio.
         */
        val item = LayoutInflater.from(parent.context).inflate(R.layout.layout_disco, parent, false)
        return DiscoViewHolder(item)
    }

    /**
     * Este evento ocurrirá cuando se tenga que actualizar la información de un elemento
     * La posición del elemento que se tenga que actualizar se pasa como parámetro
     * de forma automática.
     *
     * Normalmente, cuando añadamos un elemento al array, primero se crea el ViewHolder (lo ifnlamos,
     * y le asociamos el layout) y después se lanza el onBind, donde le asociamos valor a cada uno
     * de los elementos visuales (cuadros de texto e imágenes).
     *
     */
    override fun onBindViewHolder(holder: DiscoViewHolder, position: Int) {
        val discoActual = listaDiscos[position]
        holder.autorCuadroTexto.text = discoActual.autor
        holder.nombreCuadroTexto.text = discoActual.nombre
        holder.portadaIW.setImageResource(discoActual.portada)
    }

    /* Devuelve el número de elementos de nuestro array */
    override fun getItemCount() =  listaDiscos.size


}