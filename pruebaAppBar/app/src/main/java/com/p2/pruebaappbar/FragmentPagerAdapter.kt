package com.p2.pruebaappbar


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.util.*
import kotlin.collections.ArrayList

class FragmentPagerAdapter  /*Constructor por defecto:
    * fm: El objeto Java que se encarga de gestionar los Fragment: crearlos, borrarlos, pararlos...
    * behavior: Puede ser BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT o BEHAVIOR_SET_USER_VISIBLE_HINT */
(fm: FragmentManager?, behavior: Int) : FragmentStatePagerAdapter(fm!!, behavior) {
    /* Este Array representa los títulos de las pestañas */
    var misTabs = ArrayList(Arrays.asList("Suburbia", "NCTH", "TGG", "Sister cities", "Disco Creado", "Disco CAMBIADO!!"))

    /*Lista de los discos que se están mostrando */
    var listaDiscos: ArrayList<Disco> = ArrayList()

    override fun getItem(position: Int): Fragment {
        /*Devolvemos el fragmento que esté en la posición position.
        Para ello creamos un nuevo Fragmento y le pasamos la lista de los discos total y el filtro por el que
        discernir
         */
        return Fragmento(listaDiscos, getPageTitle(position).toString())
    }

    override fun getCount(): Int {
        return misTabs.size
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return misTabs[position]
    }

    /*Forma un poco más compleja de sobreescribir el objeto:
    Lo que se hace es modificar el fragmento que se ha modificado, es decir, el que se ha notificado que ha
    habido un cambio, y actualizar la lista de discos.
    Véase la función updateRecyclerView de la clase Fragmento, que lo que hace es regenerar la lista
    en el recyclerView.
     */
    override fun getItemPosition(elemento: Any): Int {
        if (elemento is Fragmento) elemento.updateRecyclerView(listaDiscos)
        return super.getItemPosition(elemento)
    } /*
    Otra forma más sencilla de implementar el evento
    @Override
    public int getItemPosition(@NonNull Obj
    ect object) {
        return POSITION_NONE;
    }

     */
}