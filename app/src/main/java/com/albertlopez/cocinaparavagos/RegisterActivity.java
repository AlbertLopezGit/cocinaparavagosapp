package com.albertlopez.cocinaparavagos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name,password,repeatPassword;
    Button btnRegistrar;

    RequestQueue requestQueue;

    private static final String Url1 ="http://80.32.104.188:8220/usuario.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnRegistrar.setOnClickListener(this);

        name = findViewById(R.id.editTextTextPersonName);
        password = findViewById(R.id.editTextTextPassword);
        repeatPassword = findViewById(R.id.editTextTextConfirmPassword);

        btnRegistrar = findViewById(R.id.buttonRegistrar);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.buttonRegistrar){
            String nombre = name.getText().toString().trim();
            String pass = password.getText().toString().trim();

            createUser(nombre,pass);
        }

    }

    private void createUser(String nombre, String pass) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                } ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name",nombre);
                params.put("password",pass);
                return super.getParams();
            }
        };
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}