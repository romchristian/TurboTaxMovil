package turbotaxmovil.ideaspymes.com.py.turbotaxmovil;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.ActualizadorService;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.SendDataService;

import static android.R.attr.key;
import static android.speech.RecognizerIntent.ACTION_RECOGNIZE_SPEECH;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Firebase rootRef;
    private Button buttonLogout;
    private ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        btnSpeak =(ImageButton) findViewById(R.id.btnSpeak);


        rootRef = new Firebase("https://turbotaxmobile.firebaseio.com/users/" + auth.getCurrentUser().getUid());
        Log.d("MAIN", auth.getCurrentUser().getProviderId());


        btnSpeak.setOnClickListener(this);
        buttonLogout.setOnClickListener(this);

       /* value = (EditText) findViewById(R.id.editTextValue);
        btn = (Button) findViewById(R.id.buttonEnviar);
        mensajes = (ListView) findViewById(R.id.mensajes_list);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double total = 0d;
                try {
                    total = Double.parseDouble(value.getText().toString());
                } catch (Exception e) {

                }

                if(total > 0){
                    guardaDatos(total);
                }else{
                    Toast.makeText(MainActivity.this,"El valor debe ser un numero o mayor a 0",Toast.LENGTH_LONG).show();
                }



            }
        });
        */

    }



    @Override
    protected void onStart() {
        super.onStart();



       /* Query queryRef = rootRef
                .child("gastos")
                .orderByChild("timbrado");

        FirebaseListAdapter<Gasto> adapter = new FirebaseListAdapter<Gasto>(
                this,
                Gasto.class,
                android.R.layout.simple_list_item_1,
                queryRef
        ) {
            @Override
            protected void populateView(View view, Gasto g, int i) {
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setText(g.getTipoDocumento()+": " + g.getTotal());

            }
        };

        mensajes.setAdapter(adapter);
        */

    }



    public void guardaDatos(Double total){
        Date fecha = new Date();

        SimpleDateFormat sdfAnio = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdfMes = new SimpleDateFormat("MM");
        SimpleDateFormat sdfFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String key_anio = sdfAnio.format(fecha);
        String key_mes = sdfMes.format(fecha);
        String key_gastos = sdfFecha.format(fecha);


        rootRef.child("gastos")
                .child("gasto_" + key_gastos)
                .child("tipoImpuesto")
                .setValue("IRP");

        rootRef.child("gastos")
                .child("gasto_" + key_gastos)
                .child("tipoDocumento")
                .setValue("FACTURA");


        rootRef.child("gastos")
                .child("gasto_" + key_gastos)
                .child("timbrado")
                .setValue("12345678");


        rootRef.child("gastos")
                .child("gasto_" + key_gastos)
                .child("nro")
                .setValue("001-001-7894");

        rootRef.child("gastos")
                .child("gasto_" + key_gastos)
                .child("ruc")
                .setValue("3404671");

        rootRef.child("gastos")
                .child("gasto_" + key_gastos)
                .child("total")
                .setValue(total);

        rootRef.child("gastos")
                .child("gasto_" + key_gastos)
                .child("iva")
                .setValue(total / 11);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSpeak:
             promptSpeechInput();
            break;
            case R.id.buttonLogout:
                logout();
                break;
        }
    }


    private void promptSpeechInput() {
        Intent intent = new Intent(ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Di el numero de menu");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),"Metodo de voz no soportado",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    Intent intent = new Intent(this,ClasificacionActivity.class);

                    int libroIndex = 0;

                    try {
                        libroIndex = Integer.parseInt(result.get(0));
                    }catch (Exception ex){

                    }
                    if(libroIndex > 0) {
                        intent.putExtra("libroIndex", libroIndex);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(),result.get(0)+" no es una opción!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, SendDataService.class));
    }
}
