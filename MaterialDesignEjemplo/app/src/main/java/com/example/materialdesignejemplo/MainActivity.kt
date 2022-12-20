package com.example.materialdesignejemplo

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import android.widget.AutoCompleteTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.chip.ChipGroup
import com.google.android.material.chip.Chip
import android.os.Bundle
import com.example.materialdesignejemplo.R
import android.widget.ArrayAdapter
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import android.content.DialogInterface.OnMultiChoiceClickListener
import android.content.DialogInterface
import android.view.*
import android.widget.Button
import com.google.android.material.appbar.MaterialToolbar
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var fab: FloatingActionButton
    lateinit var texto: TextInputEditText
    lateinit var textoDesplegable: AutoCompleteTextView
    lateinit  var cl: ConstraintLayout
    lateinit var botonChips: Button
    lateinit var botonInput: Button
    lateinit var chipGroup: ChipGroup
    lateinit  var perros: Chip
    lateinit  var gatos: Chip
    lateinit var peces: Chip
    lateinit var layoutDialogo: View
    lateinit var toolbar: MaterialToolbar
    var luchadores = ArrayList(Arrays.asList("John Cena", "The Rock", "Undertaker"))

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mimenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.b2 -> {
                Snackbar.make(
                    cl,
                    "Boton warning",
                    BaseTransientBottomBar.LENGTH_LONG
                ).show()
                true
            }
            R.id.b1 -> {
                Snackbar.make(
                    cl,
                    "Boton mail",
                    BaseTransientBottomBar.LENGTH_LONG
                ).show()
                true
            }
            R.id.app_bar_switch -> {
                Snackbar.make(
                    cl,
                    "El switch está " + item.isChecked,
                    BaseTransientBottomBar.LENGTH_LONG
                ).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.materialToolbar)
        setSupportActionBar(toolbar)
        fab = findViewById(R.id.fab)
        texto = findViewById(R.id.texto)
        cl = findViewById(R.id.cl)
        botonChips = findViewById(R.id.botonChips)
        botonInput = findViewById(R.id.botonInput)
        chipGroup = findViewById(R.id.grupoChip)
        perros = findViewById(R.id.perros)
        gatos = findViewById(R.id.gatos)
        peces = findViewById(R.id.peces)
        textoDesplegable = findViewById(R.id.luchadores)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, luchadores)
        textoDesplegable.setAdapter(adaptador)
        textoDesplegable.setSelection(0)

        /*
         * El AutoCompleteText implementa el evento setOnItemClickListener
         */
        textoDesplegable.setOnItemClickListener { parent, view, position, id ->
            if (position == 0) {
                Snackbar.make(
                    cl,
                    "MY TIME IS UP MY TIME IS NOW",
                    BaseTransientBottomBar.LENGTH_LONG
                ).show()
            } else if (position == 1) {
                Snackbar.make(cl, "DO YOU SMELL?", BaseTransientBottomBar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(cl, "GONGGGGGG", BaseTransientBottomBar.LENGTH_INDEFINITE).show()
            }
        }
        botonChips.setOnClickListener {
            val lista = arrayOf("Perros", "Gatos", "Peces")
            val seleccionados =
                booleanArrayOf(perros.isChecked(), gatos.isChecked(), peces.isChecked())
            val dialogo = MaterialAlertDialogBuilder(applicationContext)
            dialogo.setTitle("Ejemplo selección")
            /*
                     * Al método setMultipleChoiceItems tenemos que pasarle la lista de elementos, un array de booleanos
                     * indicando el estado de cada elemento (pulsado o no), y la función capturadora,
                     * la cual recibe el botón pulsado (which) y su estado (isChecked)
                     */
            dialogo.setMultiChoiceItems(lista, seleccionados) { dialog, which, isChecked ->
            if (which == 0)
                perros.setChecked(isChecked)
            else if (which == 1) gatos.setChecked(
                isChecked
            ) else peces.setChecked(isChecked)
        }
            dialogo.show()
        }

        fab.setOnClickListener { /*
                 * Esta es la implementación más sencilla de un dialogo.
                 * Title: Título del dialogo
                 * Icon: Icono que aparece junto al mismo
                 * Message: El mensaje textual del dialogo
                 * PossitiveButton y NegativeButton, las acciones que representa el dialogo (
                 * OK y CANCELAR, SEGUIR Y PARAR...). Ambos van junto a una función capturadora
                 * (OnClickListener) .
                 */
            val dialogo = MaterialAlertDialogBuilder(this@MainActivity)
            dialogo.setIcon(R.drawable.the_stone)
            dialogo.setTitle("Ejemplo de dialogo")
            dialogo.setMessage(
                """
    Do you smell what The Rock is cooking?
    I smell it
    Do you smell what The Rock is cooking?
    I smell it
    Come on, come on
    Do you smell what The Rock is cooking?
    """.trimIndent()
            )
            dialogo.setPositiveButton("OK") { dialog, which ->
                Snackbar.make(
                    cl,
                    "Hasta luego " + texto.getText().toString() + ", que vaya bien!",
                    Snackbar.LENGTH_SHORT
                ).setAction("Pulsame") { println("Me han pulsado!") }
                    .show()
            }
            dialogo.setNegativeButton("Cancel", null)
            dialogo.show()
        }

        botonInput.setOnClickListener{
            /*
                 * Esta implementación de un dialogo usa una interfaz propia (R.layout.dialogo)
                 * Para acceder a los componentes de dicha interfaz primero hay que inflarla.
                 */
            layoutDialogo = LayoutInflater.from(applicationContext)
                .inflate(R.layout.layoutdialogo, null, false)
            val dialogo = MaterialAlertDialogBuilder(applicationContext)
            dialogo.setTitle("Ejemplo de dialogo")
            dialogo.setView(layoutDialogo)
            dialogo.setPositiveButton("OK") { dialog, which ->
                val edad = layoutDialogo.findViewById<TextInputEditText>(R.id.edad)
                Snackbar.make(
                    cl,
                    "No sabía que tenías " + edad.text.toString() + " años!",
                    Snackbar.LENGTH_SHORT
                )
                    .show()
            }
            dialogo.setNegativeButton("Cancel", null)
            dialogo.show()
        }
    }
}