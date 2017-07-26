package com.ingetorres.finanzapp;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    public static final String OPCION = "opcion";
    public static final String EDAD = "edad";

    public static final String SALUDO = "saludo";
    public static final String DESPEDIDA = "despedida";

    public static final int MAX = 60;
    public static final int MIN = 16;



    private RadioGroup opciones;
    private SeekBar seekBarEdad;
    private Button btnSiguiente;
    private TextView textViewPro;

    private String edad;
    private String nombreUsuario;
    private String opcion;

    private int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        nombreUsuario = getIntent().getExtras().getString(MainActivity.NOMBRE_USUARIO).toString();

        textViewPro = (TextView) findViewById(R.id.textViewProgreso);
        textViewPro.setText("0");
        opciones = (RadioGroup) findViewById(R.id.radioGroupOpciones);
        opciones.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.radioButtonSaludo:
                        opcion = SALUDO;
                        break;
                    case R.id.radioButtonDespedida:
                        opcion = DESPEDIDA;
                        break;
                    default:
                        break;
                }
            }
        });

        seekBarEdad = (SeekBar) findViewById(R.id.seekBarEdad);

        seekBarEdad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                age = i;
                textViewPro.setText(age + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                age = seekBar.getProgress();
                textViewPro.setText(age + "");
                if (age > MIN && age < MAX) {
                    btnSiguiente.setVisibility(View.VISIBLE);
                } else {
                    btnSiguiente.setVisibility(View.INVISIBLE);
                    Toast.makeText(SecondActivity.this, "Debe seleccionar una edad entre a 16 y 60 años", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnSiguiente = (Button) findViewById(R.id.buttonTercero);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edad = textViewPro.getText().toString();
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra(MainActivity.NOMBRE_USUARIO, nombreUsuario);
                intent.putExtra(OPCION, opcion);
                intent.putExtra(EDAD, edad);
                startActivity(intent);
            }
        });

        //Forzar la visualización de un icon en el action bar.
        getSupportActionBar().setLogo(R.mipmap.ic_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


}
