package com.p2.pruebalistview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DiscoViewHolder extends RecyclerView.ViewHolder {
    TextView nombreCuadroTexto;
    TextView autorCuadroTexto;
    ImageView portadaIW;
    public DiscoViewHolder(@NonNull View itemView) {
        super(itemView);
        this.nombreCuadroTexto = itemView.findViewById(R.id.nombre);
        this.autorCuadroTexto = itemView.findViewById(R.id.autor);
        this.portadaIW = itemView.findViewById(R.id.portada);
    }
}
