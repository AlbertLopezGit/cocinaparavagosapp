package com.albertlopez.cocinaparavagos.recipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.albertlopez.cocinaparavagos.R;
import com.albertlopez.cocinaparavagos.UserValidation;
import com.albertlopez.cocinaparavagos.ingredients.IngredientDetailsActivity;
import com.albertlopez.cocinaparavagos.manager.ManagerAllRecipes;
import com.albertlopez.cocinaparavagos.model.Ingredient;
import com.albertlopez.cocinaparavagos.model.Recipe;

import java.util.ArrayList;

public class RecipesCoincidentesActivity extends AppCompatActivity {

    RecyclerView recyclerViewRecetas;
    RecyclerViewRecipesAdaptador recyclerViewRecipesAdaptador;
    Recipe recipeSeleccionado;
    ArrayList<Recipe> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_coincidentes);
        recipes = ManagerAllRecipes.getRecetasQueCoincidenDelTodo();
        ManagerAllRecipes.buscarRecetasQueCoincidenConLosIngredientes();
        recyclerViewRecetas = (RecyclerView)findViewById(R.id.recyclerRecipe);
        recyclerViewRecetas.setLayoutManager(new GridLayoutManager(this,2));

        recyclerViewRecipesAdaptador = new RecyclerViewRecipesAdaptador(this, recipes);


        recyclerViewRecipesAdaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipeSeleccionado = recipes.get(recyclerViewRecetas.getChildAdapterPosition(v));
                Recipe recipeParse;
                recipeParse = recipeSeleccionado;
                recipeParse.resetIngredientRecipe();
                ArrayList<String> recipeString = recipeSeleccionado.getIngredientes();
                ArrayList<Ingredient> ingredientsAll = ManagerAllRecipes.getIngredientsArray();


                if (UserValidation.getValidado() && UserValidation.getRecetasParse()) {
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

    @Override
    protected void onResume() {
        recipes = ManagerAllRecipes.getRecetasQueCoincidenDelTodo();
        ManagerAllRecipes.buscarRecetasQueCoincidenConLosIngredientes();
        ocultarBarras();
        super.onResume();
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