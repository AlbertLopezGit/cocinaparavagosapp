package com.albertlopez.cocinaparavagos.recipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.albertlopez.cocinaparavagos.R;
import com.albertlopez.cocinaparavagos.ingredients.RecyclerViewIngredientesAdaptador;
import com.albertlopez.cocinaparavagos.model.Ingredient;
import com.albertlopez.cocinaparavagos.model.Recipe;
import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecipesDetail extends AppCompatActivity {
    Recipe recetaSeleccionada;
    TextView tituloReceta,descripcionReceta;
    ImageView imagenRecetas;
    ArrayList<Ingredient> ingedientesArray;
    RecyclerView ingredientesDetails;
    RecyclerViewIngredientesAdaptador adaptadorIngrediente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_detail);
        ocultarBarras();
        tituloReceta = findViewById(R.id.tituloReceta);
        descripcionReceta = findViewById(R.id.descripcionReceta);
        imagenRecetas = findViewById(R.id.imagenRecetaDetalles);
        ingredientesDetails = (RecyclerView)findViewById(R.id.IgredientesDetails);
        ingredientesDetails.setLayoutManager(new GridLayoutManager(this,2));

        loadingIngredients();
        adaptadorIngrediente = new RecyclerViewIngredientesAdaptador(this,ingedientesArray,0);
        ingredientesDetails.setAdapter(adaptadorIngrediente);
        insertDetails();
    }

    private void insertDetails() {
        tituloReceta.setText(recetaSeleccionada.getNombreReceta());
        descripcionReceta.setText(recetaSeleccionada.getDescripcion());
        String imagen = recetaSeleccionada.getImagenReceta();
        Picasso.with(this).load(imagen).into(imagenRecetas);
    }

    private void loadingIngredients() {
        recetaSeleccionada = (Recipe) getIntent().getSerializableExtra("recetaSeleccionada");
        ingedientesArray = recetaSeleccionada.getIngredientsArray();
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