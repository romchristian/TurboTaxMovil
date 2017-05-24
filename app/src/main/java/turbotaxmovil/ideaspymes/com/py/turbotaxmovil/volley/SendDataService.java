package turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.j256.ormlite.android.apptools.OrmLiteBaseService;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.DatabaseHelper;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.RegistroParam;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.servicios.ClasificacionWS;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.servicios.ImpuestoWS;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.servicios.LibroWS;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.servicios.RegistroWS;


public class SendDataService extends OrmLiteBaseService<DatabaseHelper> {

    public SendDataService() {
    }

    public SendDataService(Context context) {
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
        timer.scheduleAtFixedRate(new mainTask(ctx), 0, 10000);

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
            send();
        }
    };


    public void send() {
        List<RegistroParam> registros = getHelper().getRegistroParamDataDao().queryForEq("estadoDescarga", "N");
        if(registros != null && !registros.isEmpty()){
            for (RegistroParam r: registros) {
                RegistroWS.post(ctx, getHelper(), r);
            }
        }
    }


}
