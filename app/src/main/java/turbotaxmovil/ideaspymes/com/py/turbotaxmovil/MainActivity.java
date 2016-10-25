package turbotaxmovil.ideaspymes.com.py.turbotaxmovil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private EditText value;
    private Button btn;
    private Firebase rootRef;
    private String userId = "@cromero";
    private String annio = "2016";
    private String periodo = "oct";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootRef = new Firebase("https://turbotaxmobile.firebaseio.com/"+userId);

        value = (EditText) findViewById(R.id.editTextValue);
        btn = (Button) findViewById(R.id.buttonEnviar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double total = Double.parseDouble(value.getText().toString());

                Firebase itemsRef = rootRef.child(annio);
                String gastoKey = UUID.randomUUID().toString();

                itemsRef.child(periodo)
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

            }
        });

    }
}
