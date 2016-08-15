package com.example.gezuzm.contactos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class MostrarEditarContactos extends AppCompatActivity {

    // referencias de la actividad
    private TextView tvNombre;
    private TextView tvFechaNacimiento;
    private TextView tvTelefono;
    private TextView tvEmail;
    private TextView tvDescripcion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos_editar_mostrar);

        // RECIBIR LOS PARAMETROS , llamados : "extras" en android
        Bundle parametros = getIntent().getExtras();

        /*
        String nombre = parametros.getString(getResources().getString(R.string.pnombre));
        String telefono = parametros.getString(getResources().getString(R.string.ptelefono));
        String email = parametros.getString(getResources().getString(R.string.pemail));
        String fechaNacimiento = parametros.getString(getResources().getString(R.string.pfechaNacimiento));
        String descripcion = parametros.getString(getResources().getString(R.string.pdescripcion));
*/

        String nombre = parametros.getString("NOMBRE");
        String telefono = parametros.getString("TELEFONO");
        String email = parametros.getString("EMAIL");
        String fechaNacimiento = parametros.getString("FECHA");
        String descripcion = parametros.getString("DESCRIPCION");


        // mostrarlos datos
        // declarando memoria para controles
        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvTelefono = (TextView) findViewById(R.id.tvTelefono);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvFechaNacimiento = (TextView) findViewById(R.id.tvFechaNacimiento);
        tvDescripcion = (TextView) findViewById(R.id.tvDescripcion);

        // enviando el texto del parametro
        tvNombre.setText(nombre);
        tvTelefono.setText(telefono);
        tvEmail.setText(email);
        tvFechaNacimiento.setText(fechaNacimiento);
        tvDescripcion.setText(descripcion);

    }


    public void regresar(View v)
    {
        Contacto datosContacto = new Contacto(tvNombre.getText().toString(),
                "",
                tvTelefono.getText().toString(),
                tvEmail.getText().toString(),
                tvDescripcion.getText().toString());

        // crea otra vez la instacia del a actividad anterior
        Intent intent = new Intent(MostrarEditarContactos.this, MainActivity.class);


        intent.putExtra("NOMBRE",datosContacto.getNombre());
        intent.putExtra("TELEFONO",datosContacto.getTelefono());
        intent.putExtra("EMAIL",datosContacto.getEmail());
        intent.putExtra("FECHA",datosContacto.getFechaNacimiento());
        intent.putExtra("DESCRIPCION",datosContacto.getDescripcion());


        startActivity(intent);
    }

    // al presionar la tecla de BACK
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            // crea otra vez la instacia del a actividad anterior
            Intent intent = new Intent(MostrarEditarContactos.this, MainActivity.class);
            startActivity(intent);
        }

        return super.onKeyDown(keyCode, event);
    }

}
