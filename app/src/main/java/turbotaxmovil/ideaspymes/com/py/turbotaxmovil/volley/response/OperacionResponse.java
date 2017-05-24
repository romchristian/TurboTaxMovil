package turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.response;

import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.Libro;

/**
 * Created by christian.romero on 18/04/2017.
 */

public class OperacionResponse {
    private int codRetorno;
    private String referencia;
    private String mensaje;

    public int getCodRetorno() {
        return codRetorno;
    }

    public void setCodRetorno(int codRetorno) {
        this.codRetorno = codRetorno;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
