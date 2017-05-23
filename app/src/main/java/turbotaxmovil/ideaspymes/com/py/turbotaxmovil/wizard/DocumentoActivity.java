package turbotaxmovil.ideaspymes.com.py.turbotaxmovil.wizard;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.R;

import static turbotaxmovil.ideaspymes.com.py.turbotaxmovil.R.id.fecha;

public class DocumentoActivity extends AppCompatActivity {

    private EditText ruc, razonSocial, timbrado, codEst, puntoExp, numero, gravada10, gravada5,exenta;
    private TextView fecha, vencimiento;
    private Button buttonGuardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documento);

        bindViews();
        setupEvents();
    }



    private void bindViews() {
        ruc = (EditText) findViewById(R.id.ruc);
        razonSocial = (EditText) findViewById(R.id.razonSocial);
        fecha = (TextView) findViewById(R.id.fecha);
        vencimiento = (TextView) findViewById(R.id.vencimiento);
        timbrado = (EditText) findViewById(R.id.timbrado);
        codEst = (EditText) findViewById(R.id.codEsta);
        puntoExp = (EditText) findViewById(R.id.puntoExp);
        numero = (EditText) findViewById(R.id.numero);
        gravada10 = (EditText) findViewById(R.id.gravada10);
        gravada5 = (EditText) findViewById(R.id.gravada5);
        exenta = (EditText) findViewById(R.id.exenta);
        buttonGuardar = (Button) findViewById(R.id.buttonGuardar);
    }

    private void setupEvents() {
        final Context ctx = this;

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int yy = calendar.get(Calendar.YEAR);
                int mm = calendar.get(Calendar.MONTH);
                int dd = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePicker = new DatePickerDialog(ctx, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date = String.valueOf(year) +"-"+String.valueOf(monthOfYear)
                                +"-"+String.valueOf(dayOfMonth);
                        fecha.setText(date);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });


        vencimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int yy = calendar.get(Calendar.YEAR);
                int mm = calendar.get(Calendar.MONTH);
                int dd = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePicker = new DatePickerDialog(ctx, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date = String.valueOf(year) +"-"+String.valueOf(monthOfYear)
                                +"-"+String.valueOf(dayOfMonth);
                        vencimiento.setText(date);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });
    }
}
