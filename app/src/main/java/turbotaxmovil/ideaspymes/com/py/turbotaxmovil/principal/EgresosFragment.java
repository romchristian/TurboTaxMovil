package turbotaxmovil.ideaspymes.com.py.turbotaxmovil.principal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EgresosFragment extends Fragment {


    public EgresosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_egresos, container, false);
    }

}
