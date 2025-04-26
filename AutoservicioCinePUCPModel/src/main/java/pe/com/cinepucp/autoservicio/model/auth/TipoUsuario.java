/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.model.auth;

/**
 *
 * @author Sergio
 */
public enum TipoUsuario {
    CLIENTE("normal"),
    ADMIN("vip");
    private final String descripcion;

    TipoUsuario(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public static TipoUsuario fromString(String text) {
        for (TipoUsuario tipo : TipoUsuario.values()) {
            if (tipo.descripcion.equalsIgnoreCase(text)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de sala no válido: " + text);
    }
}
