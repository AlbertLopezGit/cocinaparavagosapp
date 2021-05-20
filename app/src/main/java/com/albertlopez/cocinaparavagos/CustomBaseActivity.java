package com.albertlopez.cocinaparavagos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CustomBaseActivity extends AppCompatActivity {

    Button ingredientes,recetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_base);
        ocultarBarras();

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

    private void openRecetasCustomActivity() {
    }

    private void openIngredientsCustomActivity() {
        Intent intent = new Intent (this, CreateIngredientesActivity.class);
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