package com.albertlopez.cocinaparavagos;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.albertlopez.cocinaparavagos.manager.ManagerAllRecipes;
import com.albertlopez.cocinaparavagos.manager.ManagerIngredients;
import com.albertlopez.cocinaparavagos.model.Ingredient;
import java.util.ArrayList;

public class IngredientsBaseActivity extends AppCompatActivity{

    ManagerIngredients managerIngredient;
    ListView lista;
    AdaptadorIngredientesBase adapter;
    TextView botonRedondo;
    TextView textoIngredientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        managerIngredient = new ManagerIngredients();
        setContentView(R.layout.activity_ingredients_base);
        loadingIngredients();

        lista = (ListView) findViewById(R.id.listIngredients);
        adapter = new AdaptadorIngredientesBase(managerIngredient.viewIngredientsBase(),this);
        lista.setAdapter(adapter);
        botonRedondo = findViewById(R.id.botonIngredientes);
        textoIngredientes = findViewById(R.id.textoIngredientesUsuario);
        textoIngredientes.setVisibility(View.INVISIBLE);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Toast.makeText(getApplicationContext(),String.valueOf(position),Toast.LENGTH_SHORT).show(); //chuleta para ver la posicion de la lista
                ArrayList<Ingredient> tiposIngredientes = managerIngredient.returnIngredientsForTipeIngredients(position);
                openIngredientsActivity(tiposIngredientes);
            }
        });

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
    }

    private void listaIngredientes() {
        Intent intent = new Intent(this, IngredientsSelected.class);
        startActivity(intent);
    }

    private void loadingIngredients() {
        ArrayList<Ingredient> IngedientesArray = (ArrayList<Ingredient>) getIntent().getSerializableExtra("Ingredientes");
        managerIngredient.setIngredientsArray(IngedientesArray);
    }

    public void openIngredientsActivity(ArrayList<Ingredient> tiposIngredientes) {
        Intent intent = new Intent(this, IngredientsActivity.class);
        intent.putExtra("TiposIngredientes", tiposIngredientes);
        intent.putExtra("ingredientes",managerIngredient.getIngredientsArray());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ManagerAllRecipes.buscarRecetasQueCoincidenConLosIngredientes();
        ocultarBarras();
        if (ManagerAllRecipes.getIngredientesIntroducidosPorELUsuario().size() != 0) {
            textoIngredientes.setVisibility(View.VISIBLE);
            botonRedondo.setVisibility(View.VISIBLE);
            botonRedondo.setText(String.valueOf(ManagerAllRecipes.getIngredientesIntroducidosPorELUsuario().size()));
            System.out.println(ManagerAllRecipes.getIngredientesIntroducidosPorELUsuario().size());
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