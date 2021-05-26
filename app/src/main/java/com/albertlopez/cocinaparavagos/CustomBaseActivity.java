package com.albertlopez.cocinaparavagos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.albertlopez.cocinaparavagos.bbdd.Bbdd;
import com.albertlopez.cocinaparavagos.manager.ManagerAllRecipes;
import com.albertlopez.cocinaparavagos.manager.ManagerIngredients;
import com.albertlopez.cocinaparavagos.manager.ManagerRecetas;
import com.albertlopez.cocinaparavagos.model.Ingredient;
import com.albertlopez.cocinaparavagos.model.IngredientCustom;
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

public class CustomBaseActivity extends AppCompatActivity {

    Button ingredientes,recetas;
    ArrayList<IngredientCustom> ingredientCustom;
    ArrayList<Ingredient> ingredientesArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_base);
        ocultarBarras();
        loading();
        ingredientes = findViewById(R.id.ingedientsButton);
        recetas = findViewById(R.id.recetasButton);


        ingredientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIngredientsCustomActivity();
            }
        });

        recetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecetasCustomActivity();
            }
        });
    }

    private void loading() {
        ingredientCustom = (ArrayList<IngredientCustom>) getIntent().getSerializableExtra("customIngredients");
        ingredientesArray = (ArrayList<Ingredient>) getIntent().getSerializableExtra("ingredientsArray");
    }

    private void openRecetasCustomActivity() {
        finish();
        Intent intent = new Intent (this, CreateRecipesActivity.class);
        intent.putExtra("customIngredients",ingredientCustom);
        intent.putExtra("ingredientsArray",ingredientesArray);
        startActivity(intent);
    }

    private void openIngredientsCustomActivity() {
        finish();
        Intent intent = new Intent (this, CreateIngredientesActivity.class);
        intent.putExtra("customIngredients",ingredientCustom);
        startActivity(intent);
    }

    private void ocultarBarras(){
        //Para esconder la barra superior
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

}