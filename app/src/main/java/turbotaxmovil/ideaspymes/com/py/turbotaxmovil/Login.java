package turbotaxmovil.ideaspymes.com.py.turbotaxmovil;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonRegistrar;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private Firebase rootRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonRegistrar = (Button) findViewById(R.id.buttonRegistrar);
        buttonRegistrar.setOnClickListener(this);

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
                                startActivity(new Intent(Login.this, MainActivity.class));
                            }else{
                                startActivity(new Intent(Login.this,PlanActivity.class));
                            }
                            finish();
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {
                            Log.d("LOGIN","Fue cancelado .. no hay plan?: " );
                            startActivity(new Intent(Login.this,PlanActivity.class));
                            finish();
                        }
                    });

                }
            }
        };

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonRegistrar:

                final String email = editTextEmail.getText().toString();
                final String password = editTextPassword.getText().toString();

                loginWithEmailAndPassword(email,password);

              /*  auth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, task.getResult().getUser().getEmail() + ", se registro con exito!", Toast.LENGTH_LONG).show();
                            loginWithEmailAndPassword(email,password);
                        }
                    }
                });*/


                break;
        }
    }

    public void loginWithEmailAndPassword(String email, String password){
        if(email != null && password != null && !email.isEmpty() && !password.isEmpty()) {
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Login.this, "Fallo login", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }else{
            Toast.makeText(this,"Debe ingreasar email y password", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(authStateListener != null){
            auth.removeAuthStateListener(authStateListener);
        }
    }
}
