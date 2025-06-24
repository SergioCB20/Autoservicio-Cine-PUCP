
package pe.com.cinepucp.autoservicio.model.venta;

import java.util.ArrayList;
import java.util.List;

import pe.com.cinepucp.autoservicio.model.Peliculas.Funcion;

public class Boleto {

    private Integer boletoId;
    private Venta venta;
    private Funcion funcion;
    private EstadoBoleto estado;
    private List<BoletoDetalle> detalles = new ArrayList<>();

    public Boleto() {
    }

    public Boleto(Venta venta, Funcion funcion, EstadoBoleto estado, List<BoletoDetalle> detalles) {
        this.venta = venta;
        this.funcion = funcion;
        this.estado = estado;
        this.detalles = detalles;
    }

    public Integer getBoletoId() {
        return boletoId;
    }

    public void setBoletoId(Integer boletoId) {
        this.boletoId = boletoId;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public EstadoBoleto getEstado() {
        return estado;
    }

    public void setEstado(EstadoBoleto estado) {
        this.estado = estado;
    }
    
    public List<BoletoDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<BoletoDetalle> detalles) {
        this.detalles = detalles;
    }
    
    @Override
    public String toString() {
        return "Boleto{" +
                "id=" + boletoId +
                ", venta_id='" + (venta != null ? venta.getVentaId() : "null") + '\'' +
                ", funcion_id=" + (funcion != null ? funcion.getFuncionId() : "null") +
                ", estado='" + estado +
                '}';
    }
}