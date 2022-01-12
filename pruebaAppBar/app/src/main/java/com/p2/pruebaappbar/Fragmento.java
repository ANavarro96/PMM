package com.p2.pruebaappbar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class Fragmento extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Disco> listaDiscos = new ArrayList<>();
    ArrayList<Disco> listaAMostrar = new ArrayList<>();
    String filtro;
    AdaptadorDisco adaptadorDisco;

    public ArrayList<Disco> getListaDiscos() {
        return listaDiscos;
    }

    public void setListaDiscos(ArrayList<Disco> listaDiscos) {
        this.listaDiscos = listaDiscos;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    /*
     * Creamos nuestra funcion que captura el evento de cuando se pulse
     * un elemento de la lista, que será mostrar el TOAST.
     *
     */
    private AdaptadorDisco.listenersInterfaz funcion = new AdaptadorDisco.listenersInterfaz() {
        @Override
        public void clickEnElemento(int pos) {
            Toast.makeText(getActivity().getApplicationContext(), listaDiscos.get(pos).getNombre(), Toast.LENGTH_SHORT).show();
        }
    };

    public Fragmento() {
    }

    public Fragmento(ArrayList<Disco> listaDiscos,String filtro) {
        this.filtro = filtro;
        this.listaDiscos = listaDiscos;
    }

    public Fragmento(int contentLayoutId) {
        super(contentLayoutId);
    }


    public void updateRecyclerView(ArrayList<Disco> listaDiscos){
        this.listaDiscos = listaDiscos;
        listaAMostrar.clear();
        for(Disco d:listaDiscos){
            if(d.getNombre().equals(filtro)) listaAMostrar.add(d);
        }
        adaptadorDisco.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment, container, false);

        recyclerView = v.findViewById(R.id.rw);
        listaAMostrar.clear();
        for(Disco d:listaDiscos){
            if(d.getNombre().equals(filtro)) listaAMostrar.add(d);
        }

        // Creamos nuestro adaptador personalizado, y le pasamos la lista de Disco
        adaptadorDisco = new AdaptadorDisco(listaAMostrar,funcion);

        recyclerView.setHasFixedSize(true);

        // Con esto especificamos que nuestra lista se va a ver en vertical.
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Con esto especificaríamos que se vería como si fuese una tabla
        // recyclerView.setLayoutManager(new GridLayoutManager(this,3));


        // DividerItemDecoration nos permite añadir lineas de separación entre elementos,
        // Tanto verticales como horizontales
        recyclerView.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        // Establecemos nuestr adaptador al recyclerView
        recyclerView.setAdapter(adaptadorDisco);

        return v;
    }
}
