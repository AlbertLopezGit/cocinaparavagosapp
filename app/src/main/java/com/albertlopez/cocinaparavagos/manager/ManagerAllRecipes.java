package com.albertlopez.cocinaparavagos.manager;

import com.albertlopez.cocinaparavagos.model.Ingredient;
import com.albertlopez.cocinaparavagos.model.Recipe;
import com.albertlopez.cocinaparavagos.model.RecipeIngredients;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class ManagerAllRecipes{
    static public ArrayList<Recipe> recetasQueCoincidenDelTodo = new ArrayList<>();
    static public ArrayList<Recipe> recipes = new ArrayList<>();
    static public ArrayList<RecipeIngredients> recipesCantidades = new ArrayList<>();
    static public ArrayList<Ingredient> ingredientesIntroducidosPorELUsuario = new ArrayList<>();

    public static ArrayList<Ingredient> getIngredientesIntroducidosPorELUsuario() {
        return  ingredientesIntroducidosPorELUsuario =  sumarIngredientes();
    }
    public static void resetarIngredientesIntroducidosPorElUsuario(){
        ArrayList<Ingredient> ingredientesIntroducidosPorELUsuarioVacio = new ArrayList<>();
        ingredientesIntroducidosPorELUsuario = ingredientesIntroducidosPorELUsuarioVacio;
    }
    public static ArrayList<Recipe> getRecetasQueCoincidenDelTodo() {
        return recetasQueCoincidenDelTodo;
    }
    public static ArrayList<Recipe> getRecipes() {
        return recipes;
    }
    public static ArrayList<RecipeIngredients> getRecipesCantidades() {
        return recipesCantidades;
    }
    public static void setRecipes(ArrayList<Recipe> recipes) {
        ManagerAllRecipes.recipes = recipes;
    }
    public static void setRecipesCantidades(ArrayList<RecipeIngredients> recipesCantidades) {
        ManagerAllRecipes.recipesCantidades = recipesCantidades;
    }
    public static void resetearRecetas(){
        recipes = new ArrayList<>();
        recipesCantidades = new ArrayList<>();
        recetasQueCoincidenDelTodo = new ArrayList<>();
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

    public static void buscarRecetasQueCoincidenConLosIngredientes() {
        int contador = 0;
        HashSet<Recipe> ingredientesHash = new HashSet<Recipe>();
        for (Recipe i: recipes) {
            ArrayList<String>ingredientes = i.getIngredientes();
            for (String x:ingredientes) {
                for (Ingredient z:ingredientesIntroducidosPorELUsuario) {
                    if (x.equals(z.getNombreIngrediente())) {
                        contador++;
                        if (contador == ingredientes.size()) {
                            System.out.println("Puede hacer "+ i.getNombreReceta());
                            ingredientesHash.add(i);
                            contador = 0;
                        }
                    }
                }
            }
            compararCantidades(ingredientesHash);
            contador = 0;
        }
    }

    private static void compararCantidades(HashSet<Recipe> ingredientesHash) {
        ArrayList<Recipe> recetasQueCoinciden = new ArrayList<>();
        List<Recipe> recetas = new ArrayList<>(ingredientesHash);
        int contador = 0;
        for (Recipe i: recetas) {
            //System.out.println("RECETAS QUE CONICIDEN "+i.getNombreReceta());
            ArrayList<String> arrayListString = i.getIngredientes();
            ArrayList<Integer> arrayListCantidades = i.getCantidadesDeLosIngredientes();
            for (int j = 0; j < arrayListString.size(); j++) {
                //System.out.println("Ingrediente " + arrayListString.get(j));
                //System.out.println("Cantidad " + arrayListCantidades.get(j));
                for (Ingredient x :ingredientesIntroducidosPorELUsuario) {
                    if (arrayListString.get(j).equals(x.getNombreIngrediente()) && arrayListCantidades.get(j) <= x.getCantidad()) {
                        contador++;
                        if (contador == arrayListString.size()) {
                            recetasQueCoinciden.add(i);
                            contador = 0;
                        }
                    }
                }
            }
            contador = 0;
        }
        recetasQueCoincidenDelTodo = recetasQueCoinciden;
    }


}
