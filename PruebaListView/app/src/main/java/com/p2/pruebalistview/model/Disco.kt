package com.p2.pruebalistview.model;

/* Clase que representa nuestro modelo, el objeto Java que vamos a representar en la lista */
public class Disco {
    private String nombre;
    private String autor;
    private int portada;

    public Disco(String nombre, String autor, int portada) {
        this.nombre = nombre;
        this.autor = autor;
        this.portada = portada;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getPortada() {
        return portada;
    }

    public void setPortada(int portada) {
        this.portada = portada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
