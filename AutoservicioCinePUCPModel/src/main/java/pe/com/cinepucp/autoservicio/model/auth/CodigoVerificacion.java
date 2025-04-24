package pe.com.cinepucp.autoservicio.model.auth;

import java.time.LocalDateTime;

/**
 *
 * @author Miguel
 */
public class CodigoVerificacion {
    private int id;
    private Usuario usuario; 
    private String telefono;
    private String codigo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaExpiracion;
    private boolean usado;

    public CodigoVerificacion() {
    }

    public CodigoVerificacion(int id, String telefono, String codigo, LocalDateTime fechaCreacion, LocalDateTime fechaExpiracion, boolean usado) {
        this.id = id;
        this.telefono = telefono;
        this.codigo = codigo;
        this.fechaCreacion = fechaCreacion;
        this.fechaExpiracion = fechaExpiracion;
        this.usado = usado;
    }
    
    public CodigoVerificacion(int id,Usuario usuario, String telefono, String codigo, LocalDateTime fechaCreacion, LocalDateTime fechaExpiracion, boolean usado) {
        this.id = id;
        this.telefono = telefono;
        this.codigo = codigo;
        this.fechaCreacion = fechaCreacion;
        this.fechaExpiracion = fechaExpiracion;
        this.usado = usado;
        this.usuario = usuario;
    }
    
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

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDateTime fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public boolean isUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
