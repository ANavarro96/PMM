package com.p2.pruebaappbar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


// Un fragmento tiene que extender de la clase Fragment()
class Fragmento(var listaDiscos: ArrayList<Disco>, var filtro: String) : Fragment() {
    lateinit var recyclerView: RecyclerView
    var listaAMostrar = ArrayList<Disco>()
    lateinit var adaptadorDisco: AdaptadorDisco

    fun borrarElemento() {
        if(listaAMostrar.size > 0){
            val elementoBorrar = listaAMostrar[0]
            listaDiscos.remove(elementoBorrar)
            listaAMostrar.remove(elementoBorrar)
            adaptadorDisco.notifyItemRemoved(0)

        }
    }

    fun actualizarLista(nuevaLista: List<Disco>) {

        /*
        La clase DiffUtil permite gestionar las diferencias de elementos gestionados por un RecyclerView.
        Es capaz de identificar las diferencias entre los elementos de dos listas, recoger las diferencias,
        y renderizarlas de forma automática en la interfaz. Es una clase reciente, y que sustitye a otros
        métodos de sincronización usados en el pasado (notifyDataSetChanged...)
         */
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int {
                return listaAMostrar.size
            }

            override fun getNewListSize(): Int {
                return nuevaLista.size
            }

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return listaAMostrar[oldItemPosition] == nuevaLista[newItemPosition]
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return listaAMostrar[oldItemPosition] == nuevaLista[newItemPosition]
            }
        })

        listaAMostrar.clear()
        for (d in nuevaLista) {
            if (d.nombre == filtro) listaAMostrar.add(d)
        }

        // El método dispachUpdatesTo(adaptador) permite ejecutar las diferencias encontradas por el
        // diffUtil.
        diff.dispatchUpdatesTo(recyclerView.adapter!!)

    }

    // Método equivalente a OnCreate
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Como un fragmento se despliega en tiempo de ejecución, hay que usar un LayoutInflater
        // para poder renderizarla en tiempo real.

        val v = inflater.inflate(R.layout.fragment, container, false)
        recyclerView = v.findViewById(R.id.rw)
        listaAMostrar.clear()
        // Se filtra la lista recibida y se muestran sólo los discos que coincidan con la pestaña.
        for (d in listaDiscos) {
            if (d.nombre == filtro) listaAMostrar.add(d)
        }

        // Creamos nuestro adaptador personalizado, y le pasamos la lista de Disco
        adaptadorDisco = AdaptadorDisco(listaAMostrar)

        // Con esto especificamos que nuestra lista se va a ver en vertical.
        recyclerView.setLayoutManager(LinearLayoutManager(context))

        // DividerItemDecoration nos permite añadir lineas de separación entre elementos,
        // Tanto verticales como horizontales
        recyclerView.addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        // Establecemos nuestr adaptador al recyclerView
        recyclerView.adapter = adaptadorDisco
        return v
    }
}