package com.albertlopez.cocinaparavagos.bbdd;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.albertlopez.cocinaparavagos.CreateIngredientesActivity;
import com.albertlopez.cocinaparavagos.UserValidation;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.albertlopez.cocinaparavagos.bbdd.Bbdd.ingredientesCustom;


public class IngredientCreator {
    String idUsuario;
    CreateIngredientesActivity createIngredientesActivity;

    public void createIngredientCustom(final String name, final String medida, final String tipo,
                                       final RequestQueue requestQueue, CreateIngredientesActivity createIngredientesActivity) {

        this.createIngredientesActivity = createIngredientesActivity;
        idUsuario = UserValidation.getUser().getIdUsuario();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                ingredientesCustom,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (!duplicatedIngredient(response)) {

                        }
                    }
                } ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        createIngredientesActivity.ingredienteInsertado();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("NOMBREINGREDIENTECUSTOM",name);
                params.put("CLASIFICACIONINGREDIENTECUSTOM",tipo);
                params.put("INGREDIENTEDEBASECUSTOM","1");
                params.put("IMAGEN_INGREDIENTE_CUSTOM",Bbdd.ingredientesCustomGeneralImg);
                params.put("VALOR_MEDIDA_CUSTOM",medida);
                params.put("IDUSUARIO",idUsuario);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private boolean duplicatedIngredient(String message) {
        Log.d("Albert", "itemduplicado");
        String errorSql = "1062";
        String[] palabras = errorSql.split("\\W+");
        for (String palabra : palabras) {
            if (message.contains(palabra)) {
                Log.d("Albert", "INGREDIENTE DUPLICADO");
                createIngredientesActivity.ingredienteRepetido();
                return true;
            }
        }
        createIngredientesActivity.ingredienteInsertado();
        return false;
    }

}
