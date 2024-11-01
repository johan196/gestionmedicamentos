
package com.example.gestionmedicamentos;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class credencial extends AppCompatActivity {

    private EditText documento;
    private EditText clave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credencial);

        documento = findViewById(R.id.ndocumento);
        clave = findViewById(R.id.clave);
    }

    public void entrar(View view) {
        String document = documento.getText().toString();
        String clav = clave.getText().toString();
        CrudDB crudDB = new CrudDB();
        if (!document.isEmpty() && !clav.isEmpty()) {

            Usuario usuario = crudDB.entrar(this, document, clav);
            if (usuario != null) {
                Intent i;
                switch (usuario.getRol()) {
                    case "Médico":
                        Intent iMedico = new Intent(this, pantallamedico.class);
                        iMedico.putExtra("keydocumento", usuario.nombrecompleto);
                        iMedico.putExtra("keydocumento", usuario.documento);
                        iMedico.putExtra("keydocumento", usuario.rol);
                        startActivity(iMedico);
                        break;
                    case "Enfermero":

                        Intent iEnfermero = new Intent(this, pantallaEnfermero.class);
                        iEnfermero.putExtra("keydocumento", usuario.nombrecompleto);
                        iEnfermero.putExtra("keydocumento", usuario.documento);
                        iEnfermero.putExtra("keydocumento", usuario.rol);
                        startActivity(iEnfermero);
                        break;
                    case "Paciente":
                        Intent iPaciente = new Intent(this, pantallaPaciente.class);
                        iPaciente.putExtra("keynombrecompleto", usuario.nombrecompleto);
                        iPaciente.putExtra("keydocumento", usuario.documento);
                        iPaciente.putExtra("keydocumento", usuario.rol);
                        startActivity(iPaciente);
                        break;
                    default:

                        return;
                }


            } else {
                Toast.makeText(this, "Documento o clave incorrectos", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Por favor, complete ambos campos", Toast.LENGTH_SHORT).show();
        }

    }

    public void registrar(View view) {
        Intent i = new Intent(this, singup.class);
        startActivity(i);

    }

    @Override
    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == event.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desea salir de la aplicacion?").setPositiveButton("confirmar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            builder.show();
        }
        return super.onKeyDown(keycode, event);
    }

}