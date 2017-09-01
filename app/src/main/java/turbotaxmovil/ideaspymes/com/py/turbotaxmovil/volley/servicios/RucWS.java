package turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.servicios;

import android.content.Context;
import android.widget.EditText;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;

import java.util.Arrays;
import java.util.List;

import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.DatabaseHelper;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.Impuesto;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.GsonRequestNoAuth;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.MyVolley;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.ServidorURL;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.response.Contribuyente;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.response.ImpuestoResponse;

/**
 * Created by christian.romero on 24/04/2017.
 */

public class RucWS {

    public static void get(final Context ctx, final DatabaseHelper helper, final EditText editText, String ruc){

        GsonRequestNoAuth<Contribuyente> req = new GsonRequestNoAuth<Contribuyente>(Request.Method.GET, ServidorURL.RUC_URL + ruc,
                Contribuyente.class, null,
                new Response.Listener<Contribuyente>() {
                    @Override
                    public void onResponse(Contribuyente response) {

                       if(response != null && response.getRazonsocial() != null && response.getRazonsocial().length() > 0){
                           editText.setText(response.getRazonsocial());
                       }

                    }
                }, ctx);

        int socketTimeout = 50000;//50 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        req.setRetryPolicy(policy);
        MyVolley.addToQueue(req);
    }
}
