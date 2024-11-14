package com.example.gestionmedicamentos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Conexion extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "gestion_medicamentos.db";
    private static final int DATABASE_VERSION = 1;

    public Conexion(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Persona(nombrecompleto String,apellidocompleto String,documento String primary key,celular int Not null,clave String,repeclave )");
        db.execSQL("create table rol(cod_rol INTEGER primary key,descripcion VARCHAR(12)not null)");
        db.execSQL("INSERT INTO rol (descripcion) VALUES " +
                "('Administrador')," +
                "('Médico')," +
                "('Enfermero')," +
                "('Paciente');");


        db.execSQL("create table genero(cod_genero INTEGER primary key,descripcion text not null)");
        db.execSQL("INSERT INTO genero(descripcion) VALUES" +
                "('Masculino')," +
                "('Femenino');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS persona;");
        db.execSQL("DROP TABLE IF EXISTS rol;");
        db.execSQL("DROP TABLE IF EXISTS tipodocumento;");
        db.execSQL("DROP TABLE IF EXISTS genero ");
        onCreate(db);


    }

}
