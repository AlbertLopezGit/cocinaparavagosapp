package com.albertlopez.cocinaparavagos;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.albertlopez.cocinaparavagos.bbdd.Bbdd;
import com.albertlopez.cocinaparavagos.manager.ManagerAllRecipes;
import com.albertlopez.cocinaparavagos.manager.ManagerIngredients;
import com.albertlopez.cocinaparavagos.manager.ManagerRecetas;
import com.albertlopez.cocinaparavagos.manager.ManagerUser;
import com.albertlopez.cocinaparavagos.model.Ingredient;
import com.albertlopez.cocinaparavagos.model.Recipe;
import com.albertlopez.cocinaparavagos.model.RecipeIngredients;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import java.util.ArrayList;


public class SplashActivity extends AppCompatActivity implements Runnable{

    TextView loadingText;
    Handler handler;
    int stepCounter;
    ManagerIngredients managerIngredient;
    ManagerRecetas managerRecetas;
    ManagerUser managerUser;
    String pass;
    String email;
    boolean errorNetwork; //si no conecta con el server el booleano queda como true y no deja pasar al menu principal

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Para esconder la barra de los botones
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_splash);

        errorNetwork = false;
        loadingText = findViewById(R.id.loadingText);
        managerIngredient = new ManagerIngredients();
        managerRecetas = new ManagerRecetas();
        handler = new Handler();
        managerUser = new ManagerUser();
        stepCounter = 0;
        handler.postDelayed(this,0);
    }

    @Override
    public void run() {
        if (stepCounter == 0) {
            loadingText.setText("Iniciando");
            SharedPreferences prefs = getSharedPreferences(getPackageName(), MODE_PRIVATE);
            pass = prefs.getString("pass", "");
            email = prefs.getString("email", "");
            if (email.length() > 0) {
            descargarUsuario(email);
            }
            stepCounter++;
            handler.postDelayed(this,1000);
        } else if (stepCounter == 1){
            loadingText.setText("Despertando al Capibara");
            stepCounter++;
            if (pass.length() > 0) {
                comprobarContraseña(pass);
            }
            handler.postDelayed(this,1000);
        } else if (stepCounter == 2){
            loadingText.setText("Cargando Datos");
            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest request = new StringRequest(
                    Request.Method.GET,
                    Bbdd.recetasCantidades,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                managerRecetas.addCantidadesRecetas(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            stepCounter++;
                            handler.postDelayed(SplashActivity.this, 1000);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            String msg = "Network error (timeout or disconnected)";
                            Toast.makeText(SplashActivity.this,
                                    "Error al Conectar con el Servidor ",Toast.LENGTH_SHORT)
                                    .show();
                            errorNetwork = true;
                            if (error.networkResponse != null) {
                                msg = "ERROR: " + error.networkResponse.statusCode;

                            }
                            Log.d("Albert", msg);
                            stepCounter++;
                            handler.postDelayed(SplashActivity.this, 1000);
                        }
                    });
            queue.add(request);
        } else if (stepCounter == 3){
            loadingText.setText("Cargando Recetas");
            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest request = new StringRequest(
                    Request.Method.GET,
                    Bbdd.recetas,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                managerRecetas.addRecetasBase(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            stepCounter++;
                            handler.postDelayed(SplashActivity.this, 1000);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            String msg = "Network error (timeout or disconnected)";
                            Toast.makeText(SplashActivity.this,
                                    "Error al Conectar con el Servidor ",Toast.LENGTH_SHORT)
                                    .show();
                            errorNetwork = true;
                            if (error.networkResponse != null) {
                                msg = "ERROR: " + error.networkResponse.statusCode;
                            }
                            Log.d("Albert", msg);
                            stepCounter++;
                            handler.postDelayed(SplashActivity.this, 1000);
                        }
                    });
            queue.add(request);
        } else if (stepCounter == 4){
            loadingText.setText("Descargando Ingredientes");
            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest request = new StringRequest(
                    Request.Method.GET,
                    Bbdd.ingredientes,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                managerIngredient.addIngredientsBase(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            stepCounter++;
                            handler.postDelayed(SplashActivity.this, 1000);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            String msg = "Network error (timeout or disconnected)";
                            Toast.makeText(SplashActivity.this,
                                    "Error al Conectar con el Servidor ",Toast.LENGTH_SHORT)
                                    .show();
                            errorNetwork = true;
                            if (error.networkResponse != null) {
                                msg = "ERROR: " + error.networkResponse.statusCode;

                            }
                            Log.d("Albert", msg);
                            stepCounter++;
                            handler.postDelayed(SplashActivity.this, 1000);
                        }
                    });
            queue.add(request);
        }  else {
            if (!errorNetwork) {
                Intent intent = new Intent(this, MainActivity.class);
                finish();
                ArrayList<Ingredient> ingredientesArray;
                ArrayList<Recipe> recipesArray;
                ArrayList<RecipeIngredients> recipesIngredientsArray;

                recipesIngredientsArray = managerRecetas.getRecipesIngredientsArray();
                recipesArray = managerRecetas.getRecipesArray();
                ingredientesArray = managerIngredient.getIngredientsArray();

                ManagerAllRecipes.setRecipes(recipesArray);
                ManagerAllRecipes.setRecipesCantidades(recipesIngredientsArray);

                intent.putExtra("RecetasCantidades", recipesIngredientsArray);
                intent.putExtra("Recetas", recipesArray);
                intent.putExtra("Ingredientes", ingredientesArray);

                startActivity(intent);
            } else {
                handler.postDelayed(SplashActivity.this, 3000);
                finish();

            }
        }
    }

    private void comprobarContraseña(String pass) {
        UserValidation.comprobarPass(pass);
    }

    private void descargarUsuario(String email) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(
                Request.Method.GET,
                Bbdd.usuario+"/"+email,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            managerUser.addUsuario(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        errorNetwork = true;
                    }
                });
        queue.add(request);
    }
}

