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
    @SerializedName("IMAGEN_INGREDIENTE")
    private String imagen;
    @SerializedName("VALOR_MEDIDA")
    private String valorMedida;
    private double cantidad;

    public Ingredient() {}

    public Ingredient(String nombreIngrediente, String clasificacionIngredientes, int ingredienteBase, String imagen, String valorMedida) {
        this.nombreIngrediente = nombreIngrediente; //nombre del ingrediente
        this.clasificacionIngredientes = clasificacionIngredientes; //fruta, verdura, especias
        this.ingredienteBase = ingredienteBase; //0 = ingrediente base y 1 = insertado por el usuario
        this.imagen = imagen; //link donde queda la imagen del ingrediente en el servidor
        this.valorMedida = valorMedida; //unidad, gramos, litros
        this.cantidad = 0;
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
    public String getValorMedida() {
        return valorMedida;
    }
    public void setValorMedida(String valorMedida) {
        this.valorMedida = valorMedida;
    }
    public double getCantidad() {
        return cantidad;
    }
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
}
