package com.albertlopez.cocinaparavagos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.albertlopez.cocinaparavagos.manager.ManagerAllRecipes;
import com.albertlopez.cocinaparavagos.manager.ManagerIngredients;
import com.albertlopez.cocinaparavagos.model.Ingredient;
import java.util.ArrayList;

public class IngredientsBaseActivity extends AppCompatActivity{

    ManagerIngredients managerIngredient;
    ListView lista;
    AdaptadorIngredientesBase adapter;
    Button bottonIngredientesUsuario;

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
        setContentView(R.layout.activity_ingredients_base);
        loadingIngredients();

        lista = (ListView) findViewById(R.id.listIngredients);
        adapter = new AdaptadorIngredientesBase(managerIngredient.viewIngredientsBase(),this);
        lista.setAdapter(adapter);
        bottonIngredientesUsuario = findViewById(R.id.botonIngredientesDelUsuario);

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
        if (ManagerAllRecipes.getIngredientesIntroducidosPorELUsuario().size() == 0) {
            System.out.println("no tengo nada para ti");
        } else {
            bottonIngredientesUsuario.setVisibility(View.VISIBLE);
            bottonIngredientesUsuario.setText(String.valueOf(ManagerAllRecipes.getIngredientesIntroducidosPorELUsuario().size()));
            System.out.println("tenemos alguna cosa");
            System.out.println(ManagerAllRecipes.getIngredientesIntroducidosPorELUsuario().size());
        }
    }


}