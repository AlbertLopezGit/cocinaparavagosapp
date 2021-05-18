package com.albertlopez.cocinaparavagos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.albertlopez.cocinaparavagos.ingredients.IngredientsBaseActivity;
import com.albertlopez.cocinaparavagos.manager.ManagerAllRecipes;
import com.albertlopez.cocinaparavagos.manager.ManagerIngredients;
import com.albertlopez.cocinaparavagos.manager.ManagerRecetas;
import com.albertlopez.cocinaparavagos.model.Ingredient;
import com.albertlopez.cocinaparavagos.model.Recipe;
import com.albertlopez.cocinaparavagos.model.RecipeIngredients;
import com.albertlopez.cocinaparavagos.recipes.RecipesBaseActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ManagerIngredients managerIngredient;
    ManagerRecetas managerRecetas;
    Button ingedientsButton, recipesButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        managerIngredient = new ManagerIngredients();
        managerRecetas = new ManagerRecetas();
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        ingedientsButton = findViewById(R.id.ingedientsButton);
        recipesButton = findViewById(R.id.recetasButton);

        loadingIngredients(); // por aqui nos pasamos el ManagerIngredients de la activity Splash

        Menu menu = navigationView.getMenu();

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        ingedientsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIngredientsActivity();
            }
        });

        recipesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecipeActivity();
            }
        });
    }

    private void loadingIngredients() {
        ArrayList<Ingredient> ingedientesArray = (ArrayList<Ingredient>) getIntent().getSerializableExtra("Ingredientes");
        ArrayList<Recipe> recipesArray = (ArrayList<Recipe>) getIntent().getSerializableExtra("Recetas");
        ArrayList<RecipeIngredients> recipeIngredients = (ArrayList<RecipeIngredients>) getIntent().getSerializableExtra("RecetasCantidades");

        managerIngredient.setIngredientsArray(ingedientesArray);
        managerRecetas.setRecipesArray(recipesArray);
        managerRecetas.setRecipesIngredientsArray(recipeIngredients);

        ManagerAllRecipes.setRecipes(managerRecetas.mezclarRecetasConSusIngredientes(recipesArray,recipeIngredients));
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_login:
                Intent intent = new Intent (this, LoginActivity.class);
                startActivity(intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.setCheckedItem(R.id.nav_home);
    }

    public void openIngredientsActivity() {
        Intent intent = new Intent(this, IngredientsBaseActivity.class);
        ArrayList<Ingredient> ingredientesArray;
        ingredientesArray = managerIngredient.getIngredientsArray();
        intent.putExtra("Ingredientes", ingredientesArray);
        startActivity(intent);
    }

    public void openRecipeActivity() {
        Intent intent = new Intent(this, RecipesBaseActivity.class);
        ArrayList<Recipe> recipesArray;
        ArrayList<RecipeIngredients> recipeIngredientsArray;
        recipesArray = managerRecetas.getRecipesArray();
        recipeIngredientsArray = managerRecetas.getRecipesIngredientsArray();
        intent.putExtra("Recetas", recipesArray);
        intent.putExtra("RecetasCantidades", recipeIngredientsArray);
        startActivity(intent);
    }
}