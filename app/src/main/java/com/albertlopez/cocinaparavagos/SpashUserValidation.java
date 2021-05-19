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

public class SpashUserValidation extends AppCompatActivity implements Runnable{

    TextView loadingText;
    Handler handler;
    int stepCounter;
    boolean errorNetwork; //si no conecta con el server el booleano queda como true y no deja pasar al menu principal
    ManagerUser managerUser;
    String email;
    String nombre;

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
        setContentView(R.layout.activity_spash_user_validation);

        managerUser = new ManagerUser();
        errorNetwork = false;
        loadingText = findViewById(R.id.loadingText);
        handler = new Handler();
        stepCounter = 0;
        handler.postDelayed(this,0);
    }

    @Override
    public void run() {
        loading();
         if (stepCounter == 0){
            loadingText.setText("Buscando Usuario");
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
                            stepCounter++;
                            handler.postDelayed(SpashUserValidation.this, 1000);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            String msg = "Network error (timeout or disconnected)";
                            Toast.makeText(SpashUserValidation.this,
                                    "Error al Conectar con el Servidor ",Toast.LENGTH_SHORT)
                                    .show();
                            errorNetwork = true;
                            if (error.networkResponse != null) {
                                msg = "ERROR: " + error.networkResponse.statusCode;

                            }
                            Log.d("Albert", msg);
                            stepCounter++;
                            handler.postDelayed(SpashUserValidation.this, 1000);
                        }
                    });
            queue.add(request);
        }  else {
            if (!errorNetwork) {
                Intent intent = new Intent(this, MainActivity.class);
                finish();
                startActivity(intent);
            } else {
                handler.postDelayed(SpashUserValidation.this, 3000);
                finish();

            }
        }
    }

    private void loading() {
        email = (String) getIntent().getSerializableExtra("pass");
        nombre = (String) getIntent().getSerializableExtra("pass");
    }
}