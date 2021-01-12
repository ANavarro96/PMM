package com.sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLPatatasHelper extends SQLiteOpenHelper {

    String crearPatatas = "CREATE TABLE Patatas (id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " nombre VARCHAR(20),precio INTEGER)";

    String crearSabor = "CREATE TABLE Sabor (id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " nombre TEXT)";

    String crearPatatasYSabor = "CREATE TABLE PatataSabor (idPatata INTEGER," +
            " idSabor INTEGER,FOREIGN KEY (idPatata) REFERENCES Patatas (id), " +
            "FOREIGN KEY (idSabor) REFERENCES Sabor (id) ," +
            " PRIMARY KEY (idPatata,idSabor))";

    public SQLPatatasHelper(Context contexto, String nombre,
                            SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior,
                          int versionNueva) {

        /*
         * Aquí dependería de los cambios que tenga la nueva BBDD: Cambiar alguna columna y ejecutar
         * algún alter, eliminar una tabla y sustituirla por otra...
         * Habrá que usar condicionales para ver que hacer según la versión de la BBDD, yo simplemente
         * voy a borrar y crear la BBDD y ya está
         */
        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS PatataSabor");
        db.execSQL("DROP TABLE IF EXISTS Patatas");
        db.execSQL("DROP TABLE IF EXISTS Sabor");

        //Se crearía la nueva versión de la tabla
        db.execSQL(crearPatatas);
        db.execSQL(crearSabor);
        db.execSQL(crearPatatasYSabor);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la BBDD
        db.execSQL(crearPatatas);
        db.execSQL(crearSabor);
        db.execSQL(crearPatatasYSabor);
    }
}