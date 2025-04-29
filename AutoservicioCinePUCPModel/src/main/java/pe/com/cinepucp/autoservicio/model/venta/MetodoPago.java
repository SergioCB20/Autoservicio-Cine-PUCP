package pe.com.cinepucp.autoservicio.model.venta;

public enum MetodoPago {
    QR("qr"),
    TARJETA("tarjeta"),
    EFECTIVO("efectivo");
    private final String descripcion;

    MetodoPago(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    // Método para convertir String a enum
    public static MetodoPago fromString(String text) {
        for (MetodoPago tipo : MetodoPago.values()) {
            if (tipo.descripcion.equalsIgnoreCase(text)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Método de pago no válido: " + text);
    }
}
