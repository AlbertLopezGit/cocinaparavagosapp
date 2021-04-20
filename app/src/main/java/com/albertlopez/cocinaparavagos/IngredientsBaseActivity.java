package com.albertlopez.cocinaparavagos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.albertlopez.cocinaparavagos.manager.ManagerIngredients;
import com.albertlopez.cocinaparavagos.model.Ingredient;

import java.util.ArrayList;

public class IngredientsBaseActivity extends AppCompatActivity {

    ManagerIngredients managerIngredient;
    ListView lista;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        managerIngredient = new ManagerIngredients();
        setContentView(R.layout.activity_ingredients_base);
        loadingIngredients();

        lista = (ListView) findViewById(R.id.listIngredients);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,managerIngredient.viewAllIngredients());
        lista.setAdapter(adapter);
    }

    private void loadingIngredients() {
        ArrayList<Ingredient> IngedientesArray = (ArrayList<Ingredient>) getIntent().getSerializableExtra("Ingredientes");
        managerIngredient.setIngredientsArray(IngedientesArray);
    }
}