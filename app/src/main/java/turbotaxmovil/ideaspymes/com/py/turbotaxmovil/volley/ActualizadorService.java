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
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.Libro;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.response.ImpuestoResponse;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.response.LibroResponse;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.servicios.ClasificacionWS;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.servicios.ImpuestoWS;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.servicios.LibroWS;


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

    }

    private final Handler toastHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            cargaDatosBasicosEnParalelo();
        }
    };


    public void cargaDatosBasicosEnParalelo() {
        getImpuestos();
        getLibros();
        getClasificaciones();
    }


    public void getImpuestos() {
        ImpuestoWS.get(ctx, getHelper());
    }

    public void getLibros() {
        LibroWS.get(ctx, getHelper());
    }

    public void getClasificaciones() {
        ClasificacionWS.get(ctx, getHelper());
    }


}
