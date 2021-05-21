package com.albertlopez.cocinaparavagos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.albertlopez.cocinaparavagos.bbdd.IngredientCreator;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class CreateIngredientesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button botonInsertar;
    String medida,name,tipo;
    EditText nombre;
    IngredientCreator ingredientCreator;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ingredientes);
        ocultarBarras();
        name = "";
        tipo = "";

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.tipoIngrediente, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.pesos, android.R.layout.simple_spinner_item);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spiner = findViewById(R.id.spinner);
        Spinner spiner2 = findViewById(R.id.spinner2);

        spiner.setTag("spinner");
        spiner2.setTag("spinner2");


        spiner2.setAdapter(adapter2);
        spiner.setAdapter(adapter);

        spiner.setOnItemSelectedListener(this);
        spiner2.setOnItemSelectedListener(this);

        botonInsertar = findViewById(R.id.insertarIngredienteCustom);
        nombre = findViewById(R.id.nombreIngredienteCustom);
        requestQueue = Volley.newRequestQueue(this);

        botonInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nombre.getText().toString().trim();
                ingredientCreator = new IngredientCreator();
                if (!checkOptionsRegister(name,tipo,medida)) {
                    return;
                } else {
                    insertarIngredientes();
                }
            }
        });

    }

    private void insertarIngredientes() {
        ingredientCreator.createIngredientCustom(name,medida,tipo,requestQueue,this);
    }

    private boolean checkOptionsRegister(String name, String tipo,String medida) {
        if (name.length() < 1) {
            Toast.makeText(CreateIngredientesActivity.this,
                    "El nombre no puede estar vacío ",Toast.LENGTH_SHORT)
                    .show();
            return false;
        } else if (tipo.length() != 0 && tipo.equals("Tipo")) {
            Toast.makeText(CreateIngredientesActivity.this,
                    "Selecciona un Tipo de Ingrediente",Toast.LENGTH_SHORT)
                    .show();
            return false;
        } else if (medida.length() != 0 && medida.equals("Masa")) {
            Toast.makeText(CreateIngredientesActivity.this,
                    "Selecciona un Tipo de Masa",Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        return true;
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner spiner = (Spinner)parent;
        Spinner spiner2 = (Spinner)parent;
        if(spiner.getId() == R.id.spinner)
        {
            medida = parent.getItemAtPosition(position).toString();

        }
        if(spiner2.getId() == R.id.spinner2)
        {
            tipo = parent.getItemAtPosition(position).toString();

        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void ingredienteRepetido() {
        Toast.makeText(CreateIngredientesActivity.this,
                "El Ingrediente que intentas insertar ya existe en la base de datos",Toast.LENGTH_SHORT)
                .show();
    }

    public void ingredienteInsertado() {
        Toast.makeText(CreateIngredientesActivity.this,
                "Ingrediente Insertado",Toast.LENGTH_SHORT)
                .show();
        nombre.setText("");
    }
}