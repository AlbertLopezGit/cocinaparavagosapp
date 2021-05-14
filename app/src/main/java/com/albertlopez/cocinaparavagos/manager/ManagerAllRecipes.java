package com.albertlopez.cocinaparavagos.manager;

import com.albertlopez.cocinaparavagos.model.Ingredient;
import com.albertlopez.cocinaparavagos.model.Recipe;
import com.albertlopez.cocinaparavagos.model.RecipeIngredients;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ManagerAllRecipes {
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

    public static void mezclarRecetasConSusIngredientes(){
        for (Recipe i: recipes) {
            //System.out.println("Nombre Recetas " + i.getNombreReceta());
            for (RecipeIngredients x:recipesCantidades) {
                if (i.getNombreReceta().equals(x.getNombreReceta())) {
                    i.addIngrediente(x.getNombreIngrediente());
                    i.addCantidadesDeLosIngredientes(x.getCantidadIngrediente());

                    //System.out.println(x.getNombreIngrediente() + " " + x.getCantidadIngrediente());

                }
            }
        }
    }

    public static void buscarRecetasQueCoincidenConLosIngredientes() {
        int contador = 0;
        for (Recipe i: recipes) {
            ArrayList<String>ingredientes = i.getIngredientes();
            for (String x:ingredientes) {
                for (Ingredient z:ingredientesIntroducidosPorELUsuario) {
                    if (x.equals(z.getNombreIngrediente())) {
                        contador++;
                        if (contador == ingredientes.size()) {
                            contador = 0;
                        }
                    }
                }
            }
            contador = 0;
        }
    }

    public static void setRecipes(ArrayList<Recipe> recipes) {
        ManagerAllRecipes.recipes = recipes;
    }

    public static void setRecipesCantidades(ArrayList<RecipeIngredients> recipesCantidades) {
        ManagerAllRecipes.recipesCantidades = recipesCantidades;
    }


}
