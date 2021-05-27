package com.albertlopez.cocinaparavagos.manager;

import com.albertlopez.cocinaparavagos.model.Ingredient;
import com.albertlopez.cocinaparavagos.model.Recipe;
import com.albertlopez.cocinaparavagos.model.RecipeCustom;
import com.albertlopez.cocinaparavagos.model.RecipeIngredients;
import com.albertlopez.cocinaparavagos.model.RecipesIngredientsCustom;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ManagerAllRecipesCustom {
    static public ArrayList<Recipe> recetasQueCoincidenDelTodo = new ArrayList<>();
    static public ArrayList<Recipe> recipes = new ArrayList<>();
    static public ArrayList<RecipeIngredients> recipesCantidades = new ArrayList<>();
    static public ArrayList<Ingredient> ingredientesIntroducidosPorELUsuario = new ArrayList<>();

    static public ArrayList<RecipeCustom> recipeCustoms = new ArrayList<>();
    static public ArrayList<RecipesIngredientsCustom> recipeIngredientsCustoms = new ArrayList<>();

    public static ArrayList<Ingredient> getIngredientesIntroducidosPorELUsuario() {
        return  ingredientesIntroducidosPorELUsuario =  sumarIngredientes();
    }
    public static ArrayList<Recipe> getRecipes() {
        return recipes;
    }
    public static void setRecipeCustoms(ArrayList<RecipeCustom> recipeCustoms) {
        ManagerAllRecipesCustom.recipeCustoms = recipeCustoms;
    }
    public static void setRecipeIngredientsCustoms(ArrayList<RecipesIngredientsCustom> recipeIngredientsCustoms) {
        ManagerAllRecipesCustom.recipeIngredientsCustoms = recipeIngredientsCustoms;
    }
    public static ArrayList<RecipesIngredientsCustom> getRecipeIngredientsCustoms() {
        return recipeIngredientsCustoms;
    }
    public static ArrayList<RecipeCustom> getRecipeCustoms() {
        return recipeCustoms;
    }
    public static void addRecipeCustomFuntion(RecipeCustom recipe) {
        recipeCustoms.add(recipe);
    }
    public static void resetearRecipeCustom() {
        recipeCustoms = new ArrayList<>();
    }
    public static void addCantidadesRecetasCustom(RecipesIngredientsCustom recipe) {
        recipeIngredientsCustoms.add(recipe);
    }
    public static void resetearCantidadesRecetasCustom() {
        recipeIngredientsCustoms = new ArrayList<>();
    }
    public static ArrayList<RecipeIngredients> getRecipesCantidades() {
        return recipesCantidades;
    }

    private static ArrayList<Ingredient> sumarIngredientes() {
        HashMap<String,Ingredient> ingredientesMap = new HashMap<>();

        for (Ingredient i: ingredientesIntroducidosPorELUsuario) {
            ingredientesMap.put(i.getNombreIngrediente(),i);
        }

        Collection<Ingredient> values = ingredientesMap.values();
        return new ArrayList<>(values);
    }

    public static void borrarIngredientes(Ingredient ingredienteSeleccionado){
        ingredientesIntroducidosPorELUsuario.remove(ingredienteSeleccionado);
    }

    public static void setRecipesCantidades(ArrayList<RecipeIngredients> recipesCantidades) {
        ManagerAllRecipesCustom.recipesCantidades = recipesCantidades;
    }

    public static ArrayList<Recipe> getRecetasQueCoincidenDelTodo() {
        return recetasQueCoincidenDelTodo;
    }

    public static void resetarIngredientesIntroducidosPorElUsuario(){
        ArrayList<Ingredient> ingredientesIntroducidosPorELUsuarioVacio = new ArrayList<>();
        ingredientesIntroducidosPorELUsuario = ingredientesIntroducidosPorELUsuarioVacio;
    }
}
