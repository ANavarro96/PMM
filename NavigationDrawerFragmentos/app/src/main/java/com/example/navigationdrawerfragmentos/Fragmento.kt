package com.example.navigationdrawerfragmentos

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment

class Fragmento(private var imagen: Int) : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflamos el fragmento, es decir, le asignamos la interfaz en tiempo de ejecución
        val v =  inflater.inflate(R.layout.fragmento_layout, container, false)
        val iv = v.findViewById<ImageView>(R.id.imageView)
        iv.setImageResource(imagen)
        return v
    }

}