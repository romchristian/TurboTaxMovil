package turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.servicios;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;

import java.util.Arrays;
import java.util.List;

import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.ClasificacionUsuario;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.DatabaseHelper;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.Libro;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.GsonRequestNoAuth;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.MyVolley;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.ServidorURL;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.response.ClasficacionUsuarioResponse;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.response.LibroResponse;

/**
 * Created by christian.romero on 24/04/2017.
 */

public class ClasificacionWS {

    public static void get(final Context ctx, final DatabaseHelper helper){
        GsonRequestNoAuth<ClasficacionUsuarioResponse> req = new GsonRequestNoAuth<ClasficacionUsuarioResponse>(Request.Method.GET, ServidorURL.PREF_URL + "/clasificacionUsuario/all",
                ClasficacionUsuarioResponse.class, null,
                new Response.Listener<ClasficacionUsuarioResponse>() {
                    @Override
                    public void onResponse(ClasficacionUsuarioResponse response) {

                        if (response.getCodRetorno() == 200) {

                            if (response.getClasificaciones() != null && response.getClasificaciones().length > 0) {

                                List<ClasificacionUsuario> clasificaciones = Arrays.asList(response.getClasificaciones());
                                for (ClasificacionUsuario c : clasificaciones) {
                                    helper.getClasificacionUsuarioDataDao().createOrUpdate(c);
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
