package com.albertlopez.cocinaparavagos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.albertlopez.cocinaparavagos.ingredients.RecyclerViewIngredientesAdaptador;
import com.albertlopez.cocinaparavagos.model.Ingredient;

import java.util.ArrayList;

public class DeleteIngredientCustom extends AppCompatActivity {

    RecyclerViewIngredientesAdaptador adaptadorRecyclerViewIngredientes;
    RecyclerView recyclerViewBorrarIngredientes;
    ArrayList<Ingredient> arrayListCustomIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_ingredient_custom);

        recyclerViewBorrarIngredientes = (RecyclerView)findViewById(R.id.reciclerViewBorrarIngredientes);
        recyclerViewBorrarIngredientes.setLayoutManager(new GridLayoutManager(this,1));
        ocultarBarras();
        loading();


        adaptadorRecyclerViewIngredientes = new RecyclerViewIngredientesAdaptador(this,arrayListCustomIngredients,4);

        recyclerViewBorrarIngredientes.setAdapter(adaptadorRecyclerViewIngredientes);

    }

    private void loading() {
        arrayListCustomIngredients = (ArrayList<Ingredient>) getIntent().getSerializableExtra("ingredientesCustom");
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