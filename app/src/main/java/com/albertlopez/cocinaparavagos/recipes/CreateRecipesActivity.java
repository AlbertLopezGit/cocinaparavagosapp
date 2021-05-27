package com.albertlopez.cocinaparavagos.recipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.albertlopez.cocinaparavagos.R;
import com.albertlopez.cocinaparavagos.UserValidation;
import com.albertlopez.cocinaparavagos.bbdd.RecetasCreator;
import com.albertlopez.cocinaparavagos.ingredients.IngredientDetailsActivity;
import com.albertlopez.cocinaparavagos.ingredients.IngredientsCustomRecipeBaseActivity;
import com.albertlopez.cocinaparavagos.ingredients.RecyclerViewIngredientesAdaptador;
import com.albertlopez.cocinaparavagos.manager.ManagerAllRecipesCustom;
import com.albertlopez.cocinaparavagos.model.Ingredient;
import com.albertlopez.cocinaparavagos.model.IngredientCustom;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class CreateRecipesActivity extends AppCompatActivity {

    Button ingredients,insertarReceta;
    RecyclerView recyclerViewIngrediente;
    RecyclerViewIngredientesAdaptador adaptadorIngrediente;
    ArrayList<IngredientCustom> ingredientCustom;
    ArrayList<Ingredient> ingredientesArray;
    ArrayList<Ingredient> ingredientesParaHacerRecetaSeleccionadoPorElUsuario;
    TextView texto;
    EditText nombreReceta,descripcion;
    String nombreRecetaString,descripcionString;
    RecetasCreator recetasCreator;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recetasCreator = new RecetasCreator();
        setContentView(R.layout.activity_create_recipes);
        ingredients = findViewById(R.id.ingedientsButtonInsert);
        texto = findViewById(R.id.textView6);
        nombreReceta = findViewById(R.id.nombreIngredienteCustom2);
        descripcion = findViewById(R.id.editTextTextMultiLine3);
        insertarReceta = findViewById(R.id.insertarIngredienteCustom3);
        ocultarBarras();
        loading();

        requestQueue = Volley.newRequestQueue(this);

        ingredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIngredientsActivity();
            }
        });

        insertarReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkAntesDeInsertar()) {
                    insertarRecetaCustom();
                }
            }
        });
    }

    private void insertarRecetaCustom() {
        recetasCreator.createCustomRecetas(nombreRecetaString,descripcionString,requestQueue,this);
    }

    private boolean comprobarRecetasRepetidasLocal(String name) {
        ArrayList<String> ultimos;
        ultimos = UserValidation.getRecetasUltimas();
        for (String i: ultimos) {
            if (name.equals(i)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkAntesDeInsertar() {
        nombreRecetaString = nombreReceta.getText().toString().trim();
        descripcionString = descripcion.getText().toString().trim();


        if (comprobarRecetasRepetidasLocal(nombreRecetaString)) {
            Toast.makeText(CreateRecipesActivity.this,
                    "La receta ya existe",Toast.LENGTH_SHORT)
                    .show();
            return false;
        }

        if (nombreRecetaString.length() < 1) {
            Toast.makeText(CreateRecipesActivity.this,
                    "El nombre no puede estar vacío",Toast.LENGTH_SHORT)
                    .show();
            return false;
        } else if (descripcionString.length() < 1) {
            Toast.makeText(CreateRecipesActivity.this,
                    "La descripción no puede estar vacía",Toast.LENGTH_SHORT)
                    .show();
            return false;
        } else if (ingredientesParaHacerRecetaSeleccionadoPorElUsuario.size() < 1) {
            Toast.makeText(CreateRecipesActivity.this,
                    "Debes insertar algún ingrediente",Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        return true;

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

    public void recetaInsertada() {
        Toast.makeText(CreateRecipesActivity.this,
                "Receta insertada",Toast.LENGTH_SHORT)
                .show();
        UserValidation.addUltimaReceta(nombreRecetaString);
        nombreReceta.setText("");
        descripcion.setText("");
        ManagerAllRecipesCustom.resetarIngredientesIntroducidosPorElUsuario();
        ingredientesParaHacerRecetaSeleccionadoPorElUsuario = ManagerAllRecipesCustom.getIngredientesIntroducidosPorELUsuario();
        cargarRecycler();

    }
}

