package turbotaxmovil.ideaspymes.com.py.turbotaxmovil;


import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.fragment.ImpuestoFragment;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.ActualizadorService;

public class MainInputActivity extends AppCompatActivity implements ImpuestoFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_input);
        setFragment();
    }


    public void setFragment(){
        // Create new fragment and transaction
        Fragment newFragment = ImpuestoFragment.newInstance("param1", "param2");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d("FRAGMENT_INTERACTION","Uri: " + uri);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
