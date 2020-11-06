package com.p2.ejemplostartactivityforresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Actividad2 extends AppCompatActivity {
    TextView textViewNumbers;
    Button buttonAdd;
    Button buttonSubtract;
    int number1,number2;

    private View.OnClickListener funcionAñadir = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int result = number1 + number2;
            /* Creamos un intent, en el cual almacenamos la información */

            Intent resultIntent = new Intent();

            /* Metemos la info que queramos devolverle al padre*/
            resultIntent.putExtra("result", result);

            /* Ponemos el CODIGO al resultado, aquí RESULT_OK */
            setResult(RESULT_OK, resultIntent);

            /* Acabamos la actividad, y volvremos al padre*/
            finish();
        }
    };

    private View.OnClickListener funcionRestar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int result = number1 - number2;
            Intent resultIntent = new Intent();
            resultIntent.putExtra("result", result);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);
        setTitle("Activity 2");
        /** Obtenemos el intent que nos ha llamado */
        Intent intent = getIntent();

        /*Sacamos los enteros que nos pasado la actividad padre */
        number1 = intent.getIntExtra("number1", 0);
        number2 = intent.getIntExtra("number2", 0);

        textViewNumbers = findViewById(R.id.text_view_numbers);
        textViewNumbers.setText("Numbers: " + number1 + ", " + number2);
        Button buttonAdd = findViewById(R.id.button_add);
        Button buttonSubtract = findViewById(R.id.button_subtract);
        buttonAdd.setOnClickListener(funcionAñadir);
        buttonSubtract.setOnClickListener(funcionRestar);
    }

}