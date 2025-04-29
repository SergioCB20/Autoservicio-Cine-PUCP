package pe.com.cinepucp.autoservicio.model.venta;

public enum EstadoBoleto {
    VALIDO("valido"),
    USADO("usado"),
    ANULADO("anulado");
    private final String descripcion;

    EstadoBoleto(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    // Método para convertir String a enum
    public static EstadoBoleto fromString(String text) {
        for (EstadoBoleto tipo : EstadoBoleto.values()) {
            if (tipo.descripcion.equalsIgnoreCase(text)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Estado de boleto no válido: " + text);
    }
}
