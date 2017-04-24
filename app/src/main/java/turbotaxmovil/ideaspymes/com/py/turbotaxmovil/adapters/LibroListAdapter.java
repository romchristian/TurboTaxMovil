package turbotaxmovil.ideaspymes.com.py.turbotaxmovil.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.R;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.Impuesto;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.Libro;

/**
 * Created by christian.romero on 21/04/2017.
 */

public class LibroListAdapter extends ArrayAdapter<Libro> {



    public LibroListAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public LibroListAdapter(@NonNull Context context, @LayoutRes int resource, List<Libro> lista) {
        super(context, resource,lista);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.itemlistrow, null);
        }

        Libro p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.textIndice);
            TextView tt2 = (TextView) v.findViewById(R.id.textNombre);


            if (tt1 != null) {
                tt1.setText(p.getIndex()+".");
            }

            if (tt2 != null) {
                tt2.setText(p.getNombre());
            }


        }

        return v;
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }
}
