package pe.com.cinepucp.autoservicio.model.salas;

public enum EstadoAsiento {
    DISPONIBLE("disponible"),
    OCUPADO("ocupado"),
    RESERVADO("reservado");
    private final String descripcion;

    EstadoAsiento(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public static EstadoAsiento fromString(String text) {
        for (EstadoAsiento tipo : EstadoAsiento.values()) {
            if (tipo.descripcion.equalsIgnoreCase(text)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Estado de Asiento no válido: " + text);
    }
}
