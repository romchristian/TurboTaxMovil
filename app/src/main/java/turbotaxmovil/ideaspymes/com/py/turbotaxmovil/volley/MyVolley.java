package turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.FirebaseApp;


/**
 * Helper class that is used to provide references to initialized RequestQueue(s) and ImageLoader(s)
 *
 * @author Ognyan Bankov
 *
 */
public class MyVolley {
    public static final String TAG = MyVolley.class.getSimpleName();
    private static RequestQueue mRequestQueue;

    private MyVolley() {

    }

    public static RequestQueue getQueue() {
        if (mRequestQueue != null) {
            return mRequestQueue;
        } else {
            mRequestQueue = Volley.newRequestQueue(FirebaseApp.getInstance());
            return mRequestQueue;
        }
    }

    public static RequestQueue getQueue(Context context) {
        if (mRequestQueue != null) {
            return mRequestQueue;
        } else {
            mRequestQueue = Volley.newRequestQueue(context);
            return mRequestQueue;
        }
    }

    public static <T> void addToQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getQueue().add(req);
    }

    public static <T> void addToQueue(Request<T> req) {
        req.setTag(TAG);
        getQueue().add(req);
    }
    public static <T> void addToQueue(Request<T> req, Context context) {
        req.setTag(TAG);
        getQueue(context).add(req);
    }

    public static <T> void addToQueue(Request<T> req, String tag, Context context) {
        req.setTag(TAG);
        getQueue(context).add(req);
    }

    public static void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}