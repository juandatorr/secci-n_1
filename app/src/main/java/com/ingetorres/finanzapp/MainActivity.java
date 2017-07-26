package com.ingetorres.finanzapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String NOMBRE_USUARIO = "usuario";


    private EditText textNombre;
    private Button btnSiguiente;

    private String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = "";
        textNombre = (EditText) findViewById(R.id.editTextNombre);

        btnSiguiente = (Button) findViewById(R.id.buttonSiguiente);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                nombre = textNombre.getText().toString();
                if(nombre != null && !nombre.isEmpty()) {
                    Intent intentNombre = new Intent(MainActivity.this, SecondActivity.class);
                    intentNombre.putExtra(NOMBRE_USUARIO, nombre);
                    startActivity(intentNombre);
                }else{
                    Toast.makeText(MainActivity.this, "Por favor, escribe tu nombre" + nombre, Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Forzar la visualización de un icon en el action bar.
        getSupportActionBar().setLogo(R.mipmap.ic_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

}
