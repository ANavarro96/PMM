package com.p2.pruebaappbar


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import java.util.*
import kotlin.collections.ArrayList

/*Constructor por defecto:
    * fm: Una subclase de Activity que representa un fragmento
*/
class FragmentPagerAdapter
(var fm: FragmentActivity) : FragmentStateAdapter(fm){
    /* Este Array representa los títulos de las pestañas */
    var misTabs = ArrayList(Arrays.asList("Suburbia", "NCTH", "TGG", "Sister cities", "Disco Creado", "Disco CAMBIADO!!"))

    /*Lista de los discos que se están mostrando */
    var listaDiscos: ArrayList<Disco> = ArrayList()

    /*Devolvemos el fragmento que esté en la posición position.
      Para ello creamos un nuevo Fragmento y le pasamos la lista de los discos total y el filtro por el que
      discernir.
      Lo normal será que tengamos que usar una estructura condicional para elegir que fragmento
      hay que renderizar.
   */
    override fun createFragment(position: Int): Fragment {
        return Fragmento(listaDiscos, getPageTitle(position).toString())
    }

    /*
    * Esta función devuelve el numero de items (pestañas)
    */
    override fun getItemCount(): Int {
        return misTabs.size
    }


    // Esta función devuelve el nombre de la pestaña asociada a positon.
    fun getPageTitle(position: Int): CharSequence? {
        return misTabs[position]
    }

    /* Este método es similar al que hay implementado en el RecyclerView, aquí se llamará cada vez
     * que se llame a algún método de notificación.
     * Recibe la pestaña pulsada (position) y los datos que se han modificado (payloads).
     * Cada pestaña, internamente, se identifica con f seguido de un número, representando la posición
     * de la pestaña -> f0,f1..
     */
    override fun onBindViewHolder(
            holder: FragmentViewHolder,
            position: Int,
            payloads: MutableList<Any>
    ) {
        val tag = "f$position"
        // Se obtiene el fragmento que está siendo mostrado actualmente
        val fragment = fm.supportFragmentManager.findFragmentByTag(tag)
        if (fragment != null) {
                // Se obtiene y se refresca manualmente.
                (fragment as Fragmento).actualizarLista(payloads[0] as ArrayList<Disco>)
        } else {
                super.onBindViewHolder(holder, position, payloads)
        }
    }

    /* Siguiendo una estructura similar al método anterior, obtendo el fragmento actual, y se llama
     * al método borrarElemento()
     */
    fun borrarElemento(pestanya: Int){
        val tag = "f$pestanya"
        val fragment = fm.supportFragmentManager.findFragmentByTag(tag)
        (fragment as Fragmento).borrarElemento()

    }

}