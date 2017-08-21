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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.R;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.adapters.ImpuestoListAdapter;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.adapters.LibroListAdapter;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.DatabaseHelper;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.Impuesto;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.Libro;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.OrmLiteBaseAppCompatActivity;

import static turbotaxmovil.ideaspymes.com.py.turbotaxmovil.R.id.lista;

public class LibroActivity extends OrmLiteBaseAppCompatActivity<DatabaseHelper> {

    private LibroListAdapter adapter;
    private ListView listView;
    private Long impuestoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            impuestoId = extras.getLong("IMPUESTO_ID");
            //The key argument here must match that used in the other activity
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        listView = (ListView) findViewById(R.id.libroListView);
        final Context context = this;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i = new Intent(context,ClasificacionUsuarioActivity.class);

                Long libroId = adapterView.getAdapter().getItemId(position);
                i.putExtra("LIBRO_ID", libroId);
                Proceso.instance().libroId = libroId;
                startActivity(i);
            }
        });

        cargaLista();
    }

    private void cargaLista() {
        if(impuestoId != null) {
            List<Libro> lista = getHelper().getLibroDataDao().queryForEq("impuestoId", impuestoId);
            adapter = new LibroListAdapter(this, R.layout.itemlistrow, lista);
            listView.setAdapter(adapter);
            Toast.makeText(this, "Libros: " + lista.size(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}
