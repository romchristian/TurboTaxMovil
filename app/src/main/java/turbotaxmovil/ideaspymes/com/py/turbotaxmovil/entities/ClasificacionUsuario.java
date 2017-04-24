package turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by christian.romero on 24/04/2017.
 */

@DatabaseTable(tableName = "clasificacion_usuario")
public class ClasificacionUsuario {
    @DatabaseField(id = true)
    private Long id;
    @DatabaseField
    private Integer index;
    @DatabaseField
    private String nombre;
    @DatabaseField
    private Long libroId;
    @DatabaseField
    private String estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getLibroId() {
        return libroId;
    }

    public void setLibroId(Long libroId) {
        this.libroId = libroId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
