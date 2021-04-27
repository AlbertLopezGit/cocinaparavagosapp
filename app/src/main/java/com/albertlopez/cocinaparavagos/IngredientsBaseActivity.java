package com.albertlopez.cocinaparavagos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.albertlopez.cocinaparavagos.manager.ManagerIngredients;
import com.albertlopez.cocinaparavagos.model.Ingredient;

import java.util.ArrayList;

public class IngredientsBaseActivity extends AppCompatActivity implements RecyclerViewIngredientesAdaptador.OnNoteListener {

    ManagerIngredients managerIngredient;
    ListView lista;
    ArrayList<Ingredient> IngedientesArray;
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


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Toast.makeText(getApplicationContext(),String.valueOf(position),Toast.LENGTH_SHORT).show(); //chuleta para ver la posicion de la lista
                ArrayList<Ingredient> tiposIngredientes = managerIngredient.returnIngredientsForTipeIngredients(position);
                openIngredientsActivity(tiposIngredientes);
            }
        });
    }

    private void loadingIngredients() {
        IngedientesArray = (ArrayList<Ingredient>) getIntent().getSerializableExtra("Ingredientes");
        managerIngredient.setIngredientsArray(IngedientesArray);
    }

    public void openIngredientsActivity(ArrayList<Ingredient> tiposIngredientes) {
        Intent intent = new Intent(this, IngredientsActivity.class);
        intent.putExtra("TiposIngredientes", tiposIngredientes);
        intent.putExtra("ingredientes",managerIngredient.getIngredientsArray());
        startActivity(intent);
    }


    @Override
    public void onNoteClick(int position) {

    }
}