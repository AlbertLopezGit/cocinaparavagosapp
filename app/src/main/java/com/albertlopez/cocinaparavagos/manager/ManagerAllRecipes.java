package com.albertlopez.cocinaparavagos.manager;

import com.albertlopez.cocinaparavagos.model.Ingredient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ManagerAllRecipes {

    static public ArrayList<Ingredient> ingredientesIntroducidosPorELUsuario = new ArrayList<>();

    public static ArrayList<Ingredient> getIngredientesIntroducidosPorELUsuario() {
        return  ingredientesIntroducidosPorELUsuario =  sumarIngredientes();
    }

    private static ArrayList<Ingredient> sumarIngredientes() {
        Ingredient ingre = new Ingredient(" "," ",0," "," ",0);
        HashMap<String,Ingredient> ingredientesMap = new HashMap<>();


        for (Ingredient i: ingredientesIntroducidosPorELUsuario) {
            ingredientesMap.put(i.getNombreIngrediente(),i);
        }

        Collection<Ingredient> values = ingredientesMap.values();

        return new ArrayList<>(values);
    }

    //Ingredient ingredient = new Ingredient(i.getNombreIngrediente(),i.getClasificacionIngredientes(),i.getIngredienteBase(),i.getImagen(),i.getValorMedida());

    public static void borrarIngredientes(Ingredient ingredienteSeleccionado){
        ingredientesIntroducidosPorELUsuario.remove(ingredienteSeleccionado);
    }
}
