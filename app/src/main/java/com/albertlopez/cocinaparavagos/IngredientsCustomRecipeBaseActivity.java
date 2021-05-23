package com.albertlopez.cocinaparavagos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.albertlopez.cocinaparavagos.ingredients.AdaptadorIngredientesBase;
import com.albertlopez.cocinaparavagos.ingredients.IngredientsActivity;
import com.albertlopez.cocinaparavagos.ingredients.IngredientsSelected;
import com.albertlopez.cocinaparavagos.manager.ManagerAllRecipes;
import com.albertlopez.cocinaparavagos.manager.ManagerAllRecipesCustom;
import com.albertlopez.cocinaparavagos.manager.ManagerIngredients;
import com.albertlopez.cocinaparavagos.model.Ingredient;
import com.albertlopez.cocinaparavagos.model.IngredientCustom;

import java.util.ArrayList;

public class IngredientsCustomRecipeBaseActivity extends AppCompatActivity {

    ListView lista;
    AdaptadorIngredientesBase adapter;
    TextView botonRedondo;
    TextView textoIngredientes;
    ArrayList<Ingredient> ingredientsArray;
    ManagerIngredients managerIngredient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ingredients_custom_recipe_base);
        ocultarBarras();
        managerIngredient = new ManagerIngredients();

        loading();
        botonRedondo = findViewById(R.id.botonIngredientesCustom);
        textoIngredientes = findViewById(R.id.textoIngredientesUsuarioCustom);
        lista = (ListView) findViewById(R.id.listIngredients);
        adapter = new AdaptadorIngredientesBase(managerIngredient.viewIngredientsBase(),this);
        textoIngredientes.setVisibility(View.INVISIBLE);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(),String.valueOf(position),Toast.LENGTH_SHORT).show(); //chuleta para ver la posicion de la lista
                ArrayList<Ingredient> tiposIngredientes = managerIngredient.returnIngredientsForTipeIngredients(position);
                openIngredientsActivity(tiposIngredientes);
            }
        });

        botonRedondo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaIngredientes();
            }
        });

        textoIngredientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaIngredientes();
            }
        });
    }

    private void openIngredientsActivity(ArrayList<Ingredient> tiposIngredientes) {
        Intent intent = new Intent(this, IngredientsCustomRecipeActivity.class);
        intent.putExtra("TiposIngredientes", tiposIngredientes);
        intent.putExtra("ingredientes",managerIngredient.getIngredientsArray());
        startActivity(intent);
    }


    private void loading() {
        ingredientsArray = (ArrayList<Ingredient> ) getIntent().getSerializableExtra("ingredientsArray");
        managerIngredient.setIngredientsArray(ingredientsArray);
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
        ocultarBarras();
        comprobarIngredientesBoton();
    }

    private void comprobarIngredientesBoton() {
        int recetas = ManagerAllRecipesCustom.getIngredientesIntroducidosPorELUsuario().size();
        if (recetas != 0) {
            textoIngredientes.setVisibility(View.VISIBLE);
            botonRedondo.setVisibility(View.VISIBLE);
            botonRedondo.setText(String.valueOf(ManagerAllRecipesCustom.getIngredientesIntroducidosPorELUsuario().size()));
        } else {
            textoIngredientes.setVisibility(View.INVISIBLE);
            botonRedondo.setVisibility(View.INVISIBLE);
        }
    }

    private void listaIngredientes() {
        Intent intent = new Intent(this, IngredientsSelected.class);
        intent.putExtra("recetasBool", true);
        startActivity(intent);
    }
}