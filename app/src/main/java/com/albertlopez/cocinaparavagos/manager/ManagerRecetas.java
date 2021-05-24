package com.albertlopez.cocinaparavagos.manager;

import com.albertlopez.cocinaparavagos.model.Ingredient;
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
import java.util.HashMap;

public class ManagerRecetas implements Serializable{

    Gson gson = new Gson();
    ArrayList<Recipe> recipesArray;
    ArrayList<RecipeIngredients> recipesIngredientsArray;

    ArrayList<RecipeCustom>recetasCustomArray;
    ArrayList<RecipesIngredientsCustom>recetasIngredientsCustomArray;

    public void addRecetasCustom(String response) throws JSONException {
        JSONArray jsonResponse = new JSONArray(response);
        recetasCustomArray = new ArrayList<>();

        for (int i = 0; i < jsonResponse.length() ; i++) {
            JSONObject recetas = jsonResponse.getJSONObject(i);
            RecipeCustom recipe = gson.fromJson(String.valueOf(recetas),RecipeCustom.class);
            recetasCustomArray.add(recipe);
        }
    }

    public void addRecetasBase(String response) throws JSONException {
        JSONArray jsonResponse = new JSONArray(response);
        recipesArray = new ArrayList<>();

        for (int i = 0; i < jsonResponse.length() ; i++) {
            JSONObject recetas = jsonResponse.getJSONObject(i);
            Recipe recipe = gson.fromJson(String.valueOf(recetas),Recipe.class);
            recipesArray.add(recipe);
        }
    }

    public void addCantidadesRecetas(String response) throws JSONException {
        JSONArray jsonResponse = new JSONArray(response);
        recipesIngredientsArray = new ArrayList<>();

        for (int i = 0; i < jsonResponse.length() ; i++) {
            JSONObject recetasIngredientes = jsonResponse.getJSONObject(i);
            RecipeIngredients recipeIngredients = gson.fromJson(String.valueOf(recetasIngredientes),RecipeIngredients.class);
            recipesIngredientsArray.add(recipeIngredients);
        }
    }

    public void addCantidadesRecetasCustom(String response) throws JSONException {
        JSONArray jsonResponse = new JSONArray(response);
        recetasIngredientsCustomArray = new ArrayList<>();

        for (int i = 0; i < jsonResponse.length() ; i++) {
            JSONObject recetasIngredientes = jsonResponse.getJSONObject(i);
            RecipesIngredientsCustom recipeIngredients = gson.fromJson(String.valueOf(recetasIngredientes),RecipesIngredientsCustom.class);
            recetasIngredientsCustomArray.add(recipeIngredients);
        }
    }


    public ArrayList<Recipe> getRecipesArray() {
        return recipesArray;
    }

    public ArrayList<RecipeIngredients> getRecipesIngredientsArray() {
        return recipesIngredientsArray;
    }

    public void setRecipesArray(ArrayList<Recipe> recipesArray) {
        this.recipesArray = recipesArray;
    }

    public void setRecipesIngredientsArray(ArrayList<RecipeIngredients> recipesIngredientsArray) {
        this.recipesIngredientsArray = recipesIngredientsArray;
    }

    public ArrayList<Recipe> mezclarRecetasConSusIngredientes(ArrayList<Recipe> recipes, ArrayList<RecipeIngredients> recipesCantidades, ArrayList<Ingredient> ingredientes){

        for (Recipe i: recipes) {
            for (RecipeIngredients x:recipesCantidades) {
                if (x.getNombreReceta().trim().equals(i.getNombreReceta().trim())) {
                    i.addCantidadesDeLosIngredientes(x.getCantidadIngrediente());
                    i.addIngrediente(x.getNombreIngrediente());
                }
            }
        }

        for (Recipe i: recipes) {
            ArrayList<String>ingredienteString = i.getIngredientes();
            for (Ingredient x: ingredientes) {
                for (String z:ingredienteString) {
                    if (z.trim().equals(x.getNombreIngrediente().trim())) {
                        i.addIngrediente(x);
                    }
                }
            }
            i.comprobarConsitenciaIngredientes();
        }

        return recipes;
    }


    public void parseadorRecetasCustom(){
        ArrayList<Recipe>recetasNuevasCustom = new ArrayList<>();
        ArrayList<RecipeIngredients>recetasIngredientesNuevosCustom = new ArrayList<>();
        for (RecipeCustom i:recetasCustomArray) {
            Recipe recipe = new Recipe(i.getNombreReceta(), i.getDescripcion(),i.getIngredientesParaLaReceta(),i.getModoReceta(),i.getImagenReceta());
            recetasNuevasCustom.add(recipe);
        }

        for (RecipesIngredientsCustom i:recetasIngredientsCustomArray) {
            RecipeIngredients recipeIngredients = new RecipeIngredients(i.getNombreReceta(),i.getNombreIngrediente(),i.getCantidadIngrediente());
            recetasIngredientesNuevosCustom.add(recipeIngredients);
        }


        recipesArray.addAll(recetasNuevasCustom);
        recipesIngredientsArray.addAll(recetasIngredientesNuevosCustom);
    }

}
