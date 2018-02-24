//Falta por poner a funcionar boton aceptar para que mire info faltante y contraseñas concordantes. cambiar boton de fecha por icono...
//Falta funcion de escogencia de hobbies para imprimir, revisar boton aceptar
//ver como manejar arreglos
package com.edopore.edoforms;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Spinner opciones;
    private int año, mes, dia;
    private EditText mFecha;
    private Button bFecha;
    private static final int TIPO_DIALOGO = 0;
    private static DatePickerDialog.OnDateSetListener oyenteselectorfecha;
    EditText Us, Pa, Cp, Ce;
    CheckBox Ho1, Ho2, Ho3, Ho4;
    TextView T1, T2, T3, T4, T5, T6, T7, T8, T9, T0;
    int fog, foh;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instancias para corroborar datos
        Us = findViewById(R.id.eUser);
        Pa = findViewById(R.id.ePass);
        Cp = findViewById(R.id.eCPass);
        Ce = findViewById(R.id.eMail);

        Ho1 = findViewById(R.id.cLeer);
        Ho2 = findViewById(R.id.cSports);
        Ho3 = findViewById(R.id.cVideo);
        Ho4 = findViewById(R.id.cMusic);

        T1 = findViewById(R.id.tUser);
        T2 = findViewById(R.id.tPass);
        T3 = findViewById(R.id.tMail);
        T4 = findViewById(R.id.tFecha);
        T5 = findViewById(R.id.tCiudad);
        T6 = findViewById(R.id.tHobbies);
        T8 = findViewById(R.id.tHobbies2);
        T9 = findViewById(R.id.tHobbies3);
        T0 = findViewById(R.id.tHobbies4);
        T7 = findViewById(R.id.tGen);

        fog = 1;
        //Instancias para spinner
        opciones = findViewById(R.id.sCiudad);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Ciudades, android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);

        //obtener una instancia de los controles GUI dentro del layout para date picker
        mFecha = findViewById(R.id.eFecha);
        bFecha = findViewById(R.id.bFecha);
        Calendar calendario = Calendar.getInstance();
        año = calendario.get(Calendar.YEAR);
        mes = calendario.get(Calendar.MONTH);
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mostrarfecha();

        oyenteselectorfecha = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                año = year;
                mes = month;
                dia = dayOfMonth;
                mostrarfecha();
            }
        };
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 0:
                return new DatePickerDialog(this, oyenteselectorfecha, año, mes, dia);
        }
        return null;
    }

    public void mostrarcalendar(View control) {
        showDialog(TIPO_DIALOGO);

    }

    public void mostrarfecha() {
        mFecha.setText(dia + "/" + (mes + 1) + "/" + año);

    }

    public void onRadioButtonClicked(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.rMasculino:
                fog = 1;
                break;

            case R.id.rFemenino:
                fog = 2;
                break;
        }
    }

    public void onButtonClicked(View view) {
        showDialog(TIPO_DIALOGO);

    }

    public void onAceptarButton(View view) {
        if (view.isClickable()) {

            String pass, cpass, user, mail, fecha, city;
            pass = Pa.getText().toString();
            cpass = Cp.getText().toString();
            user = Us.getText().toString();
            mail = Ce.getText().toString();
            fecha = mFecha.getText().toString();
            city = opciones.getSelectedItem().toString();

            T1.setText("");
            T2.setText("");
            T3.setText("");
            T4.setText("");
            T5.setText("");
            T6.setText("");
            T7.setText("");
            T8.setText("");
            T9.setText("");
            T0.setText("");

            if (pass.equals(cpass) && !pass.isEmpty() && !cpass.isEmpty() && !user.isEmpty() && !cpass.isEmpty() && !(!Ho1.isChecked() && !Ho2.isChecked() && !Ho3.isChecked() && !Ho4.isChecked())) {
                Toast.makeText(this, R.string.datosful, Toast.LENGTH_SHORT).show();
                T1.setText(user);
                T2.setText(pass);
                T3.setText(mail);
                T4.setText(fecha);
                T5.setText(city);

                if (Ho1.isChecked()) {
                    T6.setText(Ho1.getText().toString());
                }
                if (Ho2.isChecked()) {
                    T8.setText(Ho2.getText().toString());
                }
                if (Ho3.isChecked()) {
                    T9.setText(Ho3.getText().toString());
                }
                if (Ho4.isChecked()) {
                    T0.setText(Ho4.getText().toString());
                }
                if (fog == 1) {
                    T7.setText(R.string.masculino);
                } else if (fog == 2) {
                    T7.setText(R.string.femenino);
                }
            } else {
                if (!pass.equals(cpass)) {
                    Toast.makeText(this, R.string.nopass, Toast.LENGTH_SHORT).show();
                }
                if (user.isEmpty()) {
                    Toast.makeText(this, R.string.nouser, Toast.LENGTH_SHORT).show();
                }
                if (pass.isEmpty()) {
                    Toast.makeText(this, R.string.nopass1, Toast.LENGTH_SHORT).show();
                }
                if (cpass.isEmpty()) {
                    Toast.makeText(this, R.string.nopass2, Toast.LENGTH_SHORT).show();
                }
                if (mail.isEmpty()) {
                    Toast.makeText(this, R.string.nomail, Toast.LENGTH_SHORT).show();
                }
                if (!Ho1.isChecked() && !Ho2.isChecked() && !Ho3.isChecked() && !Ho4.isChecked()) {
                    Toast.makeText(this, R.string.noho, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void isChecked(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.cLeer:
                foh = 1;
                break;
            case R.id.cVideo:
                foh = 2;
                break;
            case R.id.cSports:
                foh = 3;
                break;
            case R.id.cMusic:
                foh = 4;
                break;
        }
    }
}