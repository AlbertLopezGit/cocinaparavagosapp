package com.albertlopez.cocinaparavagos.manager;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.albertlopez.cocinaparavagos.model.Ingredient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IllegalFormatCodePointException;
import java.util.Objects;

public class ManagerAllRecipes {

    static public ArrayList<Ingredient> ingredientesIntroducidosPorELUsuario = new ArrayList<>();
    static public ArrayList<Ingredient> ingredientesIntroducidosPorELUsuarioSumandoIngredientes = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static ArrayList<Ingredient> getIngredientesIntroducidosPorELUsuario() {
        if (ingredientesIntroducidosPorELUsuario.size() >= 2) {
            ingredientesIntroducidosPorELUsuario =  sumarIngredientes();
        }
        return ingredientesIntroducidosPorELUsuario;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private static ArrayList<Ingredient> sumarIngredientes() {
        HashMap<String,Ingredient> ingredientesMap = new HashMap<>();
        int contador = 0;
        ingredientesIntroducidosPorELUsuario.sort(Comparator.comparing(Ingredient::getNombreIngrediente));
        for (Ingredient i: ingredientesIntroducidosPorELUsuario) {
            ingredientesMap.put(i.getNombreIngrediente(),i);
            for (String key : ingredientesMap.keySet()) {
                if (key.equals(i.getNombreIngrediente())) {
                    contador++;
                    if (contador == 3) {
                        if (key.equals(i.getNombreIngrediente())) {
                            double total = Objects.requireNonNull(ingredientesMap.get(key)).getCantidad();
                            total += i.getCantidad();
                            Objects.requireNonNull(ingredientesMap.get(key)).setCantidad(total);
                            contador = 0;
                        }

                    }
                } else {
                    contador = 0;
                }
            }
        }

        Collection<Ingredient> values = ingredientesMap.values();
        ArrayList<Ingredient> listOfIngredients = new ArrayList<>(values);
        return new ArrayList<>(listOfIngredients);
    }

    //Ingredient ingredient = new Ingredient(i.getNombreIngrediente(),i.getClasificacionIngredientes(),i.getIngredienteBase(),i.getImagen(),i.getValorMedida());

}
