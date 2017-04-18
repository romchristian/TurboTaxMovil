package turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.j256.ormlite.android.apptools.OrmLiteBaseService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.DatabaseHelper;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.Impuesto;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.response.ImpuestoResponse;


public class ActualizadorService extends OrmLiteBaseService<DatabaseHelper> {

    public ActualizadorService() {
    }

    public ActualizadorService(Context context) {
        ctx = context;
    }

    private static Timer timer;
    private Context ctx;


    public IBinder onBind(Intent arg0) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        startService();

    }

    private void startService() {
        //Toast.makeText(this, "Start Service Actualizacion ...", Toast.LENGTH_LONG).show();
        Log.d("SERVICIO", "DEBUG 0");
        //timer.scheduleAtFixedRate(new mainTask(ctx), 0, 1000000000);
        new mainTask(ctx).run();

        timer = new Timer();
        timer.scheduleAtFixedRate(new mainTask(ctx), 0, 3600000);

    }

    private class mainTask extends TimerTask {
        private Context context;


        public mainTask(Context context) {
            this.context = context;
        }

        public void run() {
            toastHandler.sendEmptyMessage(0);

        }
    }

    public void onDestroy() {

        timer.cancel();
        timer.purge();
        super.onDestroy();
        //Toast.makeText(this, "Service Actualizacion Stopped ...", Toast.LENGTH_LONG).show();
    }

    private final Handler toastHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            Log.d("SERVICIO", "DEBUG 1 ");

            Log.d("SERVICIO", "DEBUG 2 ");
            cargaDatosBasicosEnParalelo();

        }
    };


    public void cargaDatosBasicosEnParalelo() {
        Log.d("SERVICIO", "DEBUG 3 ");
        getImpuestos();
    }


    public void getImpuestos() {
        //final Context context = getApplicationContext();
        final Context context = ctx;
        Log.d("SERVICIO", "DEBUG 4");

        GsonRequestNoAuth<ImpuestoResponse> req = new GsonRequestNoAuth<ImpuestoResponse>(Request.Method.GET, ServidorURL.PREF_URL + "/impuesto/all",
                ImpuestoResponse.class, null,
                new Response.Listener<ImpuestoResponse>() {
                    @Override
                    public void onResponse(ImpuestoResponse response) {
                        Log.d("SERVICIO", "response: " + response.getCodRetorno());
                        if (response.getCodRetorno() == 200) {
                            Log.d("SERVICIO", "response impuestos: " + response.getImpuestos());
                            if (response.getImpuestos() != null && response.getImpuestos().length > 0) {

                                List<Impuesto> impuestos = Arrays.asList(response.getImpuestos());
                                Log.d("SERVICIO", "lista: " + impuestos.size());
                                for (Impuesto i : impuestos) {
                                    getHelper().getImpuestoDataDao().createOrUpdate(i);
                                }

                            }
                        }

                    }
                }, context);

        int socketTimeout = 50000;//50 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        req.setRetryPolicy(policy);
        MyVolley.addToQueue(req);

    }


}
