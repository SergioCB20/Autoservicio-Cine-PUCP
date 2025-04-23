/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.model.salas;

/**
 *
 * @author Sergio
 */
public enum TipoAsiento {
    NORMAL("normal"),
    VIP("vip"),
    DISCAPACITADO("discapacitado");
    private final String descripcion;

    TipoAsiento(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
