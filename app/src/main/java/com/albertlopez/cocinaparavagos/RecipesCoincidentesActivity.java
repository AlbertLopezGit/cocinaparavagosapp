package com.albertlopez.cocinaparavagos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.albertlopez.cocinaparavagos.manager.ManagerAllRecipes;

public class RecipesCoincidentesActivity extends AppCompatActivity {

    RecyclerView recyclerViewRecetas;
    RecyclerViewRecipesAdaptador recyclerViewRecipesAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_coincidentes);

        recyclerViewRecetas = (RecyclerView)findViewById(R.id.recyclerRecipe);
        recyclerViewRecetas.setLayoutManager(new GridLayoutManager(this,2));

        recyclerViewRecipesAdaptador = new RecyclerViewRecipesAdaptador(this, ManagerAllRecipes.getRecetasQueCoincidenDelTodo());
        recyclerViewRecetas.setAdapter(recyclerViewRecipesAdaptador);
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