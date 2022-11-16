package com.p2.pruebalistview.adapters


/* En esta interfaz es donde voy a determinar todas las funciones que quiero implementar
como eventos: OnClick, OnLongClick...
 */
interface EventsInterface {
    fun clickEnElemento(pos: Int)

    /* Si quisiera implementar el evento OnLongClick, para eliminar un elemento...
       fun clickLargoBorrar(posicion :Int)
     */
}