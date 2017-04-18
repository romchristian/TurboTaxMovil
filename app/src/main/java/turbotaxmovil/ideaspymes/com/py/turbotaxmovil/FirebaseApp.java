package turbotaxmovil.ideaspymes.com.py.turbotaxmovil;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Christian on 22/10/2016.
 */

public class FirebaseApp extends Application {

    private static FirebaseApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        mInstance = this;
    }
    public static synchronized FirebaseApp getInstance() {
        return mInstance;
    }
}
