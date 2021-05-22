package com.albertlopez.cocinaparavagos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.albertlopez.cocinaparavagos.model.Ingredient;
import com.albertlopez.cocinaparavagos.model.IngredientCustom;

import java.util.ArrayList;

public class CustomBaseActivity extends AppCompatActivity {

    Button ingredientes,recetas;
    ArrayList<IngredientCustom> ingredientCustom;

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
    }

    private void openRecetasCustomActivity() {
    }

    private void openIngredientsCustomActivity() {
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