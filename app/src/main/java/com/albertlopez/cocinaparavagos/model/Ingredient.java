package com.albertlopez.cocinaparavagos.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Ingredient implements Serializable{
    @SerializedName("NOMBREINGREDIENTE")
    private String nombreIngrediente;
    @SerializedName("CLASIFICACIONINGREDIENTE")
    private String clasificacionIngredientes;
    @SerializedName("INGREDIENTEBASE")
    private int ingredienteBase;
    @SerializedName("IMAGEN_INGREDIENTES")
    private String imagen;

    public Ingredient() {
    }

    public Ingredient(String nombreIngrediente, String clasificacionIngredientes, int ingredienteBase) {
        this.nombreIngrediente = nombreIngrediente;
        this.clasificacionIngredientes = clasificacionIngredientes;
        this.ingredienteBase = ingredienteBase;

    }

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }


    public String getClasificacionIngredientes() {
        return clasificacionIngredientes;
    }

    public void setClasificacionIngredientes(String clasificacionIngredientes) {
        this.clasificacionIngredientes = clasificacionIngredientes;
    }

    public String getImagen() {
        return imagen;
    }

    public int getIngredienteBase() {
        return ingredienteBase;
    }

    public void setIngredienteBase(int ingredienteBase) {
        this.ingredienteBase = ingredienteBase;
    }
}
