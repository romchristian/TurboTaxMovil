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
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.adapters.ClasificacionUsuarioListAdapter;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.adapters.LibroListAdapter;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.ClasificacionUsuario;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.DatabaseHelper;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.Libro;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.OrmLiteBaseAppCompatActivity;

public class ClasificacionUsuarioActivity extends OrmLiteBaseAppCompatActivity<DatabaseHelper> {

    private ClasificacionUsuarioListAdapter adapter;
    private ListView listView;
    private Long libroId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clasificacion_usuario);
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

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            libroId = extras.getLong("LIBRO_ID");
            //The key argument here must match that used in the other activity
        }

        listView = (ListView) findViewById(R.id.clasificacionListView);
        final Context context = this;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                /*Intent i = new Intent(context,LibroActivity.class);
                i.putExtra("IMPUESTO_ID", adapterView.getAdapter().getItemId(position));
                startActivity(i);*/
            }
        });

        cargaLista();
    }

    private void cargaLista() {
        if(libroId != null) {
            List<ClasificacionUsuario> lista = getHelper().getClasificacionUsuarioDataDao().queryForEq("libroId", libroId);
            adapter = new ClasificacionUsuarioListAdapter(this, R.layout.itemlistrow, lista);
            listView.setAdapter(adapter);
        }
    }
}
