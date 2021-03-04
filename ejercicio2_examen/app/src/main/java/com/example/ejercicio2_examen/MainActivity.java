package com.example.ejercicio2_examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    static SQLHelperGatitos sqlhelper;
    static SQLiteDatabase db;
    Button crear, lt, all, gt;
    EditText nombre, edad, edadBuscar;
    Spinner razaSpinner;
    TextView tw;




    View.OnClickListener funcionCrear = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String nombreGato;
            String raza;
            int edadGato;
         nombreGato= nombre.getText().toString();
         raza= (String) razaSpinner.getSelectedItem();
         edadGato = Integer.parseInt(edad.getText().toString());

         ContentValues registro = new ContentValues();

            registro.put("nombre",nombreGato);
            registro.put("edad",edadGato);
            registro.put("raza",raza);

            db.insert("Gatitos", null, registro);
        }
    };

    View.OnClickListener buscarTodo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Cursor c = db.query("Gatitos",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
        );
            tw.setText("");
            String raza,nombre;
            int edad;
            if (c.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                nombre = c.getString(1);
                edad= c.getInt(2);
                raza = c.getString(3);

                tw.setText(tw.getText() + nombre + " " + edad + " " + raza + "\n");
                while(c.moveToNext()) {
                    nombre = c.getString(1);
                    edad= c.getInt(2);
                    raza = c.getString(3);

                    tw.setText(tw.getText() + nombre + " " + edad + " " + raza + "\n");
                }


            }
            c.close();
        }
    };

    View.OnClickListener buscarMenor = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Cursor c = db.query("Gatitos",
                    null,
                    " edad < " + edadBuscar.getText().toString(),
                    null,
                    null,
                    null,
                    null
            );
            tw.setText("");
            String raza,nombre;
            int edad;
            if (c.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                nombre = c.getString(1);
                edad= c.getInt(2);
                raza = c.getString(3);

                tw.setText(tw.getText() + nombre + " " + edad + " " + raza + "\n");
                while(c.moveToNext()) {
                    nombre = c.getString(1);
                    edad= c.getInt(2);
                    raza = c.getString(3);

                    tw.setText(tw.getText() + nombre + " " + edad + " " + raza + "\n");
                }


            }
            c.close();
        }
    };

    View.OnClickListener buscarMayor = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Cursor c = db.query("Gatitos",
                    null,
                    " edad > " + edadBuscar.getText().toString(),
                    null,
                    null,
                    null,
                    null
            );
            tw.setText("");
            String raza,nombre;
            int edad;
            if (c.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                nombre = c.getString(1);
                edad= c.getInt(2);
                raza = c.getString(3);

                tw.setText(tw.getText() + nombre + " " + edad + " " + raza + "\n");
                while(c.moveToNext()) {
                    nombre = c.getString(1);
                    edad= c.getInt(2);
                    raza = c.getString(3);

                    tw.setText(tw.getText() + nombre + " " + edad + " " + raza + "\n");
                }


            }
            c.close();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqlhelper=new SQLHelperGatitos(getApplicationContext(),"gatos",null,1);
        db = sqlhelper.getWritableDatabase();
        crear=findViewById(R.id.button);
        lt=findViewById(R.id.button2);
        all=findViewById(R.id.button4);
        gt=findViewById(R.id.button3);
        nombre=findViewById(R.id.edittex1);
        edad=findViewById(R.id.editext2);
        edadBuscar=findViewById(R.id.editTextTextPersonName3);
        razaSpinner=findViewById(R.id.spinner);
        tw=findViewById(R.id.textView);


        lt.setOnClickListener(buscarMenor);
        gt.setOnClickListener(buscarMayor);
        crear.setOnClickListener(funcionCrear);
        all.setOnClickListener(buscarTodo);


        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, Arrays.asList("noruego","siames","persa"));
        razaSpinner.setAdapter(adapter);
    }
}