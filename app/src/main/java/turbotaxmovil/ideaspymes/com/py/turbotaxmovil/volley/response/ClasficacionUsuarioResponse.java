package turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.response;

import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.ClasificacionUsuario;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.Libro;

/**
 * Created by christian.romero on 18/04/2017.
 */

public class ClasficacionUsuarioResponse {
    private int codRetorno;
    private ClasificacionUsuario[] clasificaciones;
    private String mensaje;

    public int getCodRetorno() {
        return codRetorno;
    }

    public void setCodRetorno(int codRetorno) {
        this.codRetorno = codRetorno;
    }

    public ClasificacionUsuario[] getClasificaciones() {
        return clasificaciones;
    }

    public void setClasificaciones(ClasificacionUsuario[] clasificaciones) {
        this.clasificaciones = clasificaciones;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
