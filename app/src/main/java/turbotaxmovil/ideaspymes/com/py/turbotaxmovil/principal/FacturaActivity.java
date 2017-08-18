package turbotaxmovil.ideaspymes.com.py.turbotaxmovil.principal;


import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.R;


public class FacturaActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(value = R.id.toolbar)
    Toolbar toolbar;
    @BindView(value = R.id.fab)
    FloatingActionButton fab;

    //Inputs
    @BindView(value = R.id.ruc)
    EditText ruc;
    @BindView(value = R.id.timbrado)
    EditText timbrado;
    @BindView(value = R.id.numero)
    EditText numero;
    @BindView(value = R.id.monto)
    EditText monto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);
        ButterKnife.bind(this);
        configurarActionBar();
        fab.setOnClickListener(this);
    }

    private void configurarActionBar() {
        setSupportActionBar(toolbar);
        // Show menu icon
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                finalizar();
                break;
        }
    }

    private void finalizar() {

    }
}
