/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package pe.com.cinepucp.autoservicio.model.venta;

/**
 *
 * @author Piero
 */
public enum TipoBoleto {
    NIÑO("niño"),
    ADULTO("adulto"),
    ADULTO_MAYOR("adulto_mayor");
    private final String descripcion;

    TipoBoleto(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
