package com.albertlopez.cocinaparavagos.manager;

import com.albertlopez.cocinaparavagos.UserValidation;
import com.albertlopez.cocinaparavagos.model.Recipe;
import com.albertlopez.cocinaparavagos.model.RecipeCustom;
import com.albertlopez.cocinaparavagos.model.RecipeIngredients;
import com.albertlopez.cocinaparavagos.model.RecipesIngredientsCustom;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class ManagerRecetasIngredientesCustom implements Serializable {

    Gson gson = new Gson();

    public void addRecetasCustom(String response) throws JSONException {
        JSONArray jsonResponse = new JSONArray(response);
        ManagerAllRecipesCustom.resetearRecipeCustom();

        for (int i = 0; i < jsonResponse.length() ; i++) {
            JSONObject recetas = jsonResponse.getJSONObject(i);
            RecipeCustom recipe = gson.fromJson(String.valueOf(recetas),RecipeCustom.class);
            if (UserValidation.getUser().getIdUsuario().equals(String.valueOf(recipe.getIdUsuario()))) {
                ManagerAllRecipesCustom.addRecipeCustomFuntion(recipe);
            }
        }
    }

    public void addCantidadesRecetasCustom(String response) throws JSONException {
        JSONArray jsonResponse = new JSONArray(response);
        ManagerAllRecipesCustom.resetearCantidadesRecetasCustom();

        for (int i = 0; i < jsonResponse.length() ; i++) {
            JSONObject recetasIngredientes = jsonResponse.getJSONObject(i);
            RecipesIngredientsCustom recipeIngredients = gson.fromJson(String.valueOf(recetasIngredientes),RecipesIngredientsCustom.class);
            if (UserValidation.getUser().getIdUsuario().equals(String.valueOf(recipeIngredients.getIdusuario()))) {
                ManagerAllRecipesCustom.addCantidadesRecetasCustom(recipeIngredients);
            }
        }
    }





}
