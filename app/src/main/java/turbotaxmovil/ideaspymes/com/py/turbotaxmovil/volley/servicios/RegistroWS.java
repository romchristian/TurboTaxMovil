package turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.servicios;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.mapped.MappedPreparedStmt;

import java.util.Arrays;
import java.util.List;

import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.DatabaseHelper;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.Libro;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.RegistroParam;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.GsonRequestNoAuth;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.MyVolley;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.ServidorURL;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.response.LibroResponse;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.response.OperacionResponse;

/**
 * Created by christian.romero on 24/04/2017.
 */

public class RegistroWS {

    public static void post(final Context ctx, final DatabaseHelper helper, final RegistroParam registroParam){

        final String body = new GsonBuilder().setPrettyPrinting().create().toJson(registroParam);

        GsonRequestNoAuth<OperacionResponse> req = new GsonRequestNoAuth<OperacionResponse>(Request.Method.POST, ServidorURL.PREF_URL + "/libromov/registra",
                OperacionResponse.class, body,
                new Response.Listener<OperacionResponse>() {
                    @Override
                    public void onResponse(OperacionResponse response) {

                        Log.d("REGISTRO","response: " + response);
                        if (response.getCodRetorno() == 200) {
                            Log.d("REGISTRO","response cod: " + response.getCodRetorno());
                            if (response.getReferencia()!= null && response.getReferencia().length() > 0) {
                                    List<RegistroParam> registros =helper.getRegistroParamDataDao().queryForEq("registroUUID",response.getReferencia());

                                    if(registros != null && !registros.isEmpty()) {
                                        RegistroParam r = registros.get(0);
                                        r.setEstadoDescarga("S");
                                        helper.getRegistroParamDataDao().update(r);
                                    }
                            }
                        }

                    }
                }, ctx);

        int socketTimeout = 50000;//50 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        req.setRetryPolicy(policy);
        MyVolley.addToQueue(req);
    }
}
