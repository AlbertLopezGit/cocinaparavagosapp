package com.albertlopez.cocinaparavagos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.albertlopez.cocinaparavagos.bbdd.Bbdd;
import com.albertlopez.cocinaparavagos.ingredients.IngredientsBaseActivity;
import com.albertlopez.cocinaparavagos.ingredients.IngredientsSettingsCustom;
import com.albertlopez.cocinaparavagos.manager.ManagerAllRecipes;
import com.albertlopez.cocinaparavagos.manager.ManagerAllRecipesCustom;
import com.albertlopez.cocinaparavagos.manager.ManagerIngredients;
import com.albertlopez.cocinaparavagos.manager.ManagerRecetas;
import com.albertlopez.cocinaparavagos.model.Ingredient;
import com.albertlopez.cocinaparavagos.model.Recipe;
import com.albertlopez.cocinaparavagos.model.RecipeIngredients;
import com.albertlopez.cocinaparavagos.recipes.RecipesBaseActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ManagerIngredients managerIngredient;
    ManagerRecetas managerRecetas;
    Button ingedientsButton, recipesButton, ingredienCustomButton;
    TextView nombreUsuario;
    Menu menu;
    ArrayList<Ingredient> ingedientesArray;

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
        nombreUsuario = findViewById(R.id.NombreUsuario);
        ingredienCustomButton = findViewById(R.id.ingredientesCustomButton);

        loadingIngredients(); // por aqui nos pasamos el ManagerIngredients de la activity Splash

        menu = navigationView.getMenu();
        nombreUsuario.setVisibility(View.INVISIBLE);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        navigationView.setCheckedItem(R.id.nav_ingredientsCustom);

        ingedientsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIngredientsActivity();
            }
        });

        ingredienCustomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomIngredientsActivity();
            }
        });

        recipesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserValidation.validado) {
                    openRecipeActivity();
                } else {
                    Toast.makeText(MainActivity.this,
                            "Opción para usuarios registrados",Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    private void loadingIngredients() {
        ingedientesArray = (ArrayList<Ingredient>) getIntent().getSerializableExtra("Ingredientes");
        ManagerAllRecipes.setIngredientsArray(ingedientesArray);
        managerIngredient.setIngredientsArray(ingedientesArray);
        managerIngredient.setIngredientsArrayFijos(ingedientesArray);

        managerRecetas.setRecipesArray(ManagerAllRecipes.getRecipes());
        managerRecetas.setRecipesIngredientsArray(ManagerAllRecipes.getRecipesCantidades());

        ManagerAllRecipes.setRecipes(managerRecetas.mezclarRecetasConSusIngredientes(managerRecetas.getRecipesArray(),ManagerAllRecipes.getRecipesCantidades(),managerIngredient.getIngredientsArray()));
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
            case R.id.nav_logout:
                SharedPreferences prefs = getSharedPreferences(getPackageName(), MODE_PRIVATE);
                SharedPreferences.Editor ed = prefs.edit();
                ed.putString("pass", "");
                ed.putString("email", "");
                ed.apply();
                UserValidation.logout();
                intent = new Intent (this, LoginActivity.class);
                startActivity(intent);
                UserValidation.restearIngredientesUltimos();
                UserValidation.restearRecetasUltimas();
                UserValidation.restearIngredientesUltimosDelete();
                ManagerAllRecipes.resetarIngredientesIntroducidosPorElUsuario();
                ManagerAllRecipesCustom.resetarIngredientesIntroducidosPorElUsuario();
                ManagerAllRecipes.resetearRecetas();
                managerIngredient.noUsuario();
                intent = new Intent(this, SplashActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK); intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.nav_ingredientsCustom:
                ArrayList<Ingredient>ingredienteCustomParse;
                intent = new Intent (this, IngredientsSettingsCustom.class);
                ingredienteCustomParse = managerIngredient.conversorCustomIngredientes();
                intent.putExtra("ingredientesCustom", ingredienteCustomParse);
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
        if (UserValidation.validado) {
            loadingIngredients();
            menu.getItem(1).setVisible(false);
            menu.getItem(2).setVisible(true);
            menu.getItem(3).setVisible(true);
            ingredienCustomButton.setVisibility(View.VISIBLE);
            nombreUsuario.setVisibility(View.VISIBLE);
            nombreUsuario.setText(UserValidation.getUser().getName());
            gestionarIngredientesNuevos();
            gestionarRecetasNuevas();
        } else {
            loadingIngredients();
            ingredienCustomButton.setVisibility(View.INVISIBLE);
            menu.getItem(2).setVisible(false);
            menu.getItem(3).setVisible(false);
            menu.getItem(1).setVisible(true);
            nombreUsuario.setVisibility(View.INVISIBLE);
            managerIngredient.noUsuario();
        }
    }

    void gestionarIngredientesNuevos() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(
                Request.Method.GET,
                Bbdd.ingredientesCustom,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            managerIngredient.addIngredientsCustom(response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(request);
    }

    private void gestionarRecetasNuevas(){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(
                Request.Method.GET,
                Bbdd.getRecetasCustom,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            managerRecetas.addRecetasCustom(response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(request);
        gestionarIngredietesDeRecetasCustom();
    }

    private void gestionarIngredietesDeRecetasCustom() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(
                Request.Method.GET,
                Bbdd.getRecetasCantidades,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            managerRecetas.addCantidadesRecetasCustom(response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(request);
        managerRecetas.parseadorRecetasCustom();

        ManagerAllRecipes.setRecipes(managerRecetas.mezclarRecetasConSusIngredientes(managerRecetas.getRecipesArray(),managerRecetas.getRecipesIngredientsArray(),managerIngredient.getIngredientsArray()));
    }

    public void openIngredientsActivity() {
        if (UserValidation.getValidado()) {
            gestionarIngredientesNuevos();
            gestionarRecetasNuevas();
        }
        Intent intent = new Intent(this, IngredientsBaseActivity.class);
        ArrayList<Ingredient> ingredientesArray;
        ingredientesArray = managerIngredient.getIngredientsArray();
        intent.putExtra("Ingredientes", ingredientesArray);
        intent.putExtra("Recetas", managerRecetas.getRecipesArray());
        intent.putExtra("RecetasCantidades", managerRecetas.getRecipesIngredientsArray());
        startActivity(intent);
    }

    public void openCustomIngredientsActivity() {
        if (UserValidation.getValidado()) {
            ManagerAllRecipes.resetarIngredientesIntroducidosPorElUsuario();
            ManagerAllRecipesCustom.resetarIngredientesIntroducidosPorElUsuario();
            gestionarIngredientesNuevos();
            gestionarRecetasNuevas();
        }
        Intent intent = new Intent(this, CustomBaseActivity.class);
        intent.putExtra("customIngredients",managerIngredient.getIngredientsCustomArray());
        intent.putExtra("ingredientsArray",managerIngredient.getIngredientsArray());
        startActivity(intent);
    }

    public void openRecipeActivity() {
        if (UserValidation.getValidado()) {
            gestionarIngredientesNuevos();
            gestionarRecetasNuevas();
        }
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