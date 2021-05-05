package com.albertlopez.cocinaparavagos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.albertlopez.cocinaparavagos.manager.ManagerIngredients;
import com.albertlopez.cocinaparavagos.model.Ingredient;
import java.util.ArrayList;


public class IngredientsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewIngrediente;
    private RecyclerViewIngredientesAdaptador adaptadorIngrediente;
    ManagerIngredients managerIngredient;
    Ingredient ingredienteSeleccionado;
    ArrayList<Ingredient> IngedientesArray;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Para esconder la barra superior
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        managerIngredient = new ManagerIngredients();
        setContentView(R.layout.activity_ingredients);
        toolbar = findViewById(R.id.toolbar2);

        recyclerViewIngrediente = (RecyclerView)findViewById(R.id.recyclerIngredientes);
        recyclerViewIngrediente.setLayoutManager(new GridLayoutManager(this,2));

        loadingIngredients();

        adaptadorIngrediente = new RecyclerViewIngredientesAdaptador(this,IngedientesArray);

        adaptadorIngrediente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingredienteSeleccionado = IngedientesArray.get(recyclerViewIngrediente.getChildAdapterPosition(v));
                openIngredientsActivity(ingredienteSeleccionado);
            }
        });

        recyclerViewIngrediente.setAdapter(adaptadorIngrediente);
    }

    private void loadingIngredients() {
        IngedientesArray = (ArrayList<Ingredient>) getIntent().getSerializableExtra("TiposIngredientes");
        ArrayList<Ingredient> AllIngredientesArray = (ArrayList<Ingredient>) getIntent().getSerializableExtra("ingredientes");
        System.out.println("CANTIDAD "+IngedientesArray.size());
        System.out.println("CANTIDAD TOTAL "+ AllIngredientesArray.size());
        managerIngredient.setIngredientsArray(AllIngredientesArray);
        managerIngredient.settiposIngredientsArray(IngedientesArray);
    }

    public void openIngredientsActivity(Ingredient ingredienteSeleccionado) {
        Intent intent = new Intent(this, IngredientDetailsActivity.class);
        intent.putExtra("ingredienteSeleccionado", ingredienteSeleccionado);
        intent.putExtra("ingredientes",managerIngredient.getIngredientsArray());
        startActivity(intent);
    }





}