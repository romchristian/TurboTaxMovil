package turbotaxmovil.ideaspymes.com.py.turbotaxmovil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.ActualizadorService;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.SendDataService;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.wizard.ImpuestoActivity;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {


    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private Firebase rootRef;

    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }

        }
    };

    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        mContentView = findViewById(R.id.fullscreen_content);

        auth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){

                    rootRef = new Firebase("https://turbotaxmobile.firebaseio.com/users/" + firebaseAuth.getCurrentUser().getUid());
                    Log.d("LOGIN","En el listener Auth");
                    rootRef.child("plan").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Log.d("LOGIN","Hay Plan: " + dataSnapshot.getValue());
                            if(dataSnapshot.getValue() != null) {
                                startActivity(new Intent(SplashActivity.this, ImpuestoActivity.class));
                            }else{
                                startActivity(new Intent(SplashActivity.this,PlanActivity.class));
                            }
                            finish();
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {
                            Log.d("LOGIN","Fue cancelado .. no hay plan?: " );
                            startActivity(new Intent(SplashActivity.this,PlanActivity.class));
                            finish();
                        }
                    });

                }else{
                    startActivity(new Intent(SplashActivity.this,Login.class));
                    finish();
                }
            }
        };

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        delayedHide(100);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
        startService(new Intent(this, ActualizadorService.class));

        Log.d("SERVICIO","START SERVICE" );

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(authStateListener != null){
            auth.removeAuthStateListener(authStateListener);
        }
        stopService(new Intent(this, ActualizadorService.class));


    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, 100);
    }



    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
