package com.ingetorres.finanzapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    private String mensaje;
    private String usuario;
    private int edad;
    private String tipo;

    private ImageButton botonMostrar;
    private Button botonCompartir;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        botonCompartir = (Button) findViewById(R.id.buttonCompartir);
        botonCompartir.setVisibility(View.INVISIBLE);

        botonCompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent implicito que comparte texto en las diferentes applicaciones de mensajeria.
                Intent intentImplicito = new Intent(Intent.ACTION_SEND);
                intentImplicito.putExtra(Intent.EXTRA_TEXT, mensaje);
                intentImplicito.setType("text/plain");
                startActivity(intentImplicito);

            }
        });



        botonMostrar = (ImageButton) findViewById(R.id.imageButtonMostrar);
        botonMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario = getIntent().getExtras().getString(MainActivity.NOMBRE_USUARIO).toString();
                edad = Integer.parseInt(getIntent().getExtras().getString(SecondActivity.EDAD).toString());
                tipo = getIntent().getExtras().getString(SecondActivity.OPCION).toString();

                crearMensaje(usuario, edad, tipo);
                Toast.makeText(ThirdActivity.this, mensaje, Toast.LENGTH_LONG).show();

                botonCompartir.setVisibility(View.VISIBLE);
            }
        });


    }

    public void crearMensaje(String nombreUsuario, int edad, String tipoMensaje){
        switch (tipoMensaje){
            case SecondActivity.SALUDO:
                mensaje = "Hola " + nombreUsuario + " ¿Como llevas esos " + edad  + " años ?.  #MyApp" ;
                break;
            case SecondActivity.DESPEDIDA:
                mensaje = "Espero verte pronto " + nombreUsuario + ", antes que cumplas " + (edad + 1) + " ...#MyApp";
                break;
            default:
                break;
        }
        //Forzar la visualización de un icon en el action bar.
        getSupportActionBar().setLogo(R.mipmap.ic_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
