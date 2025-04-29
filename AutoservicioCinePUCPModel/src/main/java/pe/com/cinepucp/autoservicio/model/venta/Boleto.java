
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
}