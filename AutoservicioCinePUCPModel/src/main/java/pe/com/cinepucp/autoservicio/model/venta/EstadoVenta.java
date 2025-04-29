package pe.com.cinepucp.autoservicio.model.venta;

public enum EstadoVenta {
    PENDIENTE("pendiente"),
    COMPLETADA("completada"),
    CANCELADA("cancelada");
    private final String descripcion;

    EstadoVenta(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    // Método para convertir String a enum
    public static EstadoVenta fromString(String text) {
        for (EstadoVenta tipo : EstadoVenta.values()) {
            if (tipo.descripcion.equalsIgnoreCase(text)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Estado de venta no válido: " + text);
    }
}
