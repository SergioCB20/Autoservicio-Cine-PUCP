/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.model.salas;

/**
 *
 * @author Sergio
 */
public enum TipoSala {
    ESTANDAR("Estandar"),
    CUATRO_DX("4DX");

    private final String descripcion;

    TipoSala(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

