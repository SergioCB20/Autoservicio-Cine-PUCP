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
    ESTANDAR("estándar"),
    TRES_D("3D"),
    PREMIUM("premium"),
    CUATRO_DX("4DX");

    private final String descripcion;

    TipoSala(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    // Método para convertir String a enum
    public static TipoSala fromString(String text) {
        for (TipoSala tipo : TipoSala.values()) {
            if (tipo.descripcion.equalsIgnoreCase(text)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de sala no válido: " + text);
    }
}
