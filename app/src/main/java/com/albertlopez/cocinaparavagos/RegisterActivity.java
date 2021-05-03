package com.albertlopez.cocinaparavagos;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.albertlopez.cocinaparavagos.bbdd.UserCreator;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name;
    EditText password;
    EditText repeatPassword;
    EditText mail;
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
            if (!checkOptionsRegister(pass,passRepeat,nombre,email)) {
                return;}

            try {
                userCreator.createUser(nombre,pass,email,requestQueue,this);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }

    //metodos que comprueban que los datos son validos
    private boolean checkOptionsRegister(String pass, String passRepeat,String nombre,String email) {
        if (nombre.length() < 1) {
            Toast.makeText(RegisterActivity.this,
                    "El nombre no puede estar vacío ",Toast.LENGTH_SHORT)
                    .show();
            return false;
        } else if (!checkEmailCorrect(email)) {
            Toast.makeText(RegisterActivity.this,
                    "El email ingresado es inválido.",Toast.LENGTH_SHORT)
                    .show();
            return false;
        } else if (!pass.equals(passRepeat)) {
            Toast.makeText(RegisterActivity.this,
                "La contraseña y repetir contraseña no coinciden.",Toast.LENGTH_SHORT)
                .show();
            return false;
        } else if (pass.length() < 5) {
            Toast.makeText(RegisterActivity.this,
                    "La contraseña debe tener 5 caracteres como mínimo.",Toast.LENGTH_SHORT)
                    .show();
            return false;
        }

        return true;
    }

    public void msgErrorUsuarioDuplicado(String email){
        Toast.makeText(RegisterActivity.this,
                "Ese correo ya está en uso. Prueba con otro. "+email+"",Toast.LENGTH_SHORT)
                .show();
    }

    public void exit(){
        Toast.makeText(RegisterActivity.this,
                "Registro Completado ",Toast.LENGTH_SHORT)
                .show();
        finish();
    }

    //metodo para comprobar el que correo introducido tiene cierta logica
    private boolean checkEmailCorrect(String email) {
        Pattern pattern = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+");
        Matcher mather = pattern.matcher(email);
        return mather.find();
    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}