package com.albertlopez.cocinaparavagos.manager;

import com.albertlopez.cocinaparavagos.UserValidation;
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
import java.util.Collection;
import java.util.HashMap;

public class ManagerRecetas implements Serializable{

    Gson gson = new Gson();
    ArrayList<Recipe> recipesArray;
    ArrayList<RecipeIngredients> recipesIngredientsArray;

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

    public void addRecetasCustom(String response) throws JSONException {
        JSONArray jsonResponse = new JSONArray(response);
        UserValidation.restearRecetasArrayCustom();

        for (int i = 0; i < jsonResponse.length() ; i++) {
            JSONObject recetas = jsonResponse.getJSONObject(i);
            RecipeCustom recipe = gson.fromJson(String.valueOf(recetas),RecipeCustom.class);
            if (String.valueOf(recipe.getIdUsuario()).equals(UserValidation.getUser().getIdUsuario())) {
                UserValidation.addrecetasCustomArray(recipe);
            }
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
        UserValidation.esetearCantidadrecetasCustomArray();
        JSONArray jsonResponse = new JSONArray(response);

        for (int i = 0; i < jsonResponse.length() ; i++) {
            JSONObject recetasIngredientes = jsonResponse.getJSONObject(i);
            RecipesIngredientsCustom recipeIngredients =
                    gson.fromJson(String.valueOf(recetasIngredientes),RecipesIngredientsCustom.class);
            if (String.valueOf(recipeIngredients.getIdusuario()).equals(UserValidation.getUser().getIdUsuario())) {
                UserValidation.addCantidadrecetasCustomArray(recipeIngredients);
            }
        }
    }

    public ArrayList<Recipe>
    mezclarRecetasConSusIngredientes(ArrayList<Recipe> recipes, ArrayList<RecipeIngredients> recipesCantidades, ArrayList<Ingredient> ingredientes){

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
        HashMap<String, Recipe> recetasNuevasCustomMap = new HashMap<>();
        HashMap<String, RecipeIngredients> recetasIngredientesNuevosCustomMap = new HashMap<>();

        HashMap<String, Recipe> recetasMap = new HashMap<>();
        HashMap<String, RecipeIngredients> recetasIngredientesMap = new HashMap<>();

        ArrayList<Recipe>recetasNuevasCustom = new ArrayList<>();
        ArrayList<RecipeIngredients>recetasIngredientesNuevosCustom = new ArrayList<>();

        ArrayList<RecipeCustom> recetasCustomArray = UserValidation.getRecetasCustomArray();


        ArrayList<RecipesIngredientsCustom> recetasIngredientsCustomArray =
                UserValidation.getRecetasIngredientsCustomArray();

        for (RecipeCustom i:recetasCustomArray) {
            Recipe recipe = new Recipe(i.getNombreReceta(),
                    i.getDescripcion(),i.getIngredientesParaLaReceta(),i.getModoReceta(),i.getImagenReceta());
            if (UserValidation.getUser().getIdUsuario().equals(String.valueOf(i.getIdUsuario()))) {
                recetasNuevasCustom.add(recipe);
            }
        }

        for (int i = 0; i < recetasNuevasCustom.size() ; i++) {
            recetasNuevasCustomMap.put(recetasNuevasCustom.get(i).getNombreReceta(),recetasNuevasCustom.get(i));
        }

        for (RecipesIngredientsCustom i:recetasIngredientsCustomArray) {
            RecipeIngredients recipeIngredients = new RecipeIngredients(i.getNombreReceta(),
                    i.getNombreIngrediente(),i.getCantidadIngrediente());
            if (UserValidation.getUser().getIdUsuario().equals(String.valueOf(i.getIdusuario()))) {
                recetasIngredientesNuevosCustom.add(recipeIngredients);
            }

        }

        for (int i = 0; i < recetasIngredientesNuevosCustom.size() ; i++) {
            recetasIngredientesNuevosCustomMap.put(recetasIngredientesNuevosCustom.get(i).getNombreIngrediente(),
                    recetasIngredientesNuevosCustom.get(i));
        }

        Collection<Recipe> values = recetasNuevasCustomMap.values();
        Collection<RecipeIngredients> values2 = recetasIngredientesNuevosCustomMap.values();


        ArrayList<Recipe> listOfRecetas = new ArrayList<>(values);
        ArrayList<RecipeIngredients> listOfIngredient = new ArrayList<>(values2);


        recipesArray.addAll(listOfRecetas);
        recipesIngredientsArray.addAll(listOfIngredient);

        for (int i = 0; i < recipesArray.size() ; i++) {
            recetasMap.put(recipesArray.get(i).getNombreReceta(),recipesArray.get(i));
        }

        for (int i = 0; i < recipesIngredientsArray.size() ; i++) {
            recetasIngredientesMap.put(recipesIngredientsArray.get(i).getNombreIngrediente(),recipesIngredientsArray.get(i));
        }

        Collection<Recipe> values3 = recetasMap.values();
        Collection<RecipeIngredients> values4 = recetasIngredientesMap.values();

        ArrayList<Recipe> listOfRecetas2 = new ArrayList<>(values3);
        ArrayList<RecipeIngredients> listOfIngredient2 = new ArrayList<>(values4);

        recipesArray = new ArrayList<>();
        recipesIngredientsArray = new ArrayList<>();

        recipesArray = listOfRecetas2;
        recipesIngredientsArray = listOfIngredient2;

    }
}

