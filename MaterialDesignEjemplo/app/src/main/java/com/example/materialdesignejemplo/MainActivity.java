package com.example.materialdesignejemplo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
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
    AutoCompleteTextView textoDesplegable;
    ConstraintLayout cl;
    Button botonChips,botonInput;
    ChipGroup chipGroup;
    Chip perros,gatos,peces;
    View layoutDialogo;
    ArrayList<String> luchadores = new ArrayList<>(Arrays.asList("John Cena","The Rock","Undertaker"));



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

        textoDesplegable = findViewById(R.id.luchadores);
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,luchadores);
        textoDesplegable.setAdapter(adaptador);
        textoDesplegable.setSelection(0);

        /*
         * El AutoCompleteText implementa el evento setOnItemClickListener
         */
        textoDesplegable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Snackbar.make(cl,"MY TIME IS UP MY TIME IS NOW", BaseTransientBottomBar.LENGTH_LONG).show();
                }
                else if(position == 1){
                    Snackbar.make(cl,"DO YOU SMELL?", BaseTransientBottomBar.LENGTH_SHORT).show();
                }
                else {
                    Snackbar.make(cl,"GONGGGGGG", BaseTransientBottomBar.LENGTH_INDEFINITE).show();
                }
            }
        });

        botonChips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] lista = new String[]{"Perros","Gatos","Peces"};
                boolean[] seleccionados = new boolean[]{perros.isChecked(),gatos.isChecked(),peces.isChecked()};


                MaterialAlertDialogBuilder dialogo = new MaterialAlertDialogBuilder(MainActivity.this);
                dialogo.setTitle("Ejemplo selección");
                /*
                 * Al método setMultipleChoiceItems tenemos que pasarle la lista de elementos, un array de booleanos
                 * indicando el estado de cada elemento (pulsado o no), y la función capturadora,
                 * la cual recibe el botón pulsado (which) y su estado (isChecked)
                 */
                dialogo.setMultiChoiceItems(lista, seleccionados, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(which == 0)
                            perros.setChecked(isChecked);
                        else if (which == 1)
                            gatos.setChecked(isChecked);
                        else
                            peces.setChecked(isChecked);
                    }
                });
                dialogo.show();

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 * Esta es la implementación más sencilla de un dialogo.
                 * Title: Título del dialogo
                 * Icon: Icono que aparece junto al mismo
                 * Message: El mensaje textual del dialogo
                 * PossitiveButton y NegativeButton, las acciones que representa el dialogo (
                 * OK y CANCELAR, SEGUIR Y PARAR...). Ambos van junto a una función capturadora
                 * (OnClickListener) .
                 */
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
                /*
                 * Esta implementación de un dialogo usa una interfaz propia (R.layout.dialogo)
                 * Para acceder a los componentes de dicha interfaz primero hay que inflarla.
                 */
                layoutDialogo = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.layoutdialogo, null, false);
                MaterialAlertDialogBuilder dialogo = new MaterialAlertDialogBuilder(MainActivity.this);
                dialogo.setTitle("Ejemplo de dialogo");
                dialogo.setView(layoutDialogo);
                dialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextInputEditText edad = layoutDialogo.findViewById(R.id.edad);
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