package turbotaxmovil.ideaspymes.com.py.turbotaxmovil;

/**
 * Created by christian.romero on 26/10/2016.
 */

public class Gasto {
    private String timbrado;
    private String tipoImpuesto;
    private String tipoDocumento;
    private String ruc;
    private String nro;
    private double total;
    private double iva;

    public Gasto() {
    }

    public Gasto(String timbrado, String tipoImpuesto, String tipoDocumento, String ruc, String nro, double total, double iva) {
        this.timbrado = timbrado;
        this.tipoImpuesto = tipoImpuesto;
        this.tipoDocumento = tipoDocumento;
        this.ruc = ruc;
        this.nro = nro;
        this.total = total;
        this.iva = iva;
    }

    public String getTimbrado() {
        return timbrado;
    }

    public void setTimbrado(String timbrado) {
        this.timbrado = timbrado;
    }

    public String getTipoImpuesto() {
        return tipoImpuesto;
    }

    public void setTipoImpuesto(String tipoImpuesto) {
        this.tipoImpuesto = tipoImpuesto;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }
}
