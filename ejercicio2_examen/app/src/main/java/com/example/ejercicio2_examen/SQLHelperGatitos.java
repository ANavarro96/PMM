package com.example.ejercicio2_examen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLHelperGatitos extends SQLiteOpenHelper {

    String crearGatitos = "CREATE TABLE Gatitos (id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " nombre VARCHAR(20),edad INTEGER, raza VARCHAR(20))";

    public SQLHelperGatitos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(crearGatitos);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}


