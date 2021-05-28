package com.albertlopez.cocinaparavagos.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RecipesIngredientsCustom implements Serializable {
    @SerializedName("NOMBRERECETACUSTOM")
    private String nombreReceta;
    @SerializedName("NOMBREINGREDIENTECUSTOM")
    private String nombreIngrediente;
    @SerializedName("CANTIDAD_INGREDIENTE_CUSTOM")
    private int cantidadIngrediente;
    @SerializedName("IDUSUARIO")
    private int idusuario;

    public RecipesIngredientsCustom() {}

    public RecipesIngredientsCustom(String nombreReceta, String nombreIngrediente, int cantidadIngrediente,int idusuario) {
        this.nombreReceta = nombreReceta;
        this.nombreIngrediente = nombreIngrediente;
        this.cantidadIngrediente = cantidadIngrediente;
    }

    public int getIdusuario() {
        return idusuario;
    }
    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
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
