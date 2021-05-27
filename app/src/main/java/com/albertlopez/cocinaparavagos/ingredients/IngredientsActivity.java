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
import com.albertlopez.cocinaparavagos.recipes.RecipesCoincidentesActivity;
import com.albertlopez.cocinaparavagos.manager.ManagerAllRecipes;
import com.albertlopez.cocinaparavagos.manager.ManagerIngredients;
import com.albertlopez.cocinaparavagos.model.Ingredient;
import java.util.ArrayList;


public class IngredientsActivity extends AppCompatActivity {

    RecyclerView recyclerViewIngrediente;
    RecyclerViewIngredientesAdaptador adaptadorIngrediente;
    ManagerIngredients managerIngredient;
    Ingredient ingredienteSeleccionado;
    ArrayList<Ingredient> ingedientesArray;
    Toolbar toolbar;
    TextView botonRedondo,botonRedondoRecetas;
    TextView textoIngredientes,textoRecetasUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        managerIngredient = new ManagerIngredients();
        setContentView(R.layout.activity_ingredients);

        toolbar = findViewById(R.id.toolbar2);
        botonRedondoRecetas = findViewById(R.id.botonRecetas);
        botonRedondo = findViewById(R.id.botonIngredientes3);
        textoRecetasUsuario = findViewById(R.id.textoRecetasUsuario);
        textoIngredientes = findViewById(R.id.textoIngredientesUsuario3);
        textoRecetasUsuario.setVisibility(View.INVISIBLE);
        textoIngredientes.setVisibility(View.INVISIBLE);
        recyclerViewIngrediente = (RecyclerView)findViewById(R.id.recyclerIngredientes);
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

        textoIngredientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaIngredientes();
            }
        });

        botonRedondo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaIngredientes();
            }
        });

        textoRecetasUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaRecetas();
            }
        });

        botonRedondoRecetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaRecetas();
            }
        });
    }

    private void listaRecetas() {
        Intent intent = new Intent(this, RecipesCoincidentesActivity.class);
        startActivity(intent);
    }

    private void listaIngredientes() {
        Intent intent = new Intent(this, IngredientsSelected.class);
        intent.putExtra("recetasBool", false);
        startActivity(intent);
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
        intent.putExtra("tipoDeDetalle",false); // aqui determinamos si la siguiente pagina es para crear recetas custom o solo para consultar
        intent.putExtra("ingredientes",managerIngredient.getIngredientsArray());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        ManagerAllRecipes.buscarRecetasQueCoincidenConLosIngredientes();
        ocultarBarras();
        super.onResume();
        comprobarIngredientesBoton();
        comprobarRecetasBoton();
    }

    private void comprobarRecetasBoton() {
        int recetasQueCoinciden = ManagerAllRecipes.getRecetasQueCoincidenDelTodo().size();
        if (recetasQueCoinciden != 0) {
            textoRecetasUsuario.setVisibility(View.VISIBLE);
            botonRedondoRecetas.setVisibility(View.VISIBLE);
            ManagerAllRecipes.buscarRecetasQueCoincidenConLosIngredientes();
            botonRedondoRecetas.setText(String.valueOf(ManagerAllRecipes.getRecetasQueCoincidenDelTodo().size()));
        } else {
            textoRecetasUsuario.setVisibility(View.INVISIBLE);
            botonRedondoRecetas.setVisibility(View.INVISIBLE);
        }
    }

    private void comprobarIngredientesBoton() {
        int recetas = ManagerAllRecipes.getIngredientesIntroducidosPorELUsuario().size();
        if (recetas != 0) {
            textoIngredientes.setVisibility(View.VISIBLE);
            botonRedondo.setVisibility(View.VISIBLE);
            botonRedondo.setText(String.valueOf(ManagerAllRecipes.getIngredientesIntroducidosPorELUsuario().size()));
        } else {
            textoIngredientes.setVisibility(View.INVISIBLE);
            botonRedondo.setVisibility(View.INVISIBLE);
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