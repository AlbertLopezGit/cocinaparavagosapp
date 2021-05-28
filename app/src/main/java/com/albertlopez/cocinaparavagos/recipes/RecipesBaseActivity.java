package com.albertlopez.cocinaparavagos.recipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.albertlopez.cocinaparavagos.R;
import com.albertlopez.cocinaparavagos.UserValidation;
import com.albertlopez.cocinaparavagos.manager.ManagerAllRecipes;
import com.albertlopez.cocinaparavagos.manager.ManagerRecetas;
import com.albertlopez.cocinaparavagos.model.Ingredient;
import com.albertlopez.cocinaparavagos.model.IngredientCustom;
import com.albertlopez.cocinaparavagos.model.Recipe;
import com.albertlopez.cocinaparavagos.model.RecipeIngredients;
import com.albertlopez.cocinaparavagos.model.RecipesIngredientsCustom;

import java.util.ArrayList;

public class RecipesBaseActivity extends AppCompatActivity {

    ManagerRecetas managerRecetas;
    RecyclerView recyclerViewRecetas;
    RecyclerViewRecipesAdaptador recyclerViewRecipesAdaptador;
    ArrayList<Recipe> recipesCustom;
    Recipe recipeSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_base);
        ocultarBarras();
        recipesCustom = new ArrayList<>();
        managerRecetas = new ManagerRecetas();
        recyclerViewRecetas = findViewById(R.id.reciclerRecipes);
        recyclerViewRecetas.setLayoutManager(new GridLayoutManager(this,1));

        loadRecipes();

        recyclerViewRecipesAdaptador = new RecyclerViewRecipesAdaptador(this,recipesCustom);
        recyclerViewRecetas.setAdapter(recyclerViewRecipesAdaptador);

        recyclerViewRecipesAdaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipeSeleccionado = recipesCustom.get(recyclerViewRecetas.getChildAdapterPosition(v));
               Recipe recipeParse;
                ArrayList<String> recipeString = recipeSeleccionado.getIngredientes();
                ArrayList<Ingredient> ingredientsAll = ManagerAllRecipes.getIngredientsArray();

                recipeParse = recipeSeleccionado;

                if (UserValidation.getValidado()) {
                    ArrayList<Ingredient> ingredientes = UserValidation.getIngredientParse();

                    for (String i :recipeString) {
                        for (Ingredient x: ingredientes) {
                            if (i.equals(x.getNombreIngrediente()) && x.getIngredienteBase() == 1) {
                                recipeParse.addIngredientRecipe(x);
                            }
                        }
                    }

                    for (String i: recipeString) {
                        for (Ingredient x:ingredientsAll) {
                            if (i.equals(x.getNombreIngrediente())) {
                                recipeParse.addIngredientRecipe(x);
                            }
                        }

                    }
                }
                openIngredientsActivity(recipeParse);
            }
        });
        recyclerViewRecetas.setAdapter(recyclerViewRecipesAdaptador);
    }

    private void openIngredientsActivity(Recipe recipeSeleccionado) {
        Intent intent = new Intent(this, RecipesDetail.class);
        intent.putExtra("recetaSeleccionada", recipeSeleccionado);
        startActivity(intent);
    }

    private void loadRecipes() {
        ArrayList<Recipe> recipesArray = (ArrayList<Recipe>) getIntent().getSerializableExtra("Recetas");
        ArrayList<RecipeIngredients> recipeIngredients = (ArrayList<RecipeIngredients>) getIntent().getSerializableExtra("RecetasCantidades");

        managerRecetas.setRecipesArray(recipesArray);
        managerRecetas.setRecipesIngredientsArray(recipeIngredients);

        for (Recipe i: recipesArray) {
            if (i.getModoReceta() == 1) {
                recipesCustom.add(i);
            }
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