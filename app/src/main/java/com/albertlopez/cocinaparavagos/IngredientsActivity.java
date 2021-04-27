package com.albertlopez.cocinaparavagos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.albertlopez.cocinaparavagos.manager.ManagerIngredients;
import com.albertlopez.cocinaparavagos.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewIngrediente;
    private RecyclerViewIngredientesAdaptador adaptadorIngrediente;
    ManagerIngredients managerIngredient;
    ArrayList<Ingredient> IngedientesArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        managerIngredient = new ManagerIngredients();
        setContentView(R.layout.activity_ingredients);

        recyclerViewIngrediente = (RecyclerView)findViewById(R.id.recyclerIngredientes);
        recyclerViewIngrediente.setLayoutManager(new GridLayoutManager(this,2));

        loadingIngredients();

        adaptadorIngrediente = new RecyclerViewIngredientesAdaptador(this,IngedientesArray);
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





}