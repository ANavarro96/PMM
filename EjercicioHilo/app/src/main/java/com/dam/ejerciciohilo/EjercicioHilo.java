package com.dam.ejerciciohilo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class EjercicioHilo extends AppCompatActivity {
    private ImageView mIView;
    private Button botonCarga,otroBoton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIView = findViewById(R.id.imageView);
        botonCarga = findViewById(R.id.botonCarga);
        otroBoton = findViewById(R.id.otroBoton);

        botonCarga.setOnClickListener(funcionCargar);
        otroBoton.setOnClickListener(funcionOtroBoton);
    }

    private final View.OnClickListener funcionCargar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                // Fingimos que dura como unos 5 segundos.
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mIView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.mario));
        }
    };

    private final View.OnClickListener funcionOtroBoton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(), "No seas pesado, estoy trabajando",
                    Toast.LENGTH_SHORT).show();
        }
    };
}