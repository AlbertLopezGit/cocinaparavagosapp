package com.albertlopez.cocinaparavagos;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.albertlopez.cocinaparavagos.bbdd.UserCreator;
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
    UserCreator userCreator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        name = findViewById(R.id.editTextTextPersonName);
        password = findViewById(R.id.editTextTextPassword);
        repeatPassword = findViewById(R.id.editTextTextConfirmPassword);
        mail = findViewById(R.id.editTextTextEmail);

        btnRegistrar = findViewById(R.id.buttonRegistrar);
        btnRegistrar.setOnClickListener(this);

        btnRegistrar.setOnClickListener(this);
        requestQueue = Volley.newRequestQueue(this);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        userCreator = new UserCreator();

        if (id == R.id.buttonRegistrar){
            String nombre = name.getText().toString().trim();
            String pass = password.getText().toString().trim();
            String passRepeat = repeatPassword.getText().toString().trim();
            String email = mail.getText().toString().trim();

            //metodo para controlar todos los requisitos para registrar usuario
            if (!checkOptionsRegister(pass,passRepeat)) {
                return;}

            userCreator.createUser(nombre,pass,email,requestQueue,this);
        }
    }

    private boolean checkOptionsRegister(String pass, String passRepeat) {
        if (!pass.equals(passRepeat)) {
            Toast.makeText(RegisterActivity.this,
                "La contraseña y repetir contraseña no coinciden.",Toast.LENGTH_SHORT)
                .show();return false;
        } else if (pass.length() < 5) {
            Toast.makeText(RegisterActivity.this,
                    "La contraseña debe tener 5 caracteres como mínimo.",Toast.LENGTH_SHORT)
                    .show();return false;
        }

        return true;
    }

    public void msgErrorUsuarioDuplicado(String email){
        Toast.makeText(RegisterActivity.this,
                "Ese correo ya está en uso. Prueba con otro. "+email+"",Toast.LENGTH_SHORT)
                .show();
    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}