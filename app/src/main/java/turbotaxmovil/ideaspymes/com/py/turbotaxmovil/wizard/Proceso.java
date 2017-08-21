package turbotaxmovil.ideaspymes.com.py.turbotaxmovil.wizard;

import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.ClasificacionUsuario;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.Impuesto;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.Libro;

/**
 * Created by christian.romero on 21/08/2017.
 */

public class Proceso {
    public Long impuestoId;
    public Long libroId;
    public Long clasificacionId;


    private static Proceso instancia;

    public static Proceso instance(){
        if(instancia == null)
            instancia = new Proceso();

        return instancia;
    }

    public static void destroy(){
        instancia = null;
    }
}
