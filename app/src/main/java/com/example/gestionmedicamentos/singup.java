package com.example.gestionmedicamentos;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class singup extends AppCompatActivity {
    private EditText nombrecompleto, ndocumento;
    private EditText clave, cclave;
    public Spinner trol,tdocumento;
    ArrayList<String> listadocumentos;
    ArrayList<Tipodocumento> tipodocumentos;
    ArrayList<String>listarol;
    ArrayList<Rol> tiporoles;
    Conexion conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        conexion = new Conexion(getApplicationContext(), "gestion_medicamentos.db", null, 1);

        nombrecompleto=findViewById(R.id.nombrecompleto);
        ndocumento=findViewById(R.id.ndocumento);
        tdocumento = findViewById(R.id.tdocumento);
        trol=findViewById(R.id.trol);
        clave=findViewById(R.id.clave);
        cclave=findViewById(R.id.cclave);



        consultartipodocumentos();
        ArrayAdapter<String>adaptador=new ArrayAdapter(this, R.layout.tipodocumento_item, listadocumentos);
        tdocumento.setAdapter(adaptador);


        consultarrol();
        ArrayAdapter<CharSequence> adaptable=new ArrayAdapter(this,R.layout.tipo_rol_item,listarol);
        trol.setAdapter(adaptable);

    }




    public void registrar(View view){
        Conexion conexion = new Conexion(this, "Conexion", null, 1);
        SQLiteDatabase db=conexion.getWritableDatabase();
        String nombreApellido=nombrecompleto.getText().toString();
        String documentos=ndocumento.getText().toString();
        String contra=clave.getText().toString();
        String rcontra=cclave.getText().toString();
        String roles=trol.getSelectedItem().toString();
        String tdocumentos=tdocumento.getSelectedItem().toString();


    }

    private void consultartipodocumentos() {
        SQLiteDatabase db = null;
            db = conexion.getWritableDatabase();
            Tipodocumento tipodocumento;
            tipodocumentos = new ArrayList<>();
            Cursor cursor = db.rawQuery("SELECT * FROM tipodocumento", null);

            if (cursor.moveToFirst()) {
                do {
                    tipodocumento = new Tipodocumento();

                    tipodocumento.setDescripcion(cursor.getString(1));
                    tipodocumentos.add(tipodocumento);
                } while (cursor.moveToNext());
            }


            cursor.close();
            db.close();

            obtenerlista();
        }



    private void obtenerlista() {
        listadocumentos =new ArrayList<>();
        listadocumentos.add("seleccione");
        for(int i = 0; i< tipodocumentos.size(); i++){
            listadocumentos.add(tipodocumentos.get(i).getDescripcion());
        }

    }
    private void consultarrol() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        Rol rol;
        tiporoles = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM rol", null);

        if (cursor.moveToFirst()) {
            do {
                rol = new Rol();

                rol.setDescripcion(cursor.getString(1));
                tiporoles.add(rol);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        obtenerlistaRol();
    }

    private void obtenerlistaRol() {
        listarol =new ArrayList<String>();
        listarol.add("seleccione");
        for(int i = 0; i< tiporoles.size(); i++){
            listarol.add(tiporoles.get(i).getDescripcion());
        }
    }

}




