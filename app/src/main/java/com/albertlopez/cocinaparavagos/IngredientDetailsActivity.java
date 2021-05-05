package com.albertlopez.cocinaparavagos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.albertlopez.cocinaparavagos.model.Ingredient;
import com.squareup.picasso.Picasso;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class IngredientDetailsActivity extends AppCompatActivity {

    private TextView tvIngrediente,tipoUnidad;
    private ImageView imagenIngrediente;
    private Button botonMas,botonMenos;
    private Ingredient ingredienteSeleccionado;
    private TextView numero;
    private int cantidad;

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

        loadingIngredients();

        tvIngrediente = findViewById(R.id.nombreIngrediente);
        tipoUnidad =  findViewById(R.id.tipoUnidad);
        imagenIngrediente = findViewById(R.id.imagenIngrediente);
        botonMas = findViewById(R.id.mas);
        botonMenos = findViewById(R.id.menos);
        numero = findViewById(R.id.dondeLosNumeros);

        String image = ingredienteSeleccionado.getImagen();
        tvIngrediente.setText(ingredienteSeleccionado.getNombreIngrediente());
        tipoUnidad.setText(ingredienteSeleccionado.getValorMedida());
        Picasso.with(this).load(image).into(imagenIngrediente);

        botonMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              sumar();
            }
        });

        botonMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restar();
            }
        });
    }

    private void restar() {
        cantidad--;
        if (cantidad >= 0) {
            numero.setText(String.valueOf(cantidad));
        } else {
            cantidad = 0;
        }
    }

    private void sumar() {
        cantidad++;
        numero.setText(String.valueOf(cantidad));
    }

    private void loadingIngredients() {
        ingredienteSeleccionado = (Ingredient) getIntent().getSerializableExtra("ingredienteSeleccionado");
    }
}