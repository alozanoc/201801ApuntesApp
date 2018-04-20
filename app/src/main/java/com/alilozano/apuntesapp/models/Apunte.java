package com.alilozano.apuntesapp.models;

/**
 * Created by Upao on 05/04/2018.
 */

public class Apunte {
    private int id = -1;
    private String titulo;
    private String contenido;
    private String etiquetas;
    private boolean estrella = false;
    private boolean fijo = false;

    public Apunte() {
    }

    public Apunte(String titulo, String contenido, String etiquetas) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.etiquetas = etiquetas;
    }

    public boolean isEstrella() {
        return estrella;
    }

    public void setEstrella(boolean estrella) {
        this.estrella = estrella;
    }

    public boolean isFijo() {
        return fijo;
    }

    public void setFijo(boolean fijo) {
        this.fijo = fijo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(String etiquetas) {
        this.etiquetas = etiquetas;
    }

    @Override
    public String toString() {
        return titulo;
    }
}
