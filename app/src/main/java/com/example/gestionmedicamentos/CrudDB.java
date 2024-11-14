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
        if (!documento.isEmpty() || !clave.isEmpty()) {
            Cursor fila = bd.rawQuery("SELECT p.nombrecompleto, p.documento, p.celular, p.clave, p.repeclave,r.descripcion AS rol_descripcion, genero.descripcion AS genero_descripcion FROM persona p INNER JOIN rol r ON persona.cod_rol = rol.cod_rol INNER JOIN genero g ON persona.cod_genero = g.cod_genero where documento=? and clave=?", new String[]{documento, clave});
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
    public boolean registrar(Context context,String nombreCompleto, String documento, String celular, String clave, String repeClave, int codRol, int codGenero) {

        Conexion conex = new Conexion(context, "conexion", null, 1);
        SQLiteDatabase bd = conex.getWritableDatabase();

        // Validaciones básicas
        if (nombreCompleto.isEmpty() || documento.isEmpty() || celular.isEmpty() || clave.isEmpty() || repeClave.isEmpty()) {
            Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validación de coincidencia de contraseñas
        if (!clave.equals(repeClave)) {
            Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Inserta los datos en la tabla si todas las validaciones pasan
        ContentValues values = new ContentValues();
        values.put("nombrecompleto", nombreCompleto);
        values.put("documento", documento);
        values.put("celular", celular);
        values.put("clave", clave);
        values.put("cod_rol", codRol);
        values.put("cod_genero", codGenero);

        long resultado = bd.insert("persona", null, values);
        bd.close(); // Cierra la base de datos después de la operación

        if (resultado == -1) {
            Toast.makeText(context, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

}
