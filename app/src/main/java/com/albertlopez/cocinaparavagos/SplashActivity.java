package com.albertlopez.cocinaparavagos;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.albertlopez.cocinaparavagos.bbdd.Bbdd;
import com.albertlopez.cocinaparavagos.manager.ManagerIngredients;
import com.albertlopez.cocinaparavagos.model.Ingredient;
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
            loadingText.setText("Cargando Datos");
            stepCounter++;
            handler.postDelayed(this,1000);
        } else if (stepCounter == 3){
            loadingText.setText("Cargando Recetas");
            stepCounter++;
            handler.postDelayed(this,1000);
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
                            handler.postDelayed(SplashActivity.this, 2000);
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
                ingredientesArray = managerIngredient.getIngredientsArray();
                intent.putExtra("Ingredientes", ingredientesArray);
                startActivity(intent);
            } else {
                handler.postDelayed(SplashActivity.this, 2000);
                finish();
            }
        }
    }
}



