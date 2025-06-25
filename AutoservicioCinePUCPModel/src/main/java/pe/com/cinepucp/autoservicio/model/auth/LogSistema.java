/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.model.auth;

/**
 *
 * @author Sergio
 */
import java.time.LocalDate;

public class LogSistema {
    private int id;
    private String accion;
    private LocalDate fecha;
    private int id_usuario;

    public LogSistema() {
    }

    public LogSistema(String accion, LocalDate fecha, int id_usuario) {
        this.accion = accion;
        this.fecha = fecha;
        this.id_usuario = id_usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getUsuario() {
        return id_usuario;
    }

    public void setUsuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    // Método toString
    @Override
    public String toString() {
        return "LogSistema{" +
                "id=" + id +
                ", accion='" + accion + '\'' +
                ", fecha=" + fecha +
                ", usuario id=" + id_usuario +
                '}';
    }
}