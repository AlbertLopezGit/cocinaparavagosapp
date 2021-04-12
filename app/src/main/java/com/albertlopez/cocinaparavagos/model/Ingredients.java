package com.albertlopez.cocinaparavagos.model;

import com.google.gson.annotations.SerializedName;

public class Ingredients {
    @SerializedName("NOMBREINGREDIENTE")
    private String nombreIngrediente;
    @SerializedName("CLASIFICACIONINGREDIENTE")
    private String clasificacionIngredientes;
    @SerializedName("INGREDIENTEBASE")
    private int ingredienteBase;

    public Ingredients(String nombreIngrediente, String clasificacionIngredientes, int ingredienteBase) {
        this.nombreIngrediente = nombreIngrediente;
        this.clasificacionIngredientes = clasificacionIngredientes;
        this.ingredienteBase = ingredienteBase;

    }

}
