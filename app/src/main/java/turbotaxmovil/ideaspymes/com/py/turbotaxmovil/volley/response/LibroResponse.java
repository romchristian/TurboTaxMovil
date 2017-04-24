package turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley.response;

import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.Impuesto;
import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities.Libro;

/**
 * Created by christian.romero on 18/04/2017.
 */

public class LibroResponse {
    private int codRetorno;
    private Libro[] libros;
    private String mensaje;

    public int getCodRetorno() {
        return codRetorno;
    }

    public void setCodRetorno(int codRetorno) {
        this.codRetorno = codRetorno;
    }

    public Libro[] getLibros() {
        return libros;
    }

    public void setLibros(Libro[] libros) {
        this.libros = libros;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
