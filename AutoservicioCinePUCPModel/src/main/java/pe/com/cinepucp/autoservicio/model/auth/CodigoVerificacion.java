package pe.com.cinepucp.autoservicio.model.auth;

import java.time.LocalDate;

/**
 *
 * @author Miguel
 */
public class CodigoVerificacion {
    private int id;
//    private Usuario usuario; //flata definir esta clase
    private String telefono;
    private String codigo;
    private LocalDate fechaCreacion;
    private LocalDate fechaExpiracion;
    private boolean usado;

    public CodigoVerificacion() {
    }

    public CodigoVerificacion(int id, String telefono, String codigo, LocalDate fechaCreacion, LocalDate fechaExpiracion, boolean usado) {
        this.id = id;
        this.telefono = telefono;
        this.codigo = codigo;
        this.fechaCreacion = fechaCreacion;
        this.fechaExpiracion = fechaExpiracion;
        this.usado = usado;
        //this.usuario = usuario;
    } //Estaría faltando agregar el usuario
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDate fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public boolean isUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }
}
