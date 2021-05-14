package com.albertlopez.cocinaparavagos;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.albertlopez.cocinaparavagos.manager.ManagerAllRecipes;
import com.albertlopez.cocinaparavagos.model.Ingredient;

import java.util.ArrayList;

public class IngredientsSelected extends AppCompatActivity {

    RecyclerView recyclerViewIngrediente;
    RecyclerView recyclerViewBotones;
    RecyclerViewIngredientesAdaptador adaptadorIngrediente,adaptadorIngrediente2;
    Ingredient ingredienteSeleccionado;
    ArrayList<Ingredient> ingredientesIntroducidosPorELUsuario = ManagerAllRecipes.getIngredientesIntroducidosPorELUsuario();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients_selected);

        recyclerViewIngrediente = (RecyclerView)findViewById(R.id.listaIngredientes);
        recyclerViewBotones = (RecyclerView)findViewById(R.id.listaBotones);

        recyclerViewIngrediente.setLayoutManager(new GridLayoutManager(this,1));
        recyclerViewBotones.setLayoutManager(new GridLayoutManager(this,1));

        adaptadorIngrediente = new RecyclerViewIngredientesAdaptador(
                this, ingredientesIntroducidosPorELUsuario, 1);
        recyclerViewIngrediente.setAdapter(adaptadorIngrediente);

        adaptadorIngrediente2 = new RecyclerViewIngredientesAdaptador(
                this, ingredientesIntroducidosPorELUsuario, 3);
        recyclerViewBotones.setAdapter(adaptadorIngrediente2);

        adaptadorIngrediente2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingredienteSeleccionado = ingredientesIntroducidosPorELUsuario.get(recyclerViewIngrediente.getChildAdapterPosition(v));
                ManagerAllRecipes.borrarIngredientes(ingredienteSeleccionado);
                nosVamos();
            }
        });

        adaptadorIngrediente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingredienteSeleccionado = ingredientesIntroducidosPorELUsuario.get(recyclerViewIngrediente.getChildAdapterPosition(v));
                openIngredientsActivity(ingredienteSeleccionado);
                finish();
            }
        });


    }

    private void nosVamos() {
        if (ManagerAllRecipes.getIngredientesIntroducidosPorELUsuario().size() > 0) {
            Intent intent = new Intent(this, IngredientsSelected.class);
            finish();
            startActivity(intent);
        } else {
            finish();
        }

    }

    public void openIngredientsActivity(Ingredient ingredienteSeleccionado) {
        Intent intent = new Intent(this, IngredientDetailsActivity.class);
        intent.putExtra("ingredienteSeleccionado", ingredienteSeleccionado);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
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