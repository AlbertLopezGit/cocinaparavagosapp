package com.albertlopez.cocinaparavagos.manager;

import com.albertlopez.cocinaparavagos.model.Ingredient;
import com.albertlopez.cocinaparavagos.model.Recipe;
import com.albertlopez.cocinaparavagos.model.RecipeIngredients;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class ManagerAllRecipesCustom {
    static public ArrayList<Recipe> recetasQueCoincidenDelTodo = new ArrayList<>();
    static public ArrayList<Recipe> recipes = new ArrayList<>();
    static public ArrayList<RecipeIngredients> recipesCantidades = new ArrayList<>();
    static public ArrayList<Ingredient> ingredientesIntroducidosPorELUsuario = new ArrayList<>();

    public static ArrayList<Ingredient> getIngredientesIntroducidosPorELUsuario() {
        return  ingredientesIntroducidosPorELUsuario =  sumarIngredientes();
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


    public static void setRecipes(ArrayList<Recipe> recipes) {
        ManagerAllRecipesCustom.recipes = recipes;
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
