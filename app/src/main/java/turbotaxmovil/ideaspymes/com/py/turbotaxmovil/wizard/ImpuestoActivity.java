package turbotaxmovil.ideaspymes.com.py.turbotaxmovil.wizard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.j256.ormlite.logger.Log;

import java.util.List;

import butterknife.OnItemSelected;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.R;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.adapters.ImpuestoListAdapter;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.DatabaseHelper;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.Impuesto;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.OrmLiteBaseAppCompatActivity;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.SendDataService;

public class ImpuestoActivity extends OrmLiteBaseAppCompatActivity<DatabaseHelper> {


    private ImpuestoListAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impuesto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        listView = (ListView) findViewById(R.id.impuestoListView);
        final Context context = this;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i = new Intent(context,LibroActivity.class);
                Long impuestoId = adapterView.getAdapter().getItemId(position);
                i.putExtra("IMPUESTO_ID", impuestoId);
                android.util.Log.d("PROCESO", "impuestoId : "  + impuestoId);
                Proceso.instance().impuestoId = impuestoId;
                startActivity(i);
            }
        });

       cargaLista();

    }

    private void cargaLista() {
        List<Impuesto> lista = getHelper().getImpuestoDataDao().queryForAll();
        adapter = new ImpuestoListAdapter(this, R.layout.itemlistrow, lista);
        listView.setAdapter(adapter);
        Toast.makeText(this,"Impuestos: " + lista.size(),Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        startService(new Intent(this, SendDataService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, SendDataService.class));
    }
}
