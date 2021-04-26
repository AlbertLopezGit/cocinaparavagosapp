package com.albertlopez.cocinaparavagos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.albertlopez.cocinaparavagos.manager.ManagerIngredients;
import com.albertlopez.cocinaparavagos.model.Ingredient;

import java.util.ArrayList;

public class IngredientsActivity extends AppCompatActivity {

    ManagerIngredients managerIngredient;
    ListView lista;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        managerIngredient = new ManagerIngredients();
        setContentView(R.layout.activity_ingredients);
        loadingIngredients();

        lista = (ListView) findViewById(R.id.listIngredients);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,managerIngredient.viewIngredients());
        lista.setAdapter(adapter);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });
    }

    private void loadingIngredients() {
        ArrayList<Ingredient> IngedientesArray = (ArrayList<Ingredient>) getIntent().getSerializableExtra("TiposIngredientes");
        ArrayList<Ingredient> AllIngredientesArray = (ArrayList<Ingredient>) getIntent().getSerializableExtra("ingredientes");
        System.out.println("CANTIDAD "+IngedientesArray.size());
        System.out.println("CANTIDAD TOTAL "+ AllIngredientesArray.size());
        managerIngredient.setIngredientsArray(AllIngredientesArray);
        managerIngredient.settiposIngredientsArray(IngedientesArray);
    }




}