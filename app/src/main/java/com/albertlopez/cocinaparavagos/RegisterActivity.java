package com.albertlopez.cocinaparavagos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name,password,repeatPassword,mail;
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
        mail = findViewById(R.id.editTextTextEmail);

        btnRegistrar = findViewById(R.id.buttonRegistrar);

        requestQueue = Volley.newRequestQueue(this);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.buttonRegistrar){
            String nombre = name.getText().toString().trim();
            String pass = password.getText().toString().trim();
            String email = mail.getText().toString().trim();

            createUser(nombre,pass,email);
        }

    }

    private void createUser(final String nombre,final String pass,final String email) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(RegisterActivity.this,"Correcto",Toast.LENGTH_SHORT).show();
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
                params.put("NICKNAME",nombre);
                params.put("PASS",pass);
                params.put("CORREOELECTRONICO",email);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }




    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}