package com.albertlopez.cocinaparavagos;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.albertlopez.cocinaparavagos.manager.ManagerAllRecipes;
import com.albertlopez.cocinaparavagos.manager.ManagerIngredients;
import com.albertlopez.cocinaparavagos.model.Ingredient;
import java.util.ArrayList;


public class IngredientsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewIngrediente;
    RecyclerViewIngredientesAdaptador adaptadorIngrediente;
    ManagerIngredients managerIngredient;
    ArrayList<Ingredient> IngredientesGuardadoPorELUsuario;
    Ingredient ingredienteSeleccionado;
    ArrayList<Ingredient> IngedientesArray;
    Toolbar toolbar;
    TextView botonRedondo;
    TextView textoIngredientes;

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

        botonRedondo = findViewById(R.id.botonIngredientes3);
        textoIngredientes = findViewById(R.id.textoIngredientesUsuario3);
        textoIngredientes.setVisibility(View.INVISIBLE);
        recyclerViewIngrediente = (RecyclerView)findViewById(R.id.recyclerIngredientes);
        recyclerViewIngrediente.setLayoutManager(new GridLayoutManager(this,2));

        loadingIngredients();

        adaptadorIngrediente = new RecyclerViewIngredientesAdaptador(this,IngedientesArray,0);

        adaptadorIngrediente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingredienteSeleccionado = IngedientesArray.get(recyclerViewIngrediente.getChildAdapterPosition(v));
                openIngredientsActivity(ingredienteSeleccionado);
            }
        });
        recyclerViewIngrediente.setAdapter(adaptadorIngrediente);

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



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onResume() {
        super.onResume();
        if (ManagerAllRecipes.getIngredientesIntroducidosPorELUsuario().size() == 0) {
            System.out.println("no tengo nada para ti");
        } else {
            textoIngredientes.setVisibility(View.VISIBLE);
            botonRedondo.setVisibility(View.VISIBLE);
            botonRedondo.setText(String.valueOf(ManagerAllRecipes.getIngredientesIntroducidosPorELUsuario().size()));
            System.out.println("tenemos alguna cosa");
            System.out.println(ManagerAllRecipes.getIngredientesIntroducidosPorELUsuario().size());
        }
    }
}