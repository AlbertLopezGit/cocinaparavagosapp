package com.albertlopez.cocinaparavagos.ingredients;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.albertlopez.cocinaparavagos.R;
import com.albertlopez.cocinaparavagos.UserValidation;
import com.albertlopez.cocinaparavagos.bbdd.Bbdd;
import com.albertlopez.cocinaparavagos.model.Ingredient;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class DeleteIngredientCustom extends AppCompatActivity {

    RecyclerViewIngredientesAdaptador adaptadorRecyclerViewIngredientes;
    RecyclerView recyclerViewBorrarIngredientes;
    ArrayList<Ingredient> arrayListCustomIngredients;
    Ingredient ingredienteSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_ingredient_custom);

        recyclerViewBorrarIngredientes = (RecyclerView)findViewById(R.id.reciclerViewBorrarIngredientes);
        recyclerViewBorrarIngredientes.setLayoutManager(new GridLayoutManager(this,1));
        ocultarBarras();
        loading();

        adaptadorRecyclerViewIngredientes = new RecyclerViewIngredientesAdaptador(this,arrayListCustomIngredients,4);
        recyclerViewBorrarIngredientes.setAdapter(adaptadorRecyclerViewIngredientes);

        adaptadorRecyclerViewIngredientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingredienteSeleccionado = arrayListCustomIngredients.get(recyclerViewBorrarIngredientes.getChildAdapterPosition(v));
                borrar(ingredienteSeleccionado);
                UserValidation.addUltimoIngredienteDelete(ingredienteSeleccionado.getNombreIngrediente());
                nosVamos();
            }
        });

    }

    private void borrar(Ingredient ingredienteSeleccionado) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(
                Request.Method.DELETE,
                Bbdd.ingredientesCustomDelete + "/" + ingredienteSeleccionado.getNombreIngrediente() + "/" + UserValidation.getUser().getIdUsuario(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(DeleteIngredientCustom.this,
                                "Ingrediente eliminado",Toast.LENGTH_SHORT)
                                .show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(request);
        arrayListCustomIngredients.remove(ingredienteSeleccionado);
    }

    private void loading() {
        ArrayList<String> ultimosIngredientesDelete = UserValidation.getIngredientesUltimosDelete();
        ArrayList<Ingredient> ingredientesParaEliminar = new ArrayList<>();
        arrayListCustomIngredients = (ArrayList<Ingredient>) getIntent().getSerializableExtra("ingredientesCustom");

        for (Ingredient i:arrayListCustomIngredients) {
            for (String x: ultimosIngredientesDelete) {
                if (x.equals(i.getNombreIngrediente())) {
                    ingredientesParaEliminar.add(i);
                }
            }
        }
        arrayListCustomIngredients.removeAll(ingredientesParaEliminar);
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
    private void nosVamos() {
        finish();
    }
}