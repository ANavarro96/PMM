package com.p2.pruebaappbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.Arrays;

public class FragmentPagerAdapter extends androidx.fragment.app.FragmentStatePagerAdapter  {

    /* Este Array representa los títulos de las pestañas */
    ArrayList<String> misTabs = new ArrayList<>(Arrays.asList("Suburbia","NCTH","TGG","Sister cities"
    ,"Disco Creado","Disco CAMBIADO!!"));

    /*Lista de los discos que se están mostrando */
    ArrayList<Disco> listaDiscos;

    public ArrayList<Disco> getListaDiscos() {
        return listaDiscos;
    }

    public void setListaDiscos(ArrayList<Disco> listaDiscos) {
        this.listaDiscos = listaDiscos;
    }

    /*Constructor por defecto:
    * fm: El objeto Java que se encarga de gestionar los Fragment: crearlos, borrarlos, pararlos...
    * behavior: Puede ser BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT o BEHAVIOR_SET_USER_VISIBLE_HINT */
    public FragmentPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        /*Devolvemos el fragmento que esté en la posición position.
        Para ello creamos un nuevo Fragmento y le pasamos la lista de los discos total y el filtro por el que
        discernir
         */
        return new Fragmento(listaDiscos,getPageTitle(position).toString());
    }

    @Override
    public int getCount() {
        return misTabs.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return misTabs.get(position);
    }

    /*Forma un poco más compleja de sobreescribir el objeto:
    Lo que se hace es modificar el fragmento que se ha modificado, es decir, el que se ha notificado que ha
    habido un cambio, y actualizar la lista de discos.
    Véase la función updateRecyclerView de la clase Fragmento, que lo que hace es regenerar la lista
    en el recyclerView.
     */
    @Override
    public int getItemPosition(@NonNull Object object) {
        if(object instanceof Fragmento) ((Fragmento) object).updateRecyclerView(listaDiscos);
        return super.getItemPosition(object);
    }

    /*
    Otra forma más sencilla de implementar el evento
    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

     */
}
