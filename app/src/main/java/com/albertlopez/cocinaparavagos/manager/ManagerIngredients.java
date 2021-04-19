package com.albertlopez.cocinaparavagos.manager;

import com.albertlopez.cocinaparavagos.model.Ingredient;
import com.google.gson.Gson;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ManagerIngredients {
    Gson gson = new Gson();

    public void addIngredientsBase(String response) throws JSONException {
        JSONArray jsonResponse = new JSONArray(response);

        for (int i = 0; i < jsonResponse.length() ; i++) {
            JSONObject ingrediente = jsonResponse.getJSONObject(i);
            Ingredient ingredient = gson.fromJson(String.valueOf(ingrediente),Ingredient.class);
        }




    }



}
