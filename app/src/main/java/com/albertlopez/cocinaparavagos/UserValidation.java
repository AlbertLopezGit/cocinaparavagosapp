package com.albertlopez.cocinaparavagos;

import com.albertlopez.cocinaparavagos.bbdd.Bbdd;
import com.albertlopez.cocinaparavagos.model.Ingredient;
import com.albertlopez.cocinaparavagos.model.Recipe;
import com.albertlopez.cocinaparavagos.model.RecipeCustom;
import com.albertlopez.cocinaparavagos.model.RecipesIngredientsCustom;
import com.albertlopez.cocinaparavagos.model.User;

import java.util.ArrayList;

public class UserValidation {

    static public ArrayList<String> ingredientesUltimosDelete = new ArrayList<>();
    static public ArrayList<String> ingredientesUltimos = new ArrayList<>();
    static public ArrayList<String> recetasUltimas = new ArrayList<>();
    static public ArrayList<RecipeCustom>recetasCustomArray = new ArrayList<>();
    static public ArrayList<RecipesIngredientsCustom>recetasIngredientsCustomArray = new ArrayList<>();
    static public ArrayList<Ingredient> ingredientParse;
    static public ArrayList<Recipe> recipesParse;

    static public User user = new User();
    static public Boolean validado = false;

    public static void setUser(User user) {
        UserValidation.user = user;
    }

    public static User getUser() {
        return user;
    }

    public static void comprobarPass(String pass){
        if (user.getPass().equals(pass)) {
            validado = true;
        }
    }

    public static ArrayList<Ingredient> getIngredientParse() {
        return ingredientParse;
    }

    public static Boolean getValidado() {
        return validado;
    }

    public static void logout() {
        validado = false;
    }

    public static void addUltimoIngrediente(String ultimoIngrediente) {
        ingredientesUltimos.add(ultimoIngrediente);
    }

    public static void addUltimaReceta(String ultimaReceta) {
        recetasUltimas.add(ultimaReceta);
    }
    public static void addrecetasCustomArray(RecipeCustom ultimaReceta) {
        recetasCustomArray.add(ultimaReceta);
    }



    public static void restearRecetasArrayCustom() {
        recetasCustomArray = new ArrayList<>();
    }

    public static ArrayList<String> getIngredientesUltimos() {
        return ingredientesUltimos;
    }

    public static ArrayList<String> getRecetasUltimas() {
        return recetasUltimas;
    }

    public static void restearIngredientesUltimos() {
        ingredientesUltimos = new ArrayList<>();
    }

    public static void restearRecetasUltimas() {
        recetasUltimas = new ArrayList<>();
    }

    public static ArrayList<String> getIngredientesUltimosDelete() {
        return ingredientesUltimosDelete;
    }

    public static void restearIngredientesUltimosDelete() {
        ingredientesUltimosDelete = new ArrayList<>();
    }

    public static void addUltimoIngredienteDelete(String ultimoIngrediente) {
        ingredientesUltimosDelete.add(ultimoIngrediente);
    }

    public static void addCantidadrecetasCustomArray(RecipesIngredientsCustom recipeIngredients) {
        recetasIngredientsCustomArray.add(recipeIngredients);
        parse(recipeIngredients);
    }

    private static void parse(RecipesIngredientsCustom recipeIngredients) {
        ingredientParse = new ArrayList<>();

        Ingredient ingredientParseReal = new Ingredient(recipeIngredients.getNombreIngrediente(),"cualquiercosa",1, Bbdd.ingredientesCustomGeneralImg,"cosa");
        ingredientParse.add(ingredientParseReal);
    }

    public static void esetearCantidadrecetasCustomArray() {
        recetasIngredientsCustomArray = new ArrayList<>();
    }

    public static ArrayList<RecipeCustom> getRecetasCustomArray() {
        return recetasCustomArray;
    }

    public static ArrayList<RecipesIngredientsCustom> getRecetasIngredientsCustomArray() {
        return recetasIngredientsCustomArray;
    }
}
