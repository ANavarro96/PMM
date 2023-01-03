package com.example.navigationdrawerfragmentos

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment


class FragmentoElaborado() : Fragment() {

    private var imagen: Int = 0
    private lateinit var mensaje: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imagen = it.getInt(Companion.IMAGEN)
            mensaje = it.getString(MENSAJE).toString()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflamos el fragmento, es decir, le asignamos la interfaz en tiempo de ejecución
        return inflater.inflate(R.layout.fragmento_elaborado_layout, container, false)
    }

    // Este método actúa como el OnCreate de una actividad normal:
    // - Hacer match con variables
    // - Cualquioer tipo de inicialización de la interfaz..
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val iv = view.findViewById<ImageView>(R.id.imageView)
        val texto = view.findViewById<TextView>(R.id.mensaje)
        iv.setImageResource(imagen)
        texto.text = mensaje
    }

    companion object {
        const val IMAGEN = "imagen"
        const val MENSAJE = "mensaje"
    }
}