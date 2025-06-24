/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.model.salas;

import java.util.Date;

/**
 *
 * @author Sergio
 */
public class Asiento {
    private Integer id;
    private Sala sala;
    private char fila;
    private Integer numero;
    private TipoAsiento tipo;
    private boolean activo  = true;
    private Date fechaModificacion;
    private Integer usuarioModificacion;

    public Asiento() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    
    public Asiento(char fila, Integer numero, TipoAsiento tipo) {
        this.fila = fila;
        this.numero = numero;
        this.tipo = tipo;
    }

    public Asiento(Sala sala, char fila, Integer numero, TipoAsiento tipo) {
        this.sala = sala;
        this.fila = fila;
        this.numero = numero;
        this.tipo = tipo;
    }

    

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public char getFila() {
        return fila;
    }

    public void setFila(char fila) {
        this.fila = fila;
    }

    
    public TipoAsiento getTipo() {
        return tipo;
    }

    public void setTipo(TipoAsiento tipo) {
        this.tipo = tipo;
    }
    
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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
    
    @Override
    public String toString() {
        return "Asiento{" +
                "id=" + getId() +
                ", sala=" + (sala != null ? sala.getId() : "null") + 
                ", fila=" + fila +
                ", numero=" + getNumero() +
                ", tipo='" + tipo + '\'' +
                ", activo=" + activo +
                '}';
    }

}
