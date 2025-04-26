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
import java.util.List;

public class LogSistema {
    private int id;
    private String accion;
    private List<String> tablesAfectadas;
    private LocalDate fecha;
    private Usuario usuario;

    public LogSistema() {
    }

    public LogSistema(String accion, List<String> tablesAfectadas, LocalDate fecha, Usuario usuario) {
        this.accion = accion;
        this.tablesAfectadas = tablesAfectadas;
        this.fecha = fecha;
        this.usuario = usuario;
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

    public List<String> getTablesAfectadas() {
        return tablesAfectadas;
    }

    public void setTablesAfectadas(List<String> tablesAfectadas) {
        this.tablesAfectadas = tablesAfectadas;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // Método toString
    @Override
    public String toString() {
        return "LogSistema{" +
                "id=" + id +
                ", accion='" + accion + '\'' +
                ", tablesAfectadas=" + tablesAfectadas +
                ", fecha=" + fecha +
                ", usuario=" + usuario.getNombre()+ "(" + usuario.getId() + ")"+
                '}';
    }
}