package turbotaxmovil.ideaspymes.com.py.turbotaxmovil.wizard;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.R;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.custom.NumberTextWatcher;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.DatabaseHelper;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.Impuesto;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.OrmLiteBaseAppCompatActivity;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.RegistroParam;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.servicios.RegistroWS;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.servicios.RucWS;

import static turbotaxmovil.ideaspymes.com.py.turbotaxmovil.R.id.codEsta;
import static turbotaxmovil.ideaspymes.com.py.turbotaxmovil.R.id.fecha;
import static turbotaxmovil.ideaspymes.com.py.turbotaxmovil.R.id.ruc;
import static turbotaxmovil.ideaspymes.com.py.turbotaxmovil.R.id.textView;

public class DocumentoActivity extends OrmLiteBaseAppCompatActivity<DatabaseHelper> {

    private EditText ruc, razonSocial, timbrado, codEst, puntoExp, numero, gravada10, gravada5,exenta;
    private EditText fecha, vencimiento;
    private ImageView imgfecha, imgvencimiento;
    private Button buttonGuardar;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documento);
        auth = FirebaseAuth.getInstance();

        bindViews();
        setupEvents();
        setValoresPorDefecto();
    }

    private void setValoresPorDefecto() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        fecha.setText(sdf.format(new Date()));
        vencimiento.setText(sdf.format(new Date()));
    }


    private void bindViews() {
        ruc = (EditText) findViewById(R.id.ruc);
        razonSocial = (EditText) findViewById(R.id.razonSocial);
        fecha = (EditText) findViewById(R.id.fecha);
        fecha.setInputType(InputType.TYPE_NULL);
        vencimiento = (EditText) findViewById(R.id.vencimiento);
        vencimiento.setInputType(InputType.TYPE_NULL);
        imgfecha  = (ImageView) findViewById(R.id.img_fecha);
        imgvencimiento  = (ImageView) findViewById(R.id.img_vencimiento);

        timbrado = (EditText) findViewById(R.id.timbrado);
                codEst = (EditText) findViewById(R.id.codEsta);
        puntoExp = (EditText) findViewById(R.id.puntoExp);
        numero = (EditText) findViewById(R.id.numero);
        gravada10 = (EditText) findViewById(R.id.gravada10);
        gravada10.addTextChangedListener(new NumberTextWatcher(gravada10));
        gravada5 = (EditText) findViewById(R.id.gravada5);
        gravada5.addTextChangedListener(new NumberTextWatcher(gravada5));
        exenta = (EditText) findViewById(R.id.exenta);
        exenta.addTextChangedListener(new NumberTextWatcher(exenta));
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

        imgfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDateInWidget(ctx,fecha);
            }
        });

        imgvencimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDateInWidget(ctx,vencimiento);
            }
        });

        codEst.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    String rellenarConCeros = String.format("%03d", getNumberInt(codEst.getText()));
                    codEst.setText(rellenarConCeros);
                }

            }
        });

        puntoExp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    String rellenarConCeros = String.format("%03d", getNumberInt(puntoExp.getText()));
                    puntoExp.setText(rellenarConCeros);
                }

            }
        });


        final Context context = this;
        final DatabaseHelper helper = getHelper();
        ruc.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    if(ruc.getText() != null && ruc.getText().length() > 0){
                        RucWS.get(context,helper,razonSocial,ruc.getText().toString());
                    }
                }

            }
        });


        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar();
            }
        });
    }

    private void setDateInWidget(final Context ctx,final EditText textView) {
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
        String fechaDoc =  fecha.getText().toString();
        r.setFechaDocumento(fechaDoc);
        r.setVecimientoDocumento(vencimiento.getText().toString());

        if(fechaDoc != null){
            String[] afecha = fechaDoc.split("-");
            r.setAnio(Integer.parseInt(afecha[0]));
            r.setMes(Integer.parseInt(afecha[1]));
        }


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

        r.setBienesGanaciales(false);
        r.setCantCuotas(0);

        r.setLibroId(Proceso.instance().libroId);
        r.setClasificacionUsuarioId(Proceso.instance().clasificacionId);
        r.setInversion(false);

        r.setGravada10(getNumber(gravada10.getText()));
        r.setGravada05(getNumber(gravada5.getText()));
        r.setExenta(getNumber(exenta.getText()));

        r.setImpuesto05(new BigDecimal(r.getGravada05()/21).setScale(0,BigDecimal.ROUND_HALF_EVEN).doubleValue());
        r.setImpuesto10(new BigDecimal(r.getGravada10()/11).setScale(0,BigDecimal.ROUND_HALF_EVEN).doubleValue());
        r.setImpuetoTotal(r.getImpuesto05()+r.getImpuesto10());
        r.setGasto(true);
        r.setMontoTotal(r.getExenta()+r.getGravada05()+r.getGravada10());
        r.setMontoCobrado(r.getExenta()+r.getGravada05()+r.getGravada10());
        r.setMontoGravado(r.getGravada05()+r.getGravada10());
        r.setMontoNoGravado(r.getExenta());

        r.setNumero(codEst.getText().toString()+"-"+ puntoExp.getText().toString()+"-"+numero.getText().toString());
        r.setBaseImponibleImportaciones(0d);
        r.setTipoDocumento("RUC");

        r.setRuc(ruc.getText().toString());
        r.setRucComprador("");
        r.setRazonSocial(razonSocial.getText().toString());
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

    public Double getNumber(Editable e){
        if(e != null && e.toString().length() > 0){
            try{
                String snumero = e.toString().replace(".","");
                Log.d("PROCESO", "snumero: " + snumero);
                return Double.parseDouble(snumero);
            }catch (NumberFormatException ex){
                Log.e("PROCESO", "getNumber() -- no es un numero: " + ex.getMessage());
                return 0d;
            }
        }else{
            return 0d;
        }
    }

    public int getNumberInt(Editable e){
        if(e != null && e.toString().length() > 0){
            try{
                String snumero = e.toString().replace(".","");
                Log.d("PROCESO", "snumero: " + snumero);
                return Integer.parseInt(snumero);
            }catch (NumberFormatException ex){
                Log.e("PROCESO", "getNumber() -- no es un numero: " + ex.getMessage());
                return 0;
            }
        }else{
            return 0;
        }
    }
}
