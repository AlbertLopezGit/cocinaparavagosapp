package com.albertlopez.cocinaparavagos;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.albertlopez.cocinaparavagos.manager.ManagerUser;

public class LoginActivity extends AppCompatActivity {
    EditText email,password;
    Button register,login;
    ImageView capibara;
    SoundPool sp;
    int sound;
    ManagerUser managerUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        managerUser = new ManagerUser();
        password = findViewById(R.id.editTextTextPassword);
        email = findViewById(R.id.editTextMail);
        register = findViewById(R.id.btnRegister);
        capibara = findViewById(R.id.capibaraImg);
        login = findViewById(R.id.buttonLogin);
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC,1);
        sound = sp.load(this,R.raw.capibarasound, 1);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobarImputs();
            }
        });

        capibara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capibaraSound();
            }
        });

    }

    private void comprobarImputs() {
        String correo = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        //metodo para controlar todos los requisitos para registrar usuario
        if (!checkOptionsLogout(pass,correo)) { return;}
        managerUser.comprobarPassYdescargarUsuario(correo,pass);

    }

    private boolean checkOptionsLogout(String pass, String correo) {
        if (pass.length() <= 0) {
            Toast.makeText(LoginActivity.this,
                    "El Campo de la contraseña no puede estar vacio",Toast.LENGTH_SHORT)
                    .show();
            return false;
        } else if (email.length() <= 0) {
            Toast.makeText(LoginActivity.this,
                    "El Campo del correo no puede estar vacio",Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        return true;
    }

    public void openRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void capibaraSound() {
        sp.play(sound,1,1,1,0,0);
    }
}