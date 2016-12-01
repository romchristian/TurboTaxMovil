package turbotaxmovil.ideaspymes.com.py.turbotaxmovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

public class PerfilActivity extends AppCompatActivity {

    private Firebase rootRef;
    private Button buttonGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        buttonGuardar =(Button) findViewById(R.id.buttonGuardar);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        rootRef = new Firebase("https://turbotaxmobile.firebaseio.com/users/" + auth.getCurrentUser().getUid());

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar();
            }
        });
    }

    private void guardar() {
        startActivity(new Intent(PerfilActivity.this,MainActivity.class));
        finish();
    }


}
