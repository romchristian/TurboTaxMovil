package turbotaxmovil.ideaspymes.com.py.turbotaxmovil;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

import static android.R.id.toggle;

public class PlanActivity extends AppCompatActivity implements View.OnClickListener{

    private CardView cardSalario;
    private CardView cardHonorario;
    private CardView cardDividendo;
    private CardView cardInteres;

    private CardView cardBasico;
    private CardView cardDeluxe;
    private CardView cardPremiun;
    private CardView cardPlatinum;


    private boolean checkSalario;
    private boolean checkHonorario;
    private boolean checkDividendo;
    private boolean checkInteres;

    private boolean checkBasico;
    private boolean checkDeluxe;
    private boolean checkPremiun;
    private boolean checkPlatinum;

    private Firebase rootRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        rootRef = new Firebase("https://turbotaxmobile.firebaseio.com/users/" + auth.getCurrentUser().getUid());

        setupViews();
    }

    private void setupViews() {
        cardSalario = (CardView) findViewById(R.id.tipoIngresoSalario);
        cardHonorario = (CardView) findViewById(R.id.tipoIngresoHonorario);
        cardDividendo = (CardView) findViewById(R.id.tipoIngresoDividendo);
        cardInteres = (CardView) findViewById(R.id.tipoIngresoIntereses);

        cardBasico = (CardView) findViewById(R.id.planBasico);
        cardDeluxe = (CardView) findViewById(R.id.planDeluxe);
        cardPremiun = (CardView) findViewById(R.id.planPremiun);
        cardPlatinum = (CardView) findViewById(R.id.planPlatinum);

        cardSalario.setOnClickListener(this);
        cardHonorario.setOnClickListener(this);
        cardDividendo.setOnClickListener(this);
        cardInteres.setOnClickListener(this);

        cardBasico.setOnClickListener(this);
        cardDeluxe.setOnClickListener(this);
        cardPremiun.setOnClickListener(this);
        cardPlatinum.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tipoIngresoSalario:
                toggleSalario();
                togglePlan();
                break;
            case R.id.tipoIngresoHonorario:
                toggleHonorario();
                togglePlan();
                break;
            case R.id.tipoIngresoDividendo:
                toggleDividendo();
                togglePlan();
                break;
            case R.id.tipoIngresoIntereses:
                toggleInteres();
                togglePlan();
                break;
            case R.id.planBasico:
                enviarPlanBasico();
                break;
            case R.id.planDeluxe:
                enviarPlanDeluxe();
                break;
            case R.id.planPremiun:
                enviarPlanPremiun();
                break;
            case R.id.planPlatinum:
                enviarPlanPlatinum();
                break;
        }
    }

    private void toggleSalario() {
        if(checkSalario){
            cardSalario.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            checkSalario = false;
        }else{
            cardSalario.setCardBackgroundColor(Color.parseColor("#33B5FF"));
            checkSalario = true;
        }
    }

    private void toggleHonorario() {
        if(checkHonorario){
            cardHonorario.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            checkHonorario = false;
        }else{
            cardHonorario.setCardBackgroundColor(Color.parseColor("#33B5FF"));
            checkHonorario = true;
        }
    }

    private void toggleDividendo() {
        if(checkDividendo){
            cardDividendo.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            checkDividendo = false;
        }else{
            cardDividendo.setCardBackgroundColor(Color.parseColor("#33B5FF"));
            checkDividendo = true;
        }
    }

    private void toggleInteres() {
        if(checkInteres){
            cardInteres.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            checkInteres = false;
        }else{
            cardInteres.setCardBackgroundColor(Color.parseColor("#33B5FF"));
            checkInteres = true;
        }
    }

    private void togglePlan(){
        if(checkInteres){
            cardPlatinum.setCardBackgroundColor(Color.parseColor("#33B5FF"));
            cardPremiun.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            cardDeluxe.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            cardBasico.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            checkBasico = false;
            checkDeluxe = false;
            checkPremiun = false;
            checkPlatinum = true;
        }else if(checkDividendo){
            cardPlatinum.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            cardPremiun.setCardBackgroundColor(Color.parseColor("#33B5FF"));
            cardDeluxe.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            cardBasico.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            checkBasico = false;
            checkDeluxe = false;
            checkPremiun = true;
            checkPlatinum = false;

        }else if(checkHonorario){
            cardPlatinum.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            cardPremiun.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            cardDeluxe.setCardBackgroundColor(Color.parseColor("#33B5FF"));
            cardBasico.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            checkBasico = false;
            checkDeluxe = true;
            checkPremiun = false;
            checkPlatinum = false;

        }else if(checkSalario){

            cardPlatinum.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            cardPremiun.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            cardDeluxe.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            cardBasico.setCardBackgroundColor(Color.parseColor("#33B5FF"));

            checkBasico = true;
            checkDeluxe = false;
            checkPremiun = false;
            checkPlatinum = false;
        }else{
            cardPlatinum.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            cardPremiun.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            cardDeluxe.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            cardBasico.setCardBackgroundColor(Color.parseColor("#FFFFFF"));

            checkBasico = false;
            checkDeluxe = false;
            checkPremiun = false;
            checkPlatinum = false;
        }
    }

    public void enviarPlanBasico(){
        if(checkBasico){
            Toast.makeText(this,"Plan seleccionado: Básico",Toast.LENGTH_LONG).show();
            guardarPlan("BASICO");
        }
    }

    public void enviarPlanDeluxe(){
        if(checkDeluxe){
            Toast.makeText(this,"Plan seleccionado: Deluxe",Toast.LENGTH_LONG).show();
            guardarPlan("DELUXE");
        }
    }

    public void enviarPlanPremiun(){
        if(checkPremiun){
            Toast.makeText(this,"Plan seleccionado: Premiun",Toast.LENGTH_LONG).show();
            guardarPlan("PREMIUN");
        }
    }

    public void enviarPlanPlatinum(){

        if(checkPlatinum){
            Toast.makeText(this,"Plan seleccionado: Platinum",Toast.LENGTH_LONG).show();
            guardarPlan("PLATINUM");
        }
    }

    public void guardarPlan(final  String plan){
        rootRef.child("plan")
                .setValue(plan);

        startActivity(new Intent(PlanActivity.this,PerfilActivity.class));
        finish();
    }
}
