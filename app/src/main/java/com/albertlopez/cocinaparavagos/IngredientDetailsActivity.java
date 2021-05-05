package com.albertlopez.cocinaparavagos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.albertlopez.cocinaparavagos.model.Ingredient;
import com.squareup.picasso.Picasso;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class IngredientDetailsActivity extends AppCompatActivity {

    private  TextView tvIngrediente;
    private  ImageView imagenIngrediente;
    private Ingredient ingredienteSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_details);

        //Para esconder la barra superior
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        loadingIngredients();

        tvIngrediente = findViewById(R.id.nombreIngrediente);
        imagenIngrediente = findViewById(R.id.imagenIngrediente);

        String image = ingredienteSeleccionado.getImagen();
        tvIngrediente.setText(ingredienteSeleccionado.getNombreIngrediente());

        Picasso.with(this).load(image).into(imagenIngrediente);

    }

    private void loadingIngredients() {
        ingredienteSeleccionado = (Ingredient) getIntent().getSerializableExtra("ingredienteSeleccionado");
    }
}