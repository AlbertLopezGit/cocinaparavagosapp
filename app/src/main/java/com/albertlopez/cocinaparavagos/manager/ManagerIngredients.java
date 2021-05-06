package com.albertlopez.cocinaparavagos.manager;

import com.albertlopez.cocinaparavagos.model.Ingredient;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class ManagerIngredients implements Serializable {
    Gson gson = new Gson();
    ArrayList<Ingredient> ingredientsArray;
    ArrayList<Ingredient> tiposIngredientsArray;

    public void addIngredientsBase(String response) throws JSONException {
        JSONArray jsonResponse = new JSONArray(response);
        ingredientsArray = new ArrayList<>();
        tiposIngredientsArray = new ArrayList<>();

        for (int i = 0; i < jsonResponse.length() ; i++) {
            JSONObject ingrediente = jsonResponse.getJSONObject(i);
            Ingredient ingredient = gson.fromJson(String.valueOf(ingrediente),Ingredient.class);
            ingredientsArray.add(ingredient);
        }
    }

    public void setIngredientsArray(ArrayList<Ingredient> ingredientsArray) {
        this.ingredientsArray = ingredientsArray;
    }
    public void settiposIngredientsArray(ArrayList<Ingredient> ingredientsArray) {
        this.tiposIngredientsArray = ingredientsArray;
    }
    public ArrayList<Ingredient> getIngredientsArray() {
        return ingredientsArray;
    }
    public ArrayList<Ingredient> getTiposIngredientsArray() {
        return tiposIngredientsArray;
    }

    public ArrayList<String> viewAllIngredients(){ //aqui se retorna solo los tipos de ingredientes
        HashSet<String> tipesIngredients = new HashSet<>();
        for (int i = 0; i < this.ingredientsArray.size() ; i++) {
            tipesIngredients.add(ingredientsArray.get(i).getClasificacionIngredientes());
        }
        return new ArrayList<>(tipesIngredients);
    }

    public ArrayList<Ingredient> viewIngredientsBase(){
        HashMap<String, Ingredient> ingredientesBaseMap = new HashMap<String, Ingredient>();
        for (int i = 0; i < this.ingredientsArray.size() ; i++) {
            ingredientesBaseMap.put(ingredientsArray.get(i).getClasificacionIngredientes(),ingredientsArray.get(i));
        }
        Collection<Ingredient> values = ingredientesBaseMap.values();

        ArrayList<Ingredient> listOfIngredients = new ArrayList<>(values);
        return new ArrayList<>(listOfIngredients);
    }

    public ArrayList<Ingredient> returnIngredientsForTipeIngredients(int posicion){ //metodo usado para separar los ingredientes por tipo
        ArrayList<String> tiposIngredientes = viewAllIngredients();
        ArrayList<Ingredient> ingredientesSeleccionado = new ArrayList<>();
        for (Ingredient i :ingredientsArray) {
            if (i.getClasificacionIngredientes().equalsIgnoreCase(tiposIngredientes.get(posicion))) {
                ingredientesSeleccionado.add(i);
            }
        }
        return ingredientesSeleccionado;
    }
}
