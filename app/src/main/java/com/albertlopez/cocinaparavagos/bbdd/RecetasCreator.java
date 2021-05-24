package com.albertlopez.cocinaparavagos.bbdd;

import android.util.Log;

import com.albertlopez.cocinaparavagos.CreateRecipesActivity;
import com.albertlopez.cocinaparavagos.UserValidation;
import com.albertlopez.cocinaparavagos.manager.ManagerAllRecipesCustom;
import com.albertlopez.cocinaparavagos.model.Ingredient;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.albertlopez.cocinaparavagos.bbdd.Bbdd.postRecetasCantidades;
import static com.albertlopez.cocinaparavagos.bbdd.Bbdd.postRecetasCustom;
import static com.albertlopez.cocinaparavagos.bbdd.Bbdd.recetasCustomImg;

public class RecetasCreator {
    String idUsuario;
    ArrayList<Ingredient>ingredienteInsertadosPorElUsuario;
    RequestQueue requestQueueRecipe;
    String nombreReceta;
    CreateRecipesActivity createRecipesActivityHome;

    public void createCustomRecetas(final String nombreRecetaString, final String descripcionString, final RequestQueue requestQueue, CreateRecipesActivity createRecipesActivity) {
        System.out.println("INTENTO INSERTAR");
        createRecipesActivityHome = createRecipesActivity;
        requestQueueRecipe =  requestQueue;
        nombreReceta = nombreRecetaString;
        idUsuario = UserValidation.getUser().getIdUsuario();
        ingredienteInsertadosPorElUsuario = ManagerAllRecipesCustom.getIngredientesIntroducidosPorELUsuario();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                postRecetasCustom,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                } ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("NOMBRERECETACUSTOM",nombreRecetaString);
                params.put("DESCRIPCIONRECETACUSTOM",descripcionString);
                params.put("NOMBREINGREDIENTESTOTALESCUSTOM","ingredientes");
                params.put("RECETADEBASECUSTOM","1");
                params.put("IMAGEN_RECETA_CUSTOM",recetasCustomImg);
                params.put("IDUSUARIO",idUsuario);
                return params;
            }
        };
        requestQueueRecipe.add(stringRequest);
        insertarCantidades();
    }

    private void insertarCantidades() {
        for (Ingredient i: ingredienteInsertadosPorElUsuario) {
            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    postRecetasCantidades,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            createRecipesActivityHome.recetaInsertada();
                        }
                    } ,
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("NOMBRERECETACUSTOM",nombreReceta);
                    params.put("NOMBREINGREDIENTECUSTOM",i.getNombreIngrediente());
                    params.put("CANTIDAD_INGREDIENTE_CUSTOM",String.valueOf(i.getCantidad()));
                    params.put("IDUSUARIO",idUsuario);
                    return params;
                }
            };
            requestQueueRecipe.add(stringRequest);
        }

    }

}

