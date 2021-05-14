package com.albertlopez.cocinaparavagos;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.albertlopez.cocinaparavagos.manager.ManagerAllRecipes;
import com.albertlopez.cocinaparavagos.model.Ingredient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class IngredientDetailsActivity extends AppCompatActivity {

    TextView tvIngrediente,tipoUnidad;
    ImageView imagenIngrediente;
    Button botonMas,botonMenos,insertar;
    Ingredient ingredienteSeleccionado;
    TextView numeros;
    int cantidad = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_details);

        //Para esconder la barra superior
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        tvIngrediente = findViewById(R.id.nombreIngrediente);
        tipoUnidad =  findViewById(R.id.tipoUnidad);
        imagenIngrediente = findViewById(R.id.imagenIngrediente);
        botonMas = findViewById(R.id.mas);
        botonMenos = findViewById(R.id.menos);
        insertar = findViewById(R.id.insertar);
        numeros = findViewById(R.id.loDelosNumeros);
        numeros.setText("0");

        loadingIngredients();

        String image = ingredienteSeleccionado.getImagen();
        tvIngrediente.setText(ingredienteSeleccionado.getNombreIngrediente());
        tipoUnidad.setText(ingredienteSeleccionado.getValorMedida());
        Picasso.with(this).load(image).into(imagenIngrediente);

        ;

        botonMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parsearCantidadYNumeros();
                sumar();
            }
        });

        botonMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parsearCantidadYNumeros();
                restar();
            }
        });

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parsearCantidadYNumeros();
                insertar();
            }
        });
    }

    private void parsearCantidadYNumeros() {
        cantidad = Integer.parseInt(numeros.getText().toString());
    }

    private void insertar() {
        if(cantidad <= 0) {
            Toast.makeText(IngredientDetailsActivity.this,
                    "No tienes nada que insertar ",Toast.LENGTH_SHORT)
                    .show();
        } else {
            this.ingredienteSeleccionado.setCantidad(cantidad);
            ManagerAllRecipes.ingredientesIntroducidosPorELUsuario.add(this.ingredienteSeleccionado);
            Toast.makeText(IngredientDetailsActivity.this,
                    ingredienteSeleccionado.getCantidad() +" "+ ingredienteSeleccionado.getValorMedida() + " Introducidos",Toast.LENGTH_SHORT)
                    .show();
            finish();
        }
    }

    private void restar() {
        cantidad--;
        if (cantidad >= 0) {
            numeros.setText(String.valueOf(cantidad));
        } else {
            cantidad = 0;
        }

        ingredienteSeleccionado.setCantidad(cantidad);
    }

    private void sumar() {
        cantidad++;
        numeros.setText(String.valueOf(cantidad));
        ingredienteSeleccionado.setCantidad(cantidad);
    }

    private void loadingIngredients() {
        ingredienteSeleccionado = (Ingredient) getIntent().getSerializableExtra("ingredienteSeleccionado");
        numeros.setText(String.valueOf(ingredienteSeleccionado.getCantidad()));
    }

}