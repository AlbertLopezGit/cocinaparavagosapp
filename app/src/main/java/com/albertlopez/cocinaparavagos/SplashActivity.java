package com.albertlopez.cocinaparavagos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.albertlopez.cocinaparavagos.bbdd.Bbdd;
import com.albertlopez.cocinaparavagos.manager.ManagerIngredients;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

public class SplashActivity extends AppCompatActivity implements Runnable{

    TextView loadingText;
    Handler handler;
    int stepCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        loadingText = findViewById(R.id.loadingText);

        handler = new Handler();
        stepCounter = 0;

        handler.postDelayed(this,0);

    }

    @Override
    public void run() {
        if (stepCounter == 0) {
            loadingText.setText("Iniciando");
            stepCounter++;
            handler.postDelayed(this,1000);
        } else if (stepCounter == 1){
            loadingText.setText("Despertando al Capibara");
            stepCounter++;
            handler.postDelayed(this,1000);
        } else if (stepCounter == 2){
            //espacio para lo que haga falta sharesPreferences;

        } else if (stepCounter == 3){
            loadingText.setText("Descargando Ingredientes");
            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest request = new StringRequest(
                    Request.Method.GET,
                    Bbdd.ingredientes,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Gson gson = new Gson();
                            ManagerIngredients managerIngredients = gson.fromJson(response, ManagerIngredients.class);
                            LegoThemes themes = managerIngredients.getFirstLevelThemes();
                            String themesJson = gson.toJson(themes);
                            SharedPreferences prefs = getSharedPreferences(getPackageName(), MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("themesJson", themesJson);
                            editor.apply();
                            stepCounter++;
                            handler.postDelayed(SplashActivity.this, 1000);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            String msg = "Network error (timeout or disconnected)";
                            if (error.networkResponse != null) {
                                msg = "ERROR: " + error.networkResponse.statusCode;
                            }
                            Log.d("Albert", msg);
                            stepCounter++;
                            handler.postDelayed(SplashActivity.this, 1000);
                        }
                    });
            queue.add(request);
                        }
                    }
                    );

        }
    }


}