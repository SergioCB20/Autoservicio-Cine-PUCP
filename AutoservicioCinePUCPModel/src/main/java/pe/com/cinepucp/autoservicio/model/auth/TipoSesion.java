/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.model.auth;

/**
 *
 * @author Sergio
 */
public enum TipoSesion {
    SMS("sms"),
    QR("qr"),
    EMAIL("email");
    private final String descripcion;

    TipoSesion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public static TipoSesion fromString(String text) {
        for (TipoSesion tipo : TipoSesion.values()) {
            if (tipo.descripcion.equalsIgnoreCase(text)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de Sesion no válido: " + text);
    }
}
