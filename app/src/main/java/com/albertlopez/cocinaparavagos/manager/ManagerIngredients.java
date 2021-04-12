package com.albertlopez.cocinaparavagos.manager;
import com.google.gson.annotations.SerializedName;

public class ManagerIngredients {
    @SerializedName("NOMBREINGREDIENTE")
    private String nombreIngrediente;
    @SerializedName("CLASIFICACIONINGREDIENTE")
    private String clasificacionIngredientes;
    @SerializedName("INGREDIENTEBASE")
    private int ingredienteBase;

    public ManagerIngredients(String nombreIngrediente, String clasificacionIngredientes, int ingredienteBase) {
        this.nombreIngrediente = nombreIngrediente;
        this.clasificacionIngredientes = clasificacionIngredientes;
        this.ingredienteBase = ingredienteBase;

    }

}
