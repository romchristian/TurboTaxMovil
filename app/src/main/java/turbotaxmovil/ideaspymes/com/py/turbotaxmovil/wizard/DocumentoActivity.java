package turbotaxmovil.ideaspymes.com.py.turbotaxmovil.wizard;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.R;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.DatabaseHelper;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.Impuesto;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.OrmLiteBaseAppCompatActivity;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.RegistroParam;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.servicios.RegistroWS;

import static turbotaxmovil.ideaspymes.com.py.turbotaxmovil.R.id.fecha;
import static turbotaxmovil.ideaspymes.com.py.turbotaxmovil.R.id.textView;

public class DocumentoActivity extends OrmLiteBaseAppCompatActivity<DatabaseHelper> {

    private EditText ruc, razonSocial, timbrado, codEst, puntoExp, numero, gravada10, gravada5,exenta;
    private TextView fecha, vencimiento;
    private Button buttonGuardar;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documento);
        auth = FirebaseAuth.getInstance();

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
                setDateInWidget(ctx,fecha);
            }
        });

        vencimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDateInWidget(ctx,vencimiento);
            }
        });

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar();
            }
        });
    }

    private void setDateInWidget(final Context ctx,final TextView textView) {
        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePicker = new DatePickerDialog(ctx, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String date = String.valueOf(year) +"-"+String.valueOf(monthOfYear)
                        +"-"+String.valueOf(dayOfMonth);
                textView.setText(date);
            }
        }, yy, mm, dd);
        datePicker.show();
    }


    public void guardar(){
        String useruuid = auth.getCurrentUser().getUid();
        RegistroParam r = new RegistroParam();
        r.setRegistroUUID(UUID.randomUUID().toString());
        r.setUserUUID(useruuid);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        r.setFechaRegistro(sdf.format(new Date()));
        r.setFechaDocumento(fecha.getText().toString());
        r.setVecimientoDocumento(vencimiento.getText().toString());

        Impuesto impuesto = null;
        try {
            Long impuestoId = Proceso.instance().impuestoId;
            android.util.Log.d("PROCESO", "impuestoId : "  + impuestoId);
            impuesto = getHelper().getImpuestoDao().queryForId(Proceso.instance().impuestoId.intValue());
        } catch (SQLException e) {
            new RuntimeException(e);
        }

        if(impuesto != null) {
            r.setTipoImpuesto(impuesto.getNombre());
            switch (impuesto.getNombre()){
                case "IRP":
                    r.setTipoPeriodo("ANUAL");
                    break;
                case "IVA":
                    r.setTipoPeriodo("MENSUAL");
                    break;
            }

        }


        r.setAnio(2017);
        r.setMes(5);

        r.setBienesGanaciales(false);
        r.setCantCuotas(0);

        r.setLibroId(Proceso.instance().libroId);
        r.setClasificacionUsuarioId(Proceso.instance().clasificacionId);
        r.setInversion(false);
        r.setGravada10(1100000d);
        r.setGravada05(0d);
        r.setExenta(0d);
        r.setImpuesto05(0d);
        r.setImpuesto10(100000d);
        r.setImpuetoTotal(100000d);
        r.setGasto(true);
        r.setMontoTotal(100000d);
        r.setMontoCobrado(100000d);
        r.setMontoGravado(100000d);
        r.setMontoNoGravado(0d);
        r.setNumero("001-001-456123");
        r.setBaseImponibleImportaciones(0d);

        r.setTipoDocumento("RUC");

        r.setRuc("3404671-2");
        r.setRucComprador("");
        r.setRazonSocial("Chistian Romero");

        getHelper().getRegistroParamDataDao().create(r);

        Log.d("REGISTRO", "1");
        List<RegistroParam> registros =getHelper()
                .getRegistroParamDataDao()
                .queryForEq("RegistroUUID",r.getRegistroUUID());

        Log.d("REGISTRO", "registros: " + registros);

        if(registros != null && !registros.isEmpty()) {

            RegistroParam r2 = registros.get(0);
            Log.d("REGISTRO", "registro: " + r2);
            RegistroWS.post(this,getHelper(),r2);
        }

        Proceso.destroy();
        finish();

    }
}
