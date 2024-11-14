package com.example.gestionmedicamentos;

import android.content.ContentValues;
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



    }






    public void registrar(View view) {
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
                Toast.makeText(this, "el celular ya se encuentra registrado", Toast.LENGTH_SHORT).show();
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
    private boolean validarCampos() {
        String nombreApellidos = nombreyapellido.getText().toString().trim();
        String documentos = ndocumento.getText().toString().trim();
        String celulars = celular.getText().toString().trim();
        String contra = clave.getText().toString().trim();
        String rcontra = cclave.getText().toString().trim();


        if (nombreApellidos.isEmpty()) {
            nombreyapellido.setError("Por favor ingresa tu nombre completo");
            return false;
        }
        if (documentos.isEmpty()) {
            ndocumento.setError("Por favor ingresa tu número de documento");
            return false;
        }
        if (celulars.isEmpty()) {
            celular.setError("Por favor ingresa un número celular");
            return false;
        }
        if (contra.isEmpty()) {
            clave.setError("Por favor ingresa una clave");
            return false;
        }
        if (!contra.equals(rcontra)) {
            cclave.setError("Las claves no coinciden");
            return false;
        }

        return true;
    }

}
