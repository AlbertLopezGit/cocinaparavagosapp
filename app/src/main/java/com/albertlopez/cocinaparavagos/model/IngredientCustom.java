package com.albertlopez.cocinaparavagos.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IngredientCustom implements Serializable{
    @SerializedName("NOMBREINGREDIENTECUSTOM")
    private String nombreIngrediente;
    @SerializedName("CLASIFICACIONINGREDIENTECUSTOM")
    private String clasificacionIngredientes;
    @SerializedName("INGREDIENTEDEBASECUSTOM")
    private String ingredienteBase;
    @SerializedName("IMAGEN_INGREDIENTE_CUSTOM")
    private String imagen;
    @SerializedName("VALOR_MEDIDA_CUSTOM")
    private String valorMedida;
    @SerializedName("IDUSUARIO")
    private String idUsuario;
    private int cantidad;

    public IngredientCustom() {}

    public IngredientCustom(String nombreIngrediente, String clasificacionIngredientes, String ingredienteBase, String imagen, String valorMedida,String idUsuario) {
        this.nombreIngrediente = nombreIngrediente; //nombre del ingrediente
        this.clasificacionIngredientes = clasificacionIngredientes; //fruta, verdura, especias
        this.ingredienteBase = ingredienteBase; //0 = ingrediente base y 1 = insertado por el usuario
        this.imagen = imagen; //link donde queda la imagen del ingrediente en el servidor
        this.valorMedida = valorMedida; //unidad, gramos, litros
        this.idUsuario = idUsuario;
        this.cantidad = 0;
    }

    public IngredientCustom(String nombreIngrediente, String clasificacionIngredientes, String ingredienteBase, String imagen, String valorMedida, int cantidad) {
        this.nombreIngrediente = nombreIngrediente; //nombre del ingrediente
        this.clasificacionIngredientes = clasificacionIngredientes; //fruta, verdura, especias
        this.ingredienteBase = ingredienteBase; //0 = ingrediente base y 1 = insertado por el usuario
        this.imagen = imagen; //link donde queda la imagen del ingrediente en el servidor
        this.valorMedida = valorMedida; //unidad, gramos, litros
        this.idUsuario = idUsuario;
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
    public String getIngredienteBase() {
        return ingredienteBase;
    }
    public void setIngredienteBase(String ingredienteBase) {
        this.ingredienteBase = ingredienteBase;
    }
    public String getValorMedida() {
        return valorMedida;
    }
    public void setValorMedida(String valorMedida) {
        this.valorMedida = valorMedida;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public String getIdUsuario() {
        return idUsuario;
    }
}