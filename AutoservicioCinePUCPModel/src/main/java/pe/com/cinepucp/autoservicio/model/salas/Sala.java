/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.model.salas;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author Sergio
 */
public class Sala {

    private Integer id;
    private String nombre;
    private Integer capacidad;
    private TipoSala tipoSala;
    private List<Asiento> asientos;
    private boolean activa = true;
    private Date fechaModificacion;
    private Integer usuarioModificacion;

    public Sala() {
    }

    public Sala(String nombre, Integer capacidad, TipoSala tipoSala) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tipoSala = tipoSala;    
        this.asientos = new ArrayList<>();        
        
    }

    public Sala(String nombre, Integer capacidad, TipoSala tipoSala, List<Asiento> asientos) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tipoSala = tipoSala;
        this.asientos = asientos;
    }
    public Sala(String nombre, Integer capacidad, TipoSala tipoSala, boolean activo,Integer usuario) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tipoSala = tipoSala;
        this.activa=activo;
        this.usuarioModificacion=usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public TipoSala getTipoSala() {
        return tipoSala;
    }

    public void setTipoSala(TipoSala tipoSala) {
        this.tipoSala = tipoSala;
    }

    public List<Asiento> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<Asiento> asientos) {
        this.asientos = asientos;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    @Override
    public String toString() {
        return "Sala{"
                + "id=" + id
                + ", nombre='" + nombre + '\''
                + ", capacidad=" + capacidad
                + ", tipoSala=" + tipoSala
                + ", asientos=" + asientos
                + ", activa=" + activa
                + '}';
    }

    /**
     * @return the fechaModificacion
     */
    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     * @param fechaModificacion the fechaModificacion to set
     */
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    /**
     * @return the usuarioModificacion
     */
    public Integer getUsuarioModificacion() {
        return usuarioModificacion;
    }

    /**
     * @param usuarioModificacion the usuarioModificacion to set
     */
    public void setUsuarioModificacion(Integer usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }
}
