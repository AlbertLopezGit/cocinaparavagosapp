package com.albertlopez.cocinaparavagos.ingredients;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.albertlopez.cocinaparavagos.R;
import com.albertlopez.cocinaparavagos.ingredients.IngredientDetailsActivity;
import com.albertlopez.cocinaparavagos.ingredients.IngredientsSelected;
import com.albertlopez.cocinaparavagos.ingredients.RecyclerViewIngredientesAdaptador;
import com.albertlopez.cocinaparavagos.manager.ManagerAllRecipesCustom;
import com.albertlopez.cocinaparavagos.manager.ManagerIngredients;
import com.albertlopez.cocinaparavagos.model.Ingredient;

import java.util.ArrayList;

public class IngredientsCustomRecipeActivity extends AppCompatActivity {

    RecyclerView recyclerViewIngrediente;
    RecyclerViewIngredientesAdaptador adaptadorIngrediente;
    ManagerIngredients managerIngredient;
    Ingredient ingredienteSeleccionado;
    ArrayList<Ingredient> ingedientesArray;
    TextView botonRedondo,textoIngredientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients_custom_recipe);

        managerIngredient = new ManagerIngredients();

        recyclerViewIngrediente = (RecyclerView)findViewById(R.id.recyclerIngredientes);
        textoIngredientes = findViewById(R.id.textoIngredientesUsuario3);
        botonRedondo = findViewById(R.id.botonIngredientes3);

        textoIngredientes.setVisibility(View.INVISIBLE);
        botonRedondo.setVisibility(View.INVISIBLE);

        recyclerViewIngrediente.setLayoutManager(new GridLayoutManager(this,2));

        loadingIngredients();

        adaptadorIngrediente = new RecyclerViewIngredientesAdaptador(this,ingedientesArray,0);

        adaptadorIngrediente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingredienteSeleccionado = ingedientesArray.get(recyclerViewIngrediente.getChildAdapterPosition(v));
                openIngredientsActivity(ingredienteSeleccionado);
            }
        });
        recyclerViewIngrediente.setAdapter(adaptadorIngrediente);

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

    private void loadingIngredients() {
        ingedientesArray = (ArrayList<Ingredient>) getIntent().getSerializableExtra("TiposIngredientes");
        ArrayList<Ingredient> AllIngredientesArray = (ArrayList<Ingredient>) getIntent().getSerializableExtra("ingredientes");
        managerIngredient.setIngredientsArray(AllIngredientesArray);
        managerIngredient.settiposIngredientsArray(ingedientesArray);
    }

    public void openIngredientsActivity(Ingredient ingredienteSeleccionado) {
        Intent intent = new Intent(this, IngredientDetailsActivity.class);
        intent.putExtra("ingredienteSeleccionado", ingredienteSeleccionado);
        intent.putExtra("tipoDeDetalle",true); // aqui determinamos si la siguiente pagina es para crear recetas custom o solo para consultar
        intent.putExtra("ingredientes",managerIngredient.getIngredientsArray());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        ocultarBarras();
        super.onResume();
        comprobarIngredientesBoton();
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