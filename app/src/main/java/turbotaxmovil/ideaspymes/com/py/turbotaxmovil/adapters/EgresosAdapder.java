package turbotaxmovil.ideaspymes.com.py.turbotaxmovil.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.R;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.DatabaseHelper;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.Libro;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.RegistroParam;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.utils.Contants;

/**
 * Created by christian.romero on 01/09/2017.
 */

public class EgresosAdapder extends RecyclerView.Adapter<EgresosAdapder.ViewHolder> {

    private List<RegistroParam> mDataset;
    private DatabaseHelper helper;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextViewSimbolo;
        public TextView mTextViewDescripcion;
        public TextView mTextViewFecha;
        public TextView mTextViewImpuesto;
        public TextView mTextViewMonto;

        public ViewHolder(View v) {
            super(v);

            mTextViewSimbolo = (TextView) v.findViewById(R.id.simbolo);
            mTextViewDescripcion = (TextView) v.findViewById(R.id.descripcion);
            mTextViewFecha = (TextView) v.findViewById(R.id.fecha);
            mTextViewImpuesto = (TextView) v.findViewById(R.id.impuesto);
            mTextViewMonto = (TextView) v.findViewById(R.id.monto);
        }
    }


    public EgresosAdapder(List<RegistroParam> myDataset, DatabaseHelper helper,Context context) {
        this.mDataset = myDataset;
        this.helper = helper;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EgresosAdapder.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_registro, parent, false);

        ViewHolder vh = new ViewHolder(v);


        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        RegistroParam r = mDataset.get(position);



        if(r.getTipoMovimiento().compareToIgnoreCase(Contants.TIPO_MOV_INGRESO)==0){
            holder.mTextViewSimbolo.setText("+");
        }else{
            holder.mTextViewSimbolo.setText("-");
        }

        holder.mTextViewDescripcion.setText(r.getClasificacionDesc());
        holder.mTextViewFecha.setText(r.getFechaDocumento());
        holder.mTextViewImpuesto.setText(r.getTipoImpuesto());
        holder.mTextViewMonto.setText(r.getMontoTotal()+"");

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
