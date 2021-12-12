package com.example.materialdesignejemplo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.list.DialogMultiChoiceExtKt;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function3;

public class MainActivity extends AppCompatActivity {


    FloatingActionButton fab;
    TextInputEditText texto;
    ConstraintLayout cl;
    Button botonChips,botonInput;
    ChipGroup chipGroup;
    Chip perros,gatos,peces;
    View layoutDialogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        texto = findViewById(R.id.texto);
        cl = findViewById(R.id.cl);
        botonChips = findViewById(R.id.botonChips);
        botonInput = findViewById(R.id.botonInput);
        chipGroup = findViewById(R.id.grupoChip);
        perros = findViewById(R.id.perros);
        gatos = findViewById(R.id.gatos);
        peces = findViewById(R.id.peces);


        botonChips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] lista = new String[]{"Perros","Gatos","Peces"};
                boolean[] seleccionados = new boolean[]{perros.isChecked(),gatos.isChecked(),peces.isChecked()};


                MaterialAlertDialogBuilder dialogo = new MaterialAlertDialogBuilder(MainActivity.this);
                dialogo.setTitle("Ejemplo selección");
                dialogo.setMultiChoiceItems(lista, seleccionados, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(which == 0){
                            perros.setChecked(isChecked);

                        }
                        else if (which == 1)
                            gatos.setChecked(isChecked);
                        else
                            peces.setChecked(isChecked);
                        System.out.println(which + "");
                    }
                });
                dialogo.show();

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                MaterialAlertDialogBuilder dialogo = new MaterialAlertDialogBuilder(MainActivity.this);
                dialogo.setIcon(R.drawable.the_stone);
                dialogo.setTitle("Ejemplo de dialogo");
                dialogo.setMessage("Do you smell what The Rock is cooking?\n" +
                        "I smell it\n" +
                        "Do you smell what The Rock is cooking?\n" +
                        "I smell it\n" +
                        "Come on, come on\n" +
                        "Do you smell what The Rock is cooking?");
                dialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Snackbar.make(cl,"Hasta luego " +  texto.getText().toString()+", que vaya bien!", Snackbar.LENGTH_SHORT).
                                setAction("Pulsame", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        System.out.println("Me han pulsado!");
                                    }
                                })
                                .show();
                    }
            });
                dialogo.setNegativeButton("Cancel", null);
                dialogo.show();
            }
        });

        botonInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutDialogo = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.layoutdialogo, null, false);
                MaterialAlertDialogBuilder dialogo = new MaterialAlertDialogBuilder(MainActivity.this);
                dialogo.setTitle("Ejemplo de dialogo");
                dialogo.setView(layoutDialogo);
                dialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        TextInputEditText edad = layoutDialogo.findViewById(R.id.edad);
                        System.out.println(edad.getText().toString() + "dddds");
                        TextView texto = layoutDialogo.findViewById(R.id.textView);
                        Snackbar.make(cl,"No sabía que tenías " + edad.getText().toString() + " años!", Snackbar.LENGTH_SHORT)
                                .show();
                    }
                });
                dialogo.setNegativeButton("Cancel", null);
                dialogo.show();
            }
        });
    }
}