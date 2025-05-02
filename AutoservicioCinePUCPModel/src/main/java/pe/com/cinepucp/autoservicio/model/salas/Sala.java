/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.model.salas;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Sergio
 */
public class Sala {

    private int id;
    private String nombre;
    private int capacidad;
    private TipoSala tipoSala;
    private List<Asiento> asientos;
    private boolean activa = true;

    public Sala() {
    }

    public Sala(String nombre, int capacidad, TipoSala tipoSala) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tipoSala = tipoSala;
        this.asientos = new ArrayList<>();
    }

    public Sala(String nombre, int capacidad, TipoSala tipoSala, List<Asiento> asientos) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tipoSala = tipoSala;
        this.asientos = asientos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
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
}
