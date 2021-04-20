package com.albertlopez.cocinaparavagos.manager;

import com.albertlopez.cocinaparavagos.model.Ingredient;
import com.google.gson.Gson;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;


public class ManagerIngredients implements Serializable {
    Gson gson = new Gson();
    ArrayList<Ingredient> ingredientsArray;


    public void addIngredientsBase(String response) throws JSONException {
        JSONArray jsonResponse = new JSONArray(response);
        ingredientsArray = new ArrayList<>();

        for (int i = 0; i < jsonResponse.length() ; i++) {
            JSONObject ingrediente = jsonResponse.getJSONObject(i);
            Ingredient ingredient = gson.fromJson(String.valueOf(ingrediente),Ingredient.class);
            ingredientsArray.add(ingredient);

        }
    }

    public void setIngredientsArray(ArrayList<Ingredient> ingredientsArray) {
        this.ingredientsArray = ingredientsArray;
    }

    public ArrayList<Ingredient> getIngredientsArray() {
        return ingredientsArray;
    }

    public ArrayList<String> viewAllIngredients(){
        HashSet<String> tipesIngredients = new HashSet<>();
        for (int i = 0; i < this.ingredientsArray.size() ; i++) {
            tipesIngredients.add(ingredientsArray.get(i).getClasificacionIngredientes());
        }
        return new ArrayList<>(tipesIngredients);
    }


}
