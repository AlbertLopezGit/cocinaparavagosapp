package com.albertlopez.cocinaparavagos.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Recipe implements Serializable {
    @SerializedName("NOMBRERECETA")
    private String nombreReceta;
    @SerializedName("DESCRIPCIONRECETA")
    private String descripcion;
    @SerializedName("NOMBREINGREDIENTESTOTALES")
    private String ingredientesParaLaReceta;
    @SerializedName("RECETADEBASE")
    private int modoReceta;
    @SerializedName("IMAGEN_RECETA")
    private String imagenReceta;
    ArrayList<String>ingredientes = new ArrayList<>();
    ArrayList<Integer>cantidadesDeLosIngredientes = new ArrayList<>();
    ArrayList<Ingredient>ingredients = new ArrayList<>();

    public Recipe () {}

    public Recipe(String nombreReceta, String descripcion, String ingredientesParaLaReceta, int modoReceta, String imagenReceta) {
        this.nombreReceta = nombreReceta;
        this.descripcion = descripcion;
        this.ingredientesParaLaReceta = ingredientesParaLaReceta;
        this.modoReceta = modoReceta;
        this.imagenReceta = imagenReceta;
    }

    public void addIngrediente(String ingrediente) {
        ingredientes.add(ingrediente);
    }

    public void addCantidadesDeLosIngredientes(int cantidadIngrediente) {
        cantidadesDeLosIngredientes.add(cantidadIngrediente);
    }

    public void addIngrediente(Ingredient ingrediente) {
        ingredients.add(ingrediente);
    }

    public ArrayList<Integer> getCantidadesDeLosIngredientes() {
        return cantidadesDeLosIngredientes;
    }

    public ArrayList<Ingredient> getIngredientsArray() {
        return ingredients;
    }

    public ArrayList<String> getIngredientes() {
        return ingredientes;
    }

    public void setCantidadesDeLosIngredientes(ArrayList<Integer> cantidadesDeLosIngredientes) {
        this.cantidadesDeLosIngredientes = cantidadesDeLosIngredientes;
    }

    public void setIngredientes(ArrayList<String> ingredientes) {
        this.ingredientes = ingredientes;
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


    public void comprobarConsitenciaIngredientes(){
        HashMap<String,Ingredient> mapIngredientes = new HashMap<>();

        for (Ingredient i: ingredients) {
            mapIngredientes.put(i.getNombreIngrediente(),i);
        }

        Collection<Ingredient> values = mapIngredientes.values();
        ingredients = new ArrayList<>(values);
    }
}
