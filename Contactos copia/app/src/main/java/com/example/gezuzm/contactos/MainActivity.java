package com.example.gezuzm.contactos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.CalendarView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import  java.util.Locale;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private EditText campoNombre;
    public CalendarView campofecha;
    private EditText campoTelefono;
    private EditText campoEmail;
    private EditText campoDescripcion;
    Contacto datosContacto;
    public String fecha_local;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campoNombre = (EditText) findViewById(R.id.campoNombre);
        campoTelefono = (EditText) findViewById(R.id.campoTelefono);
        campoEmail = (EditText) findViewById(R.id.campoEmail);
        campoDescripcion = (EditText) findViewById(R.id.campoDescripcion);
        campofecha = (CalendarView) findViewById(R.id.campofecha);

        // datos regresados del activy EDITAR
        Bundle parametros = getIntent().getExtras();
        String nombre = parametros.getString("NOMBRE");
        String telefono = parametros.getString("TELEFONO");
        String email = parametros.getString("EMAIL");
        String fechaNacimiento = parametros.getString("FECHA");
        String descripcion = parametros.getString("DESCRIPCION");

        // enviando el texto del parametro
        campoNombre.setText(nombre);
        campoTelefono.setText(telefono);
        campoEmail.setText(email);

        // configurar la fecha como cadena
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String stringFecha = sdf.format(new Date(campofecha.getDate()));

        //String format = "MM-dd-yyyy";
      //  SimpleDateFormat sdf2 = new SimpleDateFormat(format, Locale.US);
        //campofecha.setDate (sdf2.parse(String.valueOf(tvDateValue.getText())));
        /*
        try {
            campofecha.setDate (sdf2.parse(String.valueOf(stringFecha)));
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        //campofecha.setText(stringFecha);

        String[] dateArray = stringFecha.split("/");

        int year = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        int date = Integer.parseInt(dateArray[2]);

        GregorianCalendar gc = new GregorianCalendar(year,month,date);
        long timeStamp = gc.getTimeInMillis();//getTimeInMillies();

        campofecha.setDate(timeStamp);

        campoDescripcion.setText(descripcion);
    }

    public void continuar(View v)
    {
/*
        campofecha = (CalendarView) findViewById(R.id.campofecha);
        SimpleDateFormat ss = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(campofecha.getDate());
        String currentdate = ss.format(date);
*/

        try {
            String dateString = "30/09/2014";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(dateString);

            Toast.makeText(MainActivity.this, date.toString(), Toast.LENGTH_SHORT).show();

            long startDate = date.getTime();


            String[] dateArray = dateString.split("/");

            int year = Integer.parseInt(dateArray[0]);
            int month = Integer.parseInt(dateArray[1]);
            int dia = Integer.parseInt(dateArray[2]);

            GregorianCalendar gc = new GregorianCalendar(year,month,dia);
            long timeStamp = gc.getTimeInMillis();


        } catch (ParseException e) {
            e.printStackTrace();
        }



        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
        String stringFecha = sdf.format(new Date(campofecha.getDate()));



        datosContacto = new Contacto(campoNombre.getText().toString(),
                                        stringFecha,
                                        campoTelefono.getText().toString(),
                                        campoEmail.getText().toString(),
                                        campoDescripcion.getText().toString());


        Intent intent = new Intent(MainActivity.this, MostrarEditarContactos.class);
        // enviar parametros
        // 1.- manera local

                intent.putExtra("NOMBRE",datosContacto.getNombre());
                intent.putExtra("TELEFONO",datosContacto.getTelefono());
                intent.putExtra("EMAIL",datosContacto.getEmail());
        intent.putExtra("FECHA",datosContacto.getFechaNacimiento());
        intent.putExtra("DESCRIPCION",datosContacto.getDescripcion());


        startActivity(intent);

        // para ahorrar  memoria
        //finish();   // termina la actividad anterior

    }

}



