package com.albertlopez.cocinaparavagos.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RecipeIngredients implements Serializable {
    @SerializedName("NOMBRERECETA")
    private String nombreReceta;
    @SerializedName("NOMBREINGREDIENTE")
    private String nombreIngrediente;
    @SerializedName("CANTIDAD_INGREDIENTE")
    private int cantidadIngrediente;

    public RecipeIngredients() {}

    public RecipeIngredients(String nombreReceta, String nombreIngrediente, int cantidadIngrediente) {
        this.nombreReceta = nombreReceta;
        this.nombreIngrediente = nombreIngrediente;
        this.cantidadIngrediente = cantidadIngrediente;
    }

    public String getNombreReceta() {
        return nombreReceta;
    }
    public void setNombreReceta(String nombreReceta) {
        this.nombreReceta = nombreReceta;
    }
    public String getNombreIngrediente() {
        return nombreIngrediente;
    }
    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }
    public int getCantidadIngrediente() {
        return cantidadIngrediente;
    }
    public void setCantidadIngrediente(int cantidadIngrediente) {
        this.cantidadIngrediente = cantidadIngrediente;
    }
}
