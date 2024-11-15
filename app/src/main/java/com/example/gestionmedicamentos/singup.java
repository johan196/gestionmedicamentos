package com.example.gestionmedicamentos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class singup extends AppCompatActivity {

    private EditText nombreyapellido, ndocumento, celular, clave, cclave;
    private Spinner trol, tdocumento, tgenero;
    private Button guardar;
    Conexion conexion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        nombreyapellido = findViewById(R.id.nombreyapellido);
        ndocumento = findViewById(R.id.ndocumento);
        celular = findViewById(R.id.celular);
        clave = findViewById(R.id.clave);
        cclave = findViewById(R.id.cclave);
        trol = findViewById(R.id.trol);
        tdocumento = findViewById(R.id.tdocumento);
        tgenero = findViewById(R.id.tgenero);
        guardar = findViewById(R.id.guardar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar();
            }
        });

    }

    public void registrar() {
        Conexion conexion = new Conexion(this, "Conexion", null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        String nombreApellidos = nombreyapellido.getText().toString();
        String documentos = ndocumento.getText().toString();
        String celulars = celular.getText().toString();
        String contra = clave.getText().toString();
        String rcontra = cclave.getText().toString();

        if(!nombreApellidos.isEmpty() || !documentos.isEmpty() || !celulars.isEmpty() || !contra.isEmpty() ) {
            Cursor cursor = db.rawQuery("SELECT * FROM persona where documento = ? ", new String[]{documentos});
            if (cursor.getCount() > 0) {
                Toast.makeText(this, "la pérsona ya se encuentra registrado", Toast.LENGTH_SHORT).show();
        }else{
                ContentValues registro = new ContentValues();
                registro.put("nombrccompleto", nombreApellidos);
                registro.put("documento", documentos);
                registro.put("celular", celulars);
                registro.put("clave", contra);
                registro.put("repclave", rcontra);

                db.insert("persona", null, registro);
                db.close();
                Toast.makeText(this, "el usuario se registro exitosamente ", Toast.LENGTH_SHORT).show();

            }
        }else{
           validarCampos();
        }

    }
    private void validarCampos() {
        boolean devolver = false;
        String nombreApellidos = nombreyapellido.getText().toString();
        String documentos = ndocumento.getText().toString();
        String celulars = celular.getText().toString();
        String contra = clave.getText().toString();
        String rcontra = cclave.getText().toString();


        if (nombreApellidos.isEmpty()) {
            nombreyapellido.setError("Porfavor ingresa tu nombre completo");
        }
        if (documentos.isEmpty()) {
           ndocumento.setError("porfavor ingresa tu numero de documento ");
        }
        if (celulars.isEmpty()) {
            celular.setError("porfavor ingresa tu numero celular ");
        }
        if (contra.isEmpty()) {
             clave.setError ("porfavor ingresa una clave ");
        }
        if (contra!=rcontra) {
            cclave.setError("Las claves no coinciden");
        }else{
            guardar.setEnabled(true);
        }
        if(celulars.length()<=9 && celulars.length()>=11){
            celular.setError("el numero de celular debe tener tener 10 digitos");
        }else{
            celular.setError("Exitosoooo");
        }
        if(documentos.equals(documentos)){
            ndocumento.setError( "la persona ya se encuentra registrado");

        }
        if(documentos.length()>=6 && documentos.length()<=11){
            ndocumento.setError("la Cedula debe contener entre 7 y 10 digitos");
    }
    }

    public void regresar(){
        Intent i = new Intent(this,credencial.class);
        startActivity(i);
    }

}
