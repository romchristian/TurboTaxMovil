package turbotaxmovil.ideaspymes.com.py.turbotaxmovil.principal;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.R;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.PreferencesUtil;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.ServidorURL;

public class PrincipalActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(value = R.id.toolbar)
    Toolbar toolbar;
    @BindView(value = R.id.tablayout)
    TabLayout tabLayout;
    @BindView(value = R.id.viewpager)
    ViewPager pager;
    @BindView(value = R.id.fab)
    FloatingActionButton fab;
    int tabCurrentPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        ButterKnife.bind(this);

        configurarActionBar();
        setupViewPager();
        setupTablayout();

        fab.setOnClickListener(this);
    }

    private void configurarActionBar() {
        setSupportActionBar(toolbar);
        // Show menu icon
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void setupViewPager() {
        pager.setAdapter(new PrincipalViewPagerAdapter(getSupportFragmentManager()));
    }

    private void setupTablayout() {
        tabLayout.setupWithViewPager(pager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabCurrentPosition = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:
                addOperation();
                break;
        }
    }


    private void addOperation() {
        switch (tabCurrentPosition){
            case 0:
                addIngreso();
                break;
            case 1:
                addEgreso();
                break;
        }
    }

    private void addEgreso() {
        Toast.makeText(getApplicationContext(),"Agregar Egreso",Toast.LENGTH_LONG).show();
        startActivity(new Intent(this,FacturaActivity.class));
    }

    private void addIngreso() {
        Toast.makeText(getApplicationContext(),"Agregar Ingreso",Toast.LENGTH_LONG).show();
        startActivity(new Intent(this,FacturaActivity.class));
    }


}
