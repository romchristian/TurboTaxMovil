package turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by christian.romero on 24/05/2017.
 */

@DatabaseTable(tableName = "registroparam")
public class RegistroParam {

    @DatabaseField(id = true)
    private String registroUUID;
    @DatabaseField
    private String userUUID;
    @DatabaseField
    private String plan;

    @DatabaseField
    private String tipoImpuesto;
    @DatabaseField
    private Long libroId;
    @DatabaseField
    private Long clasificacionUsuarioId;
    @DatabaseField
    private boolean generarAporteIps;
    @DatabaseField
    private boolean bienesGanaciales;

    //Datos documento
    @DatabaseField
    private Integer anio;
    @DatabaseField
    private Integer mes;
    @DatabaseField
    private String tipoPeriodo;
    @DatabaseField
    private String tipoDocumento;
    @DatabaseField
    private String fechaDocumento;
    @DatabaseField
    private String vecimientoDocumento;
    @DatabaseField
    private String timbrado;
    @DatabaseField
    private String numero;
    @DatabaseField
    private Integer cantCuotas;

    //Proveedor/Cliente
    private String ruc;
    @DatabaseField
    private String razonSocial;
    @DatabaseField
    private String rucComprador;

    private Double gravada05;
    @DatabaseField
    private Double impuesto05;
    @DatabaseField
    private Double gravada10;
    @DatabaseField
    private Double impuesto10;
    @DatabaseField
    private Double exenta;
    @DatabaseField
    private Double impuetoTotal;
    @DatabaseField
    private Double montoTotal;
    @DatabaseField
    private Double montoCobrado;
    @DatabaseField
    private Double montoGravado;
    @DatabaseField
    private Double montoNoGravado;
    @DatabaseField
    private Double retenciones;
    @DatabaseField
    private Double baseImponibleImportaciones;
    @DatabaseField
    private boolean gasto;
    @DatabaseField
    private boolean inversion;
    @DatabaseField
    private String fechaRegistro;
    @DatabaseField
    private String estadoDescarga;

    public String getRegistroUUID() {
        return registroUUID;
    }

    public void setRegistroUUID(String registroUUID) {
        this.registroUUID = registroUUID;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getTipoImpuesto() {
        return tipoImpuesto;
    }

    public void setTipoImpuesto(String tipoImpuesto) {
        this.tipoImpuesto = tipoImpuesto;
    }

    public Long getLibroId() {
        return libroId;
    }

    public void setLibroId(Long libroId) {
        this.libroId = libroId;
    }

    public Long getClasificacionUsuarioId() {
        return clasificacionUsuarioId;
    }

    public void setClasificacionUsuarioId(Long clasificacionUsuarioId) {
        this.clasificacionUsuarioId = clasificacionUsuarioId;
    }

    public boolean isGenerarAporteIps() {
        return generarAporteIps;
    }

    public void setGenerarAporteIps(boolean generarAporteIps) {
        this.generarAporteIps = generarAporteIps;
    }

    public boolean isBienesGanaciales() {
        return bienesGanaciales;
    }

    public void setBienesGanaciales(boolean bienesGanaciales) {
        this.bienesGanaciales = bienesGanaciales;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public String getTipoPeriodo() {
        return tipoPeriodo;
    }

    public void setTipoPeriodo(String tipoPeriodo) {
        this.tipoPeriodo = tipoPeriodo;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(String fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public String getVecimientoDocumento() {
        return vecimientoDocumento;
    }

    public void setVecimientoDocumento(String vecimientoDocumento) {
        this.vecimientoDocumento = vecimientoDocumento;
    }

    public String getTimbrado() {
        return timbrado;
    }

    public void setTimbrado(String timbrado) {
        this.timbrado = timbrado;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getCantCuotas() {
        return cantCuotas;
    }

    public void setCantCuotas(Integer cantCuotas) {
        this.cantCuotas = cantCuotas;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRucComprador() {
        return rucComprador;
    }

    public void setRucComprador(String rucComprador) {
        this.rucComprador = rucComprador;
    }

    public Double getGravada05() {
        return gravada05;
    }

    public void setGravada05(Double gravada05) {
        this.gravada05 = gravada05;
    }

    public Double getImpuesto05() {
        return impuesto05;
    }

    public void setImpuesto05(Double impuesto05) {
        this.impuesto05 = impuesto05;
    }

    public Double getGravada10() {
        return gravada10;
    }

    public void setGravada10(Double gravada10) {
        this.gravada10 = gravada10;
    }

    public Double getImpuesto10() {
        return impuesto10;
    }

    public void setImpuesto10(Double impuesto10) {
        this.impuesto10 = impuesto10;
    }

    public Double getExenta() {
        return exenta;
    }

    public void setExenta(Double exenta) {
        this.exenta = exenta;
    }

    public Double getImpuetoTotal() {
        return impuetoTotal;
    }

    public void setImpuetoTotal(Double impuetoTotal) {
        this.impuetoTotal = impuetoTotal;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Double getMontoCobrado() {
        return montoCobrado;
    }

    public void setMontoCobrado(Double montoCobrado) {
        this.montoCobrado = montoCobrado;
    }

    public Double getMontoGravado() {
        return montoGravado;
    }

    public void setMontoGravado(Double montoGravado) {
        this.montoGravado = montoGravado;
    }

    public Double getMontoNoGravado() {
        return montoNoGravado;
    }

    public void setMontoNoGravado(Double montoNoGravado) {
        this.montoNoGravado = montoNoGravado;
    }

    public Double getRetenciones() {
        return retenciones;
    }

    public void setRetenciones(Double retenciones) {
        this.retenciones = retenciones;
    }

    public Double getBaseImponibleImportaciones() {
        return baseImponibleImportaciones;
    }

    public void setBaseImponibleImportaciones(Double baseImponibleImportaciones) {
        this.baseImponibleImportaciones = baseImponibleImportaciones;
    }

    public boolean isGasto() {
        return gasto;
    }

    public void setGasto(boolean gasto) {
        this.gasto = gasto;
    }

    public boolean isInversion() {
        return inversion;
    }

    public void setInversion(boolean inversion) {
        this.inversion = inversion;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstadoDescarga() {
        return estadoDescarga;
    }

    public void setEstadoDescarga(String estadoDescarga) {
        this.estadoDescarga = estadoDescarga;
    }


}
