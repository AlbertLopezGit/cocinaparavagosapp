package com.albertlopez.cocinaparavagos.manager;

import com.albertlopez.cocinaparavagos.model.Ingredient;

import java.util.ArrayList;

public class ManagerAllRecipes {

    static public ArrayList<Ingredient> ingredientesIntroducidosPorELUsuario = new ArrayList<>();

    public static ArrayList<Ingredient> getIngredientesIntroducidosPorELUsuario() {
        return ingredientesIntroducidosPorELUsuario;
    }
}
