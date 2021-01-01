package com.sqllite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static SQLPatatasHelper sqlhelper;
    static SQLiteDatabase db;

    Button ver,borrar,guardar,modificar,buscar;
    EditText nombre,precio;
    TextView lista;

    private View.OnClickListener guardarPatatas = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String patatas = nombre.getText().toString();
            double precioPatatas = Double.parseDouble(precio.getText().toString());

            ContentValues nuevoRegistro = new ContentValues();
            nuevoRegistro.put("nombre", patatas);
            nuevoRegistro.put("precio",precioPatatas);

            //Insertamos el registro en la base de datos
            db.insert("Patatas", null, nuevoRegistro);
        }
    };

    private View.OnClickListener verTodasPatatas = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Cursor c = db.query("Patatas", // Tabla a la que se accede (FROM)
                    null, // Columnas que se quieren recuperar.
                    // Si ponemos null recogemos todas (*), y podemos poner ,max(),min(),count()...
                    null, // Filtros y condiciones en el WHERE
                    null, // Los parámetros que se aplican en el WHERE (?)
                    null, // Las columnas por las que se hace group By
                    null, // La cláusula HAVING
                    null); // Las columnas por las que se ordenan 

            //Nos aseguramos de que existe al menos un registro
            lista.setText("");
            if (c.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                while(c.moveToNext()) {
                    String codigo= c.getString(0);
                    String nombre = c.getString(1);
                    Double precop = c.getDouble(c.getColumnIndex("precio"));
                    lista.setText(lista.getText() + codigo + " " + nombre + " " + precop + "\n");
                }
            }
            c.close();
        }
    };

    private View.OnClickListener buscarPatatas = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String patatas = nombre.getText().toString();
            Cursor c = db.query("Patatas", // Tabla a la que se accede (FROM)
                    null, // Columnas que se quieren recuperar. Si ponemos null recogemos todas (*)
                    "nombre=?", // Filtros y condiciones en el WHERE
                    new String[]{patatas}, // Los parámetros que se aplican en el WHERE (?)
                    null, // Las columnas por las que se hace group By
                    null, // La cláusula HAVING
                    "id DESC"); // Las columnas por las que se ordenan

            //Nos aseguramos de que existe al menos un registro
            lista.setText("");
            if (c.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                while(c.moveToNext()) {
                    String codigo= c.getString(0);
                    String nombre = c.getString(1);
                    Double precop = c.getDouble(c.getColumnIndex("precio"));
                    lista.setText(lista.getText() + codigo + " " + nombre + " " + precop + "\n");
                }
            }
            c.close();
        }
    };

    private View.OnClickListener modificarPatatas = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String patatas = nombre.getText().toString();
            double precioPatatas = Double.parseDouble(precio.getText().toString());

            ContentValues nuevoRegistro = new ContentValues();
            nuevoRegistro.put("nombre", patatas);
            nuevoRegistro.put("precio",precioPatatas);

            //Actualizamos el registro en la base de datos, modifico el segundo siempre
            db.update("Patatas", nuevoRegistro, "id=2", null);
            // Si en el where pusieramos null, se modificarían todos los registros.
        }
    };

    private View.OnClickListener borrarPatatas = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String patatas = nombre.getText().toString();


            //Borro el registro (o los registros) que tengan el mismo nombre
            // db.delete("Patatas", "nombre='" + patatas + "'", null);
            db.delete("Patatas", "nombre=?", new String[]{patatas});
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ver = findViewById(R.id.ver);
        borrar = findViewById(R.id.borrar);
        guardar = findViewById(R.id.guardar);
        modificar = findViewById(R.id.modificar);
        buscar = findViewById(R.id.buscar);
        nombre = findViewById(R.id.nombre);
        precio = findViewById(R.id.precio);
        lista = findViewById(R.id.lista);

        guardar.setOnClickListener(guardarPatatas);
        ver.setOnClickListener(verTodasPatatas);
        modificar.setOnClickListener(modificarPatatas);
        borrar.setOnClickListener(borrarPatatas);
        buscar.setOnClickListener(buscarPatatas);


        //Abrimos la base de datos, en modo escritura
         sqlhelper = new SQLPatatasHelper(this, "DBPatatas", null, 1);
        System.out.println(sqlhelper.getDatabaseName());
        db = sqlhelper.getWritableDatabase();

        DialogoCrear dialogo = new DialogoCrear();
        dialogo.show(getSupportFragmentManager(),"Dialogo de crear");
    }

    @Override
    protected void onDestroy() {
        sqlhelper.close();
        super.onDestroy();
    }

    /* Esta función recoge el evento de pulsar el botón si del diálogo creado aquí abajo */
    public static DialogInterface.OnClickListener funcionSi = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

            if(db != null)
            {
                //usarExecSQL(db);

                //Cerrríamos la base de datos
                //db.close();
            }
        }
    };

    public static void usarExecSQL(SQLiteDatabase db){
        //Inserto datos de ejemplo
        db.execSQL("INSERT INTO Patatas (nombre,precio) " +
                "VALUES ('" + "Lays" + "', " + 1.5 +")");

        db.execSQL("INSERT INTO Patatas (nombre,precio) " +
                "VALUES ('" + "Pringles" + "', " + 2 +")");

        db.execSQL("INSERT INTO Sabor (nombre) " +
                "VALUES ('" + "Sal" + "')");

        db.execSQL("INSERT INTO Sabor (nombre) " +
                "VALUES ('" + "Campesinas" + "')");

        db.execSQL("INSERT INTO PatataSabor (idPatata,idSabor) " +
                "VALUES (" + "1" + "," + "2" + ")");

        db.execSQL("INSERT INTO PatataSabor (idPatata,idSabor) " +
                "VALUES (" + "2" + "," + "1" + ")");

    }

    public static class DialogoCrear extends AppCompatDialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Importante")
                    .setMessage("¿Quieres crear datos de prueba? ")
                    .setPositiveButton("Sí!", funcionSi);
            builder.setNegativeButton("No!",null);
            return builder.create();
        }
    }
}

