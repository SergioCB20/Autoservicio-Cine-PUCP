package pe.com.cinepucp.autoservicio.model.salas;

import pe.com.cinepucp.autoservicio.model.Peliculas.Funcion;

public class AsientoFuncion {
    
    private Integer idAsientoFuncion;
    private Funcion funcion;
    private Asiento asiento;
    private EstadoAsiento estado;

    public Integer getIdAsientoFuncion() {
        return idAsientoFuncion;
    }

    public void setIdAsientoFuncion(Integer idAsientoFuncion) {
        this.idAsientoFuncion = idAsientoFuncion;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }

    public EstadoAsiento getEstado() {
        return estado;
    }

    public void setEstado(EstadoAsiento estado) {
        this.estado = estado;
    }
    
}
