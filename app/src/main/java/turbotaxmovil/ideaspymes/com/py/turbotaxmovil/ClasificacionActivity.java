package turbotaxmovil.ideaspymes.com.py.turbotaxmovil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;

import static android.R.id.list;
import static butterknife.OnItemSelected.Callback.NOTHING_SELECTED;

public class ClasificacionActivity extends AppCompatActivity {

    @BindView(R.id.lista)
    ListView listView;
    private int libroIndex;
    ArrayAdapter<String> adapter;

    String[] valuesIngresos = {
            "1. Salario, Horas Extras, Comisiones, Otros beneficios sujetos a IPS",
            "2. Gratificaciones, Bonos, Ayuda Alimentaria, Otros beneficios no sujetos a IPS",
            "3. Honorarios Profesionales",
            "4. Jubilaciones, pensiones, aguinaldo, remuneraciones de diplomáticos, indemnizaciones del trabajo.",
            "5. Ganancia de participaciones accionarias",
            "6. Ganancia por venta de inmuebles, acciones y otros",
            "7. Ganancias de inversiones financieras",
            "8. Venta de bienes, indemnizaciones no laborales, obtención de premios."
    };

    String[] valuesEgresos = {
            "1. Gastos personales y familiares en el pais",
            "2. Gastos personales y familiares en el exterior",
            "3. Donaciones",
            "4. Colocación de depósitos de ahorro en entidades bancarias, financieras, cooperativas; inversiones o en fondos privados de jubilaciones",
            "5. Capitalización de excedentes, retornos e intereses y cuotas o aportes en entidades exoneradas por Ley",
            "6. Gastos de las Sociedades Simples en el país",
            "7. Gastos de las Sociedades Simples en el exterior",
            "8. Intereses, comisiones y recargos por la obtención de préstamos y financiaciones",
            "9. Costo por la enajenación o transferencia de inmuebles, cesión de derechos, venta de títulos, acciones o cuotas de capital, regalías y otros similares",
            "10. Otros gastos no mencionados en los incisos anteriores, debidamente documentados"

    };
    String[] valuesInversiones = {
            "1. Muebles para oficina o el hogar",
            "2. Útiles y enseres",
            "3. Electrodomésticos y equipos electrónicos",
            "4. Joyas, alhajas y similares, en metales y piedras preciosas",
            "5. Obras de arte en cuadros, esculturas y similares",
            "6. Herramientas, instrumentos y equipos",
            "7. Equipos de informática",
            "8. Automóviles, camionetas, camiones, remolques o acoplados y similares",
            "9. Motocicletas, motonetas, cuaciclones, bicicletas y similares",
            "10. Restantes bienes similares",
            "11. Aviones, Avionetas, helicópteros y similares",
            "12. Instalaciones de tierra, material de vuelo y demas bienes similares",
            "13. Embarcaciones en general, tales como barcos, remolcadores, lanchas, chatas y similares",
            "14. Canoas, botes y demás bienes",
            "15. Adquisición de Inmuebles",
            "16. Contrucciones o mejoras de inmuebles propios o arrendados",
            "17. Bienes incorporales",
            "18. Adquisicion de derechos, titulos, acciones, cuotas de capital de sociedad y similares",
            "19. Los restantes bienes no contemplados en los incisos precedentes"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clasificacion);

        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                libroIndex= 0;
            } else {
                libroIndex= extras.getInt("libroIndex");
            }
        } else {
            libroIndex= (int) savedInstanceState.getSerializable("libroIndex");
        }

        cargaLista();
    }


    @OnItemSelected(R.id.lista)
    void onItemSelected(int position) {

    }


    private void cargaLista() {

        switch (libroIndex) {
            case 1:
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, valuesIngresos);
                listView.setAdapter(adapter);
                break;
            case 2:
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, valuesEgresos);
                listView.setAdapter(adapter);
                break;
            case 3:
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, valuesInversiones);
                listView.setAdapter(adapter);
                break;
        }
    }


}
