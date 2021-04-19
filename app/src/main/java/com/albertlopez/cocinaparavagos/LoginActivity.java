package com.albertlopez.cocinaparavagos;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class LoginActivity extends AppCompatActivity {

    Button register;
    Button login;
    ImageView capibara;
    SoundPool sp;
    int sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
                //DEBE IR A LA PAGINA DEL USUARIO EN CONCRETO, A SUS
            }
        });


        capibara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capibaraSound();
            }
        });

    }

    public void openRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void capibaraSound() {
        sp.play(sound,1,1,1,0,0);
    }




}