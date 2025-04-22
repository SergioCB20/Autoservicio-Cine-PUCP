package pe.com.cinepucp.autoservicio.model.auth;

import java.time.LocalDate;

/**
 *
 * @author Miguel
 */
public class Sesion {
    private int id;
    //private Usuario usuario;  //Falta definir esta clase
    private String token;
    private String metodoLogin;
    private LocalDate fechaInicio;
    private LocalDate fechaExpiracion;

    public Sesion() {
    }

    public Sesion(int id, String token, String metodoLogin, LocalDate fechaInicio, LocalDate fechaExpiracion) {
        this.id = id;
        this.token = token;
        this.metodoLogin = metodoLogin;
        this.fechaInicio = fechaInicio;
        this.fechaExpiracion = fechaExpiracion;
        //this.usuario=usuario;
    }//falta agregar al usuario;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMetodoLogin() {
        return metodoLogin;
    }

    public void setMetodoLogin(String metodoLogin) {
        this.metodoLogin = metodoLogin;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDate fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }
     
}
