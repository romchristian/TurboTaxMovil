package turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.response;

import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.Impuesto;

/**
 * Created by christian.romero on 18/04/2017.
 */

public class ImpuestoResponse {
    private int codRetorno;
    private Impuesto[] impuestos;
    private String mensaje;

    public int getCodRetorno() {
        return codRetorno;
    }

    public void setCodRetorno(int codRetorno) {
        this.codRetorno = codRetorno;
    }

    public Impuesto[] getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(Impuesto[] impuestos) {
        this.impuestos = impuestos;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
