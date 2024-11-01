package com.example.gestionmedicamentos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class CrudDB {

    private Object context;

    public Usuario entrar(Context context, String documento, String clave) {
        Conexion conex = new Conexion(context, "conexion", null, 1);

        SQLiteDatabase bd = conex.getWritableDatabase();
        if (!documento.isEmpty() && clave.isEmpty()) {
            Cursor fila = bd.rawQuery("Select p.nombrecompleto,p.documento,r.cod_rol,t.tiporol from persona join rol on p.cod_rol =r.cod_rol where documento = ? and clave = ?", new String[]{documento, clave});
            if (fila.moveToFirst()) {
                String nombrecomp = fila.getString(0);
                String document = fila.getString(1);
                String clav = fila.getString(4);
                String rol = fila.getString(0);
                Usuario usuario = new Usuario();
                bd.close();
                return usuario;
            } else {
                Toast.makeText(context, "el documento con el que quiere ingresar no esta registrado", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "los campos son invalidos  ", Toast.LENGTH_SHORT).show();
            bd.close();


        }
        return null;
    }

}
