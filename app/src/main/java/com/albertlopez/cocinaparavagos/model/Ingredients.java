package com.albertlopez.cocinaparavagos.model;

public class Ingredients {
    String nombre;
    String clasificacion;
    String tipo;

    public Ingredients(String nombre,String clasificacion, String tipo){
        nombre = nombre;
        clasificacion = clasificacion;
        tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }
    public String getClasificacion() {
        return clasificacion;
    }
    public String getTipo() {
        return tipo;
    }

}
