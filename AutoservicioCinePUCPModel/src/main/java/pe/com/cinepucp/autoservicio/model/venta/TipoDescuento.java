package pe.com.cinepucp.autoservicio.model.venta;

public enum TipoDescuento {
    PORCENTAJE("porcentaje"),
    MONTO_FIJO("monto_fijo");
    private final String descripcion;

    TipoDescuento(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    // Método para convertir String a enum
    public static TipoDescuento fromString(String text) {
        for (TipoDescuento tipo : TipoDescuento.values()) {
            if (tipo.descripcion.equalsIgnoreCase(text)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de descuento no válido: " + text);
    }
}
