package com.example.navigationdrawerfragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragmento_elaborado extends Fragment{

    private int imagen;
    private String mensaje;

    public Fragmento_elaborado(int imagen,String mensaje1){
        this.imagen = imagen;
        this.mensaje = mensaje1;
    }

    public Fragmento_elaborado(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflamos el fragmento, es decir, le asignamos la interfaz en tiempo de ejecución
        return inflater.inflate(R.layout.fragmento_elaborado_layout, container, false);
    }

    @Override
    // Este método actúa como el OnCreate de una actividad normal:
    // - Hacer match con variables
    // - Cualquioer tipo de inicialización de la interfaz..
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView iv = view.findViewById(R.id.imageView);
        TextView texto = view.findViewById(R.id.mensaje);

        iv.setImageResource(this.imagen);
        texto.setText(this.mensaje);
    }
}
