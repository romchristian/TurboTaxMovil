package turbotaxmovil.ideaspymes.com.py.turbotaxmovil;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static android.R.attr.key;

public class MainActivity extends AppCompatActivity {

    private EditText value;
    private Button btn;
    private Firebase rootRef;
    private String userId = "@cromero";
    private String annio = "2016";
    private String periodo = "oct";
    private ListView mensajes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootRef = new Firebase("https://turbotaxmobile.firebaseio.com/"+userId);

        value = (EditText) findViewById(R.id.editTextValue);
        btn = (Button) findViewById(R.id.buttonEnviar);
        mensajes = (ListView) findViewById(R.id.mensajes_list);


        //TelephonyManager tMgr = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        //String mPhoneNumber = tMgr.getLine1Number();

        final String key = "1";

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mensaje = value.getText().toString();

                Date fecha = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String key_mensaje = sdf.format(fecha);
                Firebase chatRef = rootRef.child("chat_" + key);
                chatRef.child("usuarioOrigen:")
                        .setValue(userId);
                chatRef.child("telOrigen:")
                        .setValue("0984497044");
                chatRef.child("usuarioDest:")
                        .setValue("@josecolman");
                chatRef.child("telDest:")
                        .setValue("0972787278");

                chatRef.child("mensaje_"+key_mensaje)
                        .child("userId")
                        .setValue(userId);

                chatRef.child("mensaje_"+key_mensaje)
                        .child("texto")
                        .setValue(mensaje);




             /*   itemsRef.child(periodo)
                        .child("gastos")
                        .child("gasto_"+gastoKey)
                        .child("impuesto")
                        .setValue("iva");

                itemsRef.child(periodo)
                        .child("gastos")
                        .child("gasto_"+gastoKey)
                        .child("tipo")
                        .setValue("factura");

                itemsRef.child(periodo)
                        .child("gastos")
                        .child("gasto_"+gastoKey)
                        .child("timbrado")
                        .setValue("12345678");

                itemsRef.child(periodo)
                        .child("gastos")
                        .child("gasto_"+gastoKey)
                        .child("nro")
                        .setValue("001-001-7894");

                itemsRef.child(periodo)
                        .child("gastos")
                        .child("gasto_"+gastoKey)
                        .child("ruc")
                        .setValue("3404671");

                itemsRef.child(periodo)
                        .child("gastos")
                        .child("gasto_"+gastoKey)
                        .child("total")
                        .setValue(total);

                itemsRef.child(periodo)
                        .child("gastos")
                        .child("gasto_"+gastoKey)
                        .child("iva")
                        .setValue(total / 11);
              */

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Query queryRef = rootRef.orderByChild("userId").equalTo("@cromero");
        FirebaseListAdapter<String> adapter = new FirebaseListAdapter<String>(
                this,
                String.class,
                android.R.layout.simple_list_item_1,
                queryRef
        ) {
            @Override
            protected void populateView(View view, String s, int i) {
                TextView textView = (TextView) findViewById(android.R.id.text1);
                textView.setText(s);
            }
        };

        mensajes.setAdapter(adapter);
    }
}
