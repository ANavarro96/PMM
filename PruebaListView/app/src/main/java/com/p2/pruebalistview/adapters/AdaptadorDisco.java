package com.p2.pruebalistview.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.p2.pruebalistview.R;
import com.p2.pruebalistview.model.Disco;

import java.util.ArrayList;

public class AdaptadorDisco extends RecyclerView.Adapter<AdaptadorDisco.DiscoViewHolder> {
    private ArrayList<Disco> listaDiscos;
    private EventsInterface mOnClickListener;


    public AdaptadorDisco(ArrayList<Disco> listaDiscos, EventsInterface mOnClickListener) {
        this.listaDiscos = listaDiscos;
        this.mOnClickListener = mOnClickListener;
    }

    public ArrayList<Disco> getListaDiscos() {
        return listaDiscos;
    }
    public void setListaDiscos(ArrayList<Disco> listaDiscos) {
        this.listaDiscos = listaDiscos;
    }



    @NonNull
    @Override
    public DiscoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /**
         * La clase View es un método abstracto de Android que representa cualquier elemento gráfico,
         * cualquier cosa que salga por pantalla.
         *
         * La clase LayoutInflater nos permite asignar un layout (un xml) en tiempo de ejecución, de forma dinámica.
         * Usamos una instancia para asignar nuestro layout (layout_disco) al elemento, cada vez que se
         * cree uno nuevo. NO ES EL LAYOUT DE LA ACTIVIDAD, SI NO EL QUE REPRESENTE CADA ELEMENTO.
         *
         * Este método se llamará cada vez que añadamos un elemnto a nuestro array o notifiquemos
         * de algún cambio.
         */
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_disco, parent, false);
        return new DiscoViewHolder(item);
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
    @Override
    public void onBindViewHolder(@NonNull DiscoViewHolder holder, int position) {
            Disco discoActual = listaDiscos.get(position);
            holder.getAutorCuadroTexto().setText(discoActual.getAutor());
            holder.getNombreCuadroTexto().setText(discoActual.getNombre());
            holder.getPortadaIW().setImageResource(discoActual.getPortada());
    }

    /* Devuelve el número de elementos de nuestro array */
    @Override
    public int getItemCount() {
        return listaDiscos.size();
    }

    /*
           La clase DiscoViewHolder es la que gestiona el layout de cada elemento de la lista.
           En los ViewHolder declararemos nuestras variables Java que se enlazarán con los
           controles de la interfaz que hayamos creado. En este ejemplo, layout_disco
        */
    public class DiscoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nombreCuadroTexto;
        TextView autorCuadroTexto;
        ImageView portadaIW;


        public TextView getNombreCuadroTexto() {
            return nombreCuadroTexto;
        }

        public void setNombreCuadroTexto(TextView nombreCuadroTexto) {
            this.nombreCuadroTexto = nombreCuadroTexto;
        }

        public TextView getAutorCuadroTexto() {
            return autorCuadroTexto;
        }

        public void setAutorCuadroTexto(TextView autorCuadroTexto) {
            this.autorCuadroTexto = autorCuadroTexto;
        }

        public ImageView getPortadaIW() {
            return portadaIW;
        }

        public void setPortadaIW(ImageView portadaIW) {
            this.portadaIW = portadaIW;
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            mOnClickListener.clickEnElemento(position);
        }

        // Constructor por defecto, se le pasa una instancia de la clase View
        public DiscoViewHolder(@NonNull View itemView) {
            super(itemView);
            // Se utiliza este View para enlazar el XML con Java
            this.nombreCuadroTexto = itemView.findViewById(R.id.nombre);
            this.autorCuadroTexto = itemView.findViewById(R.id.autor);
            this.portadaIW = itemView.findViewById(R.id.portada);
            itemView.setOnClickListener(this);
        }
    }
}
