package pe.com.cinepucp.autoservicio.model.venta;

import pe.com.cinepucp.autoservicio.model.salas.Asiento;

public class BoletoDetalle {
    private Integer detalleId;
    private Boleto boleto;
    private TipoBoleto tipo;
    private Double precio;
    private Asiento asiento;

    public BoletoDetalle() {
    }

    public BoletoDetalle(Integer detalleId, Boleto boleto, TipoBoleto tipo, Double precio, Asiento asiento) {
        this.detalleId = detalleId;
        this.boleto = boleto;
        this.tipo = tipo;
        this.precio = precio;
        this.asiento = asiento;
    }
    
    public Integer getDetalleId() {
        return detalleId;
    }

    public void setDetalleId(Integer detalleId) {
        this.detalleId = detalleId;
    }

    public Boleto getBoleto() {
        return boleto;
    }

    public void setBoleto(Boleto boleto) {
        this.boleto = boleto;
    }

    public TipoBoleto getTipo() {
        return tipo;
    }

    public void setTipo(TipoBoleto tipo) {
        this.tipo = tipo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }
    
}
