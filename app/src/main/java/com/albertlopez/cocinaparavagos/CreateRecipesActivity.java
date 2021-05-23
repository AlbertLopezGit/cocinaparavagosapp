package com.albertlopez.cocinaparavagos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.albertlopez.cocinaparavagos.ingredients.IngredientDetailsActivity;
import com.albertlopez.cocinaparavagos.ingredients.IngredientsSelected;
import com.albertlopez.cocinaparavagos.ingredients.RecyclerViewIngredientesAdaptador;
import com.albertlopez.cocinaparavagos.manager.ManagerAllRecipes;
import com.albertlopez.cocinaparavagos.manager.ManagerAllRecipesCustom;
import com.albertlopez.cocinaparavagos.manager.ManagerIngredients;
import com.albertlopez.cocinaparavagos.manager.ManagerRecetas;
import com.albertlopez.cocinaparavagos.model.Ingredient;
import com.albertlopez.cocinaparavagos.model.IngredientCustom;
import com.albertlopez.cocinaparavagos.model.Recipe;
import com.albertlopez.cocinaparavagos.model.RecipeIngredients;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CreateRecipesActivity extends AppCompatActivity {

    Button ingredients;
    RecyclerView recyclerViewIngrediente;
    RecyclerViewIngredientesAdaptador adaptadorIngrediente;
    Ingredient ingredienteSeleccionado;
    ArrayList<IngredientCustom> ingredientCustom;
    ArrayList<Ingredient> ingredientesArray;
    ArrayList<Ingredient> ingredientesParaHacerRecetaSeleccionadoPorElUsuario;
    TextView texto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipes);
        ingredients = findViewById(R.id.ingedientsButtonInsert);
        texto = findViewById(R.id.textView6);
        ocultarBarras();
        loading();

        ingredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIngredientsActivity();
            }
        });

    }

    private void cargarRecycler() {
        recyclerViewIngrediente = (RecyclerView)findViewById(R.id.listaIngredientes);
        recyclerViewIngrediente.setLayoutManager(new GridLayoutManager(this,2));

        adaptadorIngrediente = new RecyclerViewIngredientesAdaptador(this,ingredientesParaHacerRecetaSeleccionadoPorElUsuario,0);
        recyclerViewIngrediente.setAdapter(adaptadorIngrediente);

    }


    private void openIngredientsActivity() {
        Intent intent = new Intent (this, IngredientsCustomRecipeBaseActivity.class);
        intent.putExtra("customIngredients",ingredientCustom);
        intent.putExtra("ingredientsArray",ingredientesArray);
        startActivity(intent);
    }

    public void openIngredientsDetail(Ingredient ingredienteSeleccionado) {
        Intent intent = new Intent(this, IngredientDetailsActivity.class);
        intent.putExtra("tipoDeDetalle",true); // aqui determinamos si la siguiente pagina es para crear recetas custom o solo para consultar
        intent.putExtra("ingredienteSeleccionado", ingredienteSeleccionado);
        startActivity(intent);
    }

    private void loading() {
        ingredientCustom = (ArrayList<IngredientCustom>) getIntent().getSerializableExtra("customIngredients");
        ingredientesArray = (ArrayList<Ingredient>) getIntent().getSerializableExtra("ingredientsArray");

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

    @Override
    protected void onResume() {
        super.onResume();
        ingredientesParaHacerRecetaSeleccionadoPorElUsuario = ManagerAllRecipesCustom.getIngredientesIntroducidosPorELUsuario();
        cargarRecycler();
        if (ingredientesParaHacerRecetaSeleccionadoPorElUsuario.size() >0) {
            texto.setVisibility(View.VISIBLE);
        } else {
            texto.setVisibility(View.INVISIBLE);
        }

    }


}

