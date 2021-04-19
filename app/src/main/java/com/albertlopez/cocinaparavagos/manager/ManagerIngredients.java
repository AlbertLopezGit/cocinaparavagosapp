package com.albertlopez.cocinaparavagos.manager;


import com.albertlopez.cocinaparavagos.model.Ingredients;

import java.util.ArrayList;
import java.util.List;

public class ManagerIngredients {
    private List<Ingredients> results;

    public ManagerIngredients(ArrayList<Ingredients> results){
        this.results = results;
    }

    public IngredientsList getFirstLevelThemes() {
        IngredientsList ingredients = new IngredientsList();
        for(Ingredients ingredient : results) {
            if (ingredient.getNombreIngrediente() == null) {
                ingredients.add(ingredient);
            }
        }
        return ingredients;
    }



}
