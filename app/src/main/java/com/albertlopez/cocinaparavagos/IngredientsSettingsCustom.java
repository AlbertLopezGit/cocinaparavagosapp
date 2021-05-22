package com.albertlopez.cocinaparavagos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.albertlopez.cocinaparavagos.model.Ingredient;
import com.albertlopez.cocinaparavagos.model.IngredientCustom;
import com.albertlopez.cocinaparavagos.model.RecipeIngredients;

import java.util.ArrayList;

public class IngredientsSettingsCustom extends AppCompatActivity {

    Button borraIngredientes, borrarRecetas;
    ArrayList<Ingredient> arrayListCustomIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ocultarBarras();
        setContentView(R.layout.activity_ingredients_settings_custom);

        borraIngredientes = findViewById(R.id.borrarIngredientesButton);
        borrarRecetas = findViewById(R.id.borrarRecetasButton);

        loading();




        borraIngredientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarIngredientes();
            }
        });



    }

    private void loading() {
        arrayListCustomIngredients = (ArrayList<Ingredient>) getIntent().getSerializableExtra("ingredientesCustom");
    }

    private void borrarIngredientes() {
        if (arrayListCustomIngredients.size() != 0) {
            Intent intent = new Intent(this, DeleteIngredientCustom.class);
            intent.putExtra("ingredientesCustom", arrayListCustomIngredients);
            startActivity(intent);
        } else {
            Toast.makeText(IngredientsSettingsCustom.this,
                    "No hay ingredientes propios que gestionar",Toast.LENGTH_SHORT)
                    .show();
        }

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