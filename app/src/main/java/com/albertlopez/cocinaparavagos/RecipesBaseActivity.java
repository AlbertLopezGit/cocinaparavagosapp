package com.albertlopez.cocinaparavagos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.albertlopez.cocinaparavagos.manager.ManagerRecetas;
import com.albertlopez.cocinaparavagos.model.Ingredient;
import com.albertlopez.cocinaparavagos.model.Recipe;
import com.albertlopez.cocinaparavagos.model.RecipeIngredients;

import java.util.ArrayList;

public class RecipesBaseActivity extends AppCompatActivity {

    ManagerRecetas managerRecetas;
    RecyclerView recyclerViewRecetas;
    RecyclerViewRecipesAdaptador recyclerViewRecipesAdaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_base);
        ocultarBarras();
        managerRecetas = new ManagerRecetas();
        recyclerViewRecetas = findViewById(R.id.reciclerRecipes);
        recyclerViewRecetas.setLayoutManager(new GridLayoutManager(this,1));

        loadRecipes();

        recyclerViewRecipesAdaptador = new RecyclerViewRecipesAdaptador(this,managerRecetas.getRecipesArray());
        recyclerViewRecetas.setAdapter(recyclerViewRecipesAdaptador);
    }

    private void loadRecipes() {
        ArrayList<Recipe> recipesArray = (ArrayList<Recipe>) getIntent().getSerializableExtra("Recetas");
        ArrayList<RecipeIngredients> recipeIngredients = (ArrayList<RecipeIngredients>) getIntent().getSerializableExtra("RecetasCantidades");

        managerRecetas.setRecipesArray(recipesArray);
        managerRecetas.setRecipesIngredientsArray(recipeIngredients);
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