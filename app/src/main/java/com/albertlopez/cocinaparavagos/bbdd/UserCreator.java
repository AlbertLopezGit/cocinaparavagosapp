package com.albertlopez.cocinaparavagos.bbdd;

import android.util.Log;

import com.albertlopez.cocinaparavagos.RegisterActivity;
import com.albertlopez.cocinaparavagos.manager.ManagerUser;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import static com.albertlopez.cocinaparavagos.bbdd.Bbdd.usuario;

public class UserCreator {
    RegisterActivity registerActivity;
    ManagerUser managerUser;

    public void createUser(final String nombre, final String pass, final String email,
                           final RequestQueue requestQueue, RegisterActivity registerActivity) throws NoSuchAlgorithmException {
        managerUser = new ManagerUser();
        String encriptPass = managerUser.encryptPass(pass);

        this.registerActivity = registerActivity;
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                usuario,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Albert", response);
                        if (!duplicatedUser(response, email)) {
                            registerActivity.exit();
                        }
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
                params.put("NICKNAME",nombre);
                params.put("PASS",encriptPass);
                params.put("CORREOELECTRONICO",email);
                return params;
            }
        };


        requestQueue.add(stringRequest);
    }

    //con el "message" que retorna de la base de datos lo recorremos
    //y al encontrar el error 1062 no salta un error en la registerActivity
    //es una forma para no poder introducir dos veces el mismo usuario
    private boolean duplicatedUser(String message, String email) {
        Log.d("Albert", "duplicatedUser");
        String errorSql = "1062";
        String[] palabras = errorSql.split("\\W+");
        for (String palabra : palabras) {
            if (message.contains(palabra)) {
                Log.d("Albert", "USUARIO DUPLICADO");
                registerActivity.msgErrorUsuarioDuplicado(email);
                return true;
            }
        }
        return false;
    }
}
