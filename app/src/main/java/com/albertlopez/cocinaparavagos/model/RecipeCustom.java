package com.albertlopez.cocinaparavagos.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class RecipeCustom implements Serializable {
    @SerializedName("NOMBRERECETACUSTOM")
    private String nombreReceta;
    @SerializedName("DESCRIPCIONRECETACUSTOM")
    private String descripcion;
    @SerializedName("NOMBREINGREDIENTESTOTALESCUSTOM")
    private String ingredientesParaLaReceta;
    @SerializedName("RECETADEBASECUSTOM")
    private int modoReceta;
    @SerializedName("IMAGEN_RECETA_CUSTOM")
    private String imagenReceta;
    @SerializedName("IDUSUARIO")
    private int idUsuario;

    ArrayList<String> ingredientes = new ArrayList<>();
    ArrayList<Integer>cantidadesDeLosIngredientes = new ArrayList<>();
    ArrayList<Ingredient>ingredients = new ArrayList<>();

    public RecipeCustom () {}

    public RecipeCustom(String nombreReceta, String descripcion, String ingredientesParaLaReceta, int modoReceta, String imagenReceta,int idUsuario) {
        this.nombreReceta = nombreReceta;
        this.descripcion = descripcion;
        this.ingredientesParaLaReceta = ingredientesParaLaReceta;
        this.modoReceta = modoReceta;
        this.imagenReceta = imagenReceta;
        this.idUsuario = idUsuario;
    }

    public String getNombreReceta() {
        return nombreReceta;
    }

    public void setNombreReceta(String nombreReceta) {
        this.nombreReceta = nombreReceta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIngredientesParaLaReceta() {
        return ingredientesParaLaReceta;
    }

    public void setIngredientesParaLaReceta(String ingredientesParaLaReceta) {
        this.ingredientesParaLaReceta = ingredientesParaLaReceta;
    }

    public int getModoReceta() {
        return modoReceta;
    }

    public void setModoReceta(int modoReceta) {
        this.modoReceta = modoReceta;
    }

    public String getImagenReceta() {
        return imagenReceta;
    }

    public void setImagenReceta(String imagenReceta) {
        this.imagenReceta = imagenReceta;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public ArrayList<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public ArrayList<Integer> getCantidadesDeLosIngredientes() {
        return cantidadesDeLosIngredientes;
    }

    public void setCantidadesDeLosIngredientes(ArrayList<Integer> cantidadesDeLosIngredientes) {
        this.cantidadesDeLosIngredientes = cantidadesDeLosIngredientes;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
