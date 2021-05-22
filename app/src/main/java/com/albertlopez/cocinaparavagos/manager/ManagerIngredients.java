package com.albertlopez.cocinaparavagos.manager;

import com.albertlopez.cocinaparavagos.UserValidation;
import com.albertlopez.cocinaparavagos.model.Ingredient;
import com.albertlopez.cocinaparavagos.model.IngredientCustom;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class ManagerIngredients implements Serializable {
    Gson gson = new Gson();
    ArrayList<Ingredient> ingredientsArray;
    ArrayList<Ingredient> tiposIngredientsArray;
    ArrayList<Ingredient> ingredientsArrayFijos;
    ArrayList<IngredientCustom> ingredientsCustomArray;

    public void addIngredientsBase(String response) throws JSONException {
        JSONArray jsonResponse = new JSONArray(response);
        ingredientsArray = new ArrayList<>();
        ingredientsArrayFijos = new ArrayList<>();
        tiposIngredientsArray = new ArrayList<>();

        for (int i = 0; i < jsonResponse.length() ; i++) {
            JSONObject ingrediente = jsonResponse.getJSONObject(i);
            Ingredient ingredient = gson.fromJson(String.valueOf(ingrediente),Ingredient.class);
            ingredientsArray.add(ingredient);
        }
    }

    public void addIngredientsCustom(String response) throws JSONException {
        ingredientsCustomArray  = new ArrayList<>();
        JSONArray jsonResponse = new JSONArray(response);

        for (int i = 0; i < jsonResponse.length() ; i++) {
            JSONObject ingrediente = jsonResponse.getJSONObject(i);
            IngredientCustom ingredient = gson.fromJson(String.valueOf(ingrediente),IngredientCustom.class);
            if (ingredient.getIdUsuario().equals(UserValidation.getUser().getIdUsuario())) {
                ingredientsCustomArray.add(ingredient);
            }
        }
        mezclarIngredientesConCustom();
    }

    public void setIngredientsArray(ArrayList<Ingredient> ingredientsArray) {
        this.ingredientsArray = ingredientsArray;
    }
    public void settiposIngredientsArray(ArrayList<Ingredient> ingredientsArray) {
        this.tiposIngredientsArray = ingredientsArray;
    }
    public ArrayList<Ingredient> getIngredientsArray() {
        return ingredientsArray;
    }
    public ArrayList<Ingredient> getTiposIngredientsArray() {
        return tiposIngredientsArray;
    }

    public ArrayList<String> viewAllIngredients(){ //aqui se retorna solo los tipos de ingredientes
        HashSet<String> tipesIngredients = new HashSet<>();
        for (int i = 0; i < this.ingredientsArray.size() ; i++) {
            tipesIngredients.add(ingredientsArray.get(i).getClasificacionIngredientes());
        }
        return new ArrayList<>(tipesIngredients);
    }

    public ArrayList<Ingredient> viewIngredientsBase(){
        HashMap<String, Ingredient> ingredientesBaseMap = new HashMap<String, Ingredient>();
        for (int i = 0; i < this.ingredientsArray.size() ; i++) {
            ingredientesBaseMap.put(ingredientsArray.get(i).getClasificacionIngredientes(),ingredientsArray.get(i));
        }
        Collection<Ingredient> values = ingredientesBaseMap.values();

        ArrayList<Ingredient> listOfIngredients = new ArrayList<>(values);
        return new ArrayList<>(listOfIngredients);
    }

    public ArrayList<Ingredient> returnIngredientsForTipeIngredients(int posicion){ //metodo usado para separar los ingredientes por tipo
        ArrayList<String> tiposIngredientes = viewAllIngredients();
        ArrayList<Ingredient> ingredientesSeleccionado = new ArrayList<>();
        for (Ingredient i :ingredientsArray) {
            if (i.getClasificacionIngredientes().equalsIgnoreCase(tiposIngredientes.get(posicion))) {
                ingredientesSeleccionado.add(i);
            }
        }
        return ingredientesSeleccionado;
    }

    public void mezclarIngredientesConCustom() {

        if (ingredientsCustomArray.size() == 0) {return;}
        ArrayList<Ingredient> ingredientsArrayMezclado = new ArrayList<>();
        LinkedHashMap<String,Ingredient> mapIngredientes = new LinkedHashMap<>();
        ArrayList<Ingredient> mezclado = new ArrayList<>();

        for (IngredientCustom i: ingredientsCustomArray) {
            if (i.getIdUsuario().equals(UserValidation.getUser().getIdUsuario())) {
                Ingredient ingredient = new Ingredient(i.getNombreIngrediente(),i.getClasificacionIngredientes(),
                        1, i.getImagen(), i.getValorMedida());
                ingredientsArrayMezclado.add(ingredient);
            }
        }

        ingredientsArrayMezclado.addAll(ingredientsArrayFijos);

        for (Ingredient i: ingredientsArrayMezclado) {
            mapIngredientes.put(i.getNombreIngrediente(),i);
        }

        for (Map.Entry<String, Ingredient> entry : mapIngredientes.entrySet()) {
            Ingredient value = entry.getValue();
            mezclado.add(value);
        }
        setIngredientsArray(mezclado);
    }

    public void setIngredientsArrayFijos(ArrayList<Ingredient> ingredientsArrayFijos) {
        this.ingredientsArrayFijos = ingredientsArrayFijos;
    }

    public void noUsuario(){
        setIngredientsArray(ingredientsArrayFijos);
    }

    public ArrayList<IngredientCustom> getIngredientsCustomArray() {
        return ingredientsCustomArray;
    }

    public ArrayList<Ingredient> conversorCustomIngredientes() {
        ArrayList<Ingredient> ingredientesCustomParse = new ArrayList<>();
        for (IngredientCustom i:ingredientsCustomArray) {
            Ingredient ingredient = new Ingredient(i.getNombreIngrediente(),i.getClasificacionIngredientes(),
                    1, i.getImagen(), i.getValorMedida());
            ingredientesCustomParse.add(ingredient);
        }

        return ingredientesCustomParse;
    }
}
