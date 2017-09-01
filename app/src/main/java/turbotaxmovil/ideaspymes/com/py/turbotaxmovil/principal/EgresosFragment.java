package turbotaxmovil.ideaspymes.com.py.turbotaxmovil.principal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.sql.SQLException;
import java.util.List;

import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.R;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.adapters.EgresosAdapder;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.DatabaseHelper;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.OrmBaseLiteFragment;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.RegistroParam;

/**
 * A simple {@link Fragment} subclass.
 */
public class EgresosFragment extends OrmBaseLiteFragment<DatabaseHelper> {

    private static final String TAG = EgresosFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private EgresosAdapder adapder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v =  inflater.inflate(R.layout.fragment_egresos, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        adapder = new EgresosAdapder(getLista(), getHelper(),getActivity());
        mRecyclerView.setAdapter(adapder);

        return v;
    }

    private List<RegistroParam> getLista() {
        List<RegistroParam> lista = null;
        try {
            lista = getHelper().getRegistroParamDao().queryForAll();
        } catch (SQLException e) {
            Log.d(TAG, "Error al traer los movimientos: " + e.getMessage());
        }
        return lista;
    }

}
